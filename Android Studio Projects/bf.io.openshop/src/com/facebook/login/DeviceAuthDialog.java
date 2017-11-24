package com.facebook.login;

import android.support.v4.app.*;
import java.util.concurrent.atomic.*;
import android.app.*;
import java.util.*;
import com.facebook.internal.*;
import org.json.*;
import java.util.concurrent.*;
import android.widget.*;
import android.view.*;
import com.facebook.*;
import android.support.annotation.*;
import android.content.*;
import android.text.*;
import android.os.*;

public class DeviceAuthDialog extends DialogFragment
{
    private static final String DEVICE_OUATH_ENDPOINT = "oauth/device";
    private static final String REQUEST_STATE_KEY = "request_state";
    private AtomicBoolean completed;
    private TextView confirmationCode;
    private volatile GraphRequestAsyncTask currentGraphRequestPoll;
    private volatile RequestState currentRequestState;
    private DeviceAuthMethodHandler deviceAuthMethodHandler;
    private Dialog dialog;
    private boolean isBeingDestroyed;
    private ProgressBar progressBar;
    private volatile ScheduledFuture scheduledPoll;
    
    public DeviceAuthDialog() {
        this.completed = new AtomicBoolean();
        this.isBeingDestroyed = false;
    }
    
    private GraphRequest getPollRequest() {
        final Bundle bundle = new Bundle();
        bundle.putString("type", "device_token");
        bundle.putString("client_id", FacebookSdk.getApplicationId());
        bundle.putString("code", this.currentRequestState.getRequestCode());
        return new GraphRequest(null, "oauth/device", bundle, HttpMethod.POST, (GraphRequest.Callback)new GraphRequest.Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                if (DeviceAuthDialog.this.completed.get()) {
                    return;
                }
                final FacebookRequestError error = graphResponse.getError();
                if (error != null) {
                    final String errorMessage = error.getErrorMessage();
                    if (errorMessage.equals("authorization_pending") || errorMessage.equals("slow_down")) {
                        DeviceAuthDialog.this.schedulePoll();
                        return;
                    }
                    if (errorMessage.equals("authorization_declined") || errorMessage.equals("code_expired")) {
                        DeviceAuthDialog.this.onCancel();
                        return;
                    }
                    DeviceAuthDialog.this.onError(graphResponse.getError().getException());
                }
                else {
                    try {
                        DeviceAuthDialog.this.onSuccess(graphResponse.getJSONObject().getString("access_token"));
                    }
                    catch (JSONException ex) {
                        DeviceAuthDialog.this.onError(new FacebookException((Throwable)ex));
                    }
                }
            }
        });
    }
    
    private void onCancel() {
        if (!this.completed.compareAndSet(false, true)) {
            return;
        }
        if (this.deviceAuthMethodHandler != null) {
            this.deviceAuthMethodHandler.onCancel();
        }
        this.dialog.dismiss();
    }
    
    private void onError(final FacebookException ex) {
        if (!this.completed.compareAndSet(false, true)) {
            return;
        }
        this.deviceAuthMethodHandler.onError(ex);
        this.dialog.dismiss();
    }
    
    private void onSuccess(final String s) {
        final Bundle bundle = new Bundle();
        bundle.putString("fields", "id,permissions");
        new GraphRequest(new AccessToken(s, FacebookSdk.getApplicationId(), "0", null, null, null, null, null), "me", bundle, HttpMethod.GET, (GraphRequest.Callback)new GraphRequest.Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                if (DeviceAuthDialog.this.completed.get()) {
                    return;
                }
                if (graphResponse.getError() != null) {
                    DeviceAuthDialog.this.onError(graphResponse.getError().getException());
                    return;
                }
                try {
                    final JSONObject jsonObject = graphResponse.getJSONObject();
                    final String string = jsonObject.getString("id");
                    final Utility.PermissionsPair handlePermissionResponse = Utility.handlePermissionResponse(jsonObject);
                    DeviceAuthDialog.this.deviceAuthMethodHandler.onSuccess(s, FacebookSdk.getApplicationId(), string, handlePermissionResponse.getGrantedPermissions(), handlePermissionResponse.getDeclinedPermissions(), AccessTokenSource.DEVICE_AUTH, null, null);
                    DeviceAuthDialog.this.dialog.dismiss();
                }
                catch (JSONException ex) {
                    DeviceAuthDialog.this.onError(new FacebookException((Throwable)ex));
                }
            }
        }).executeAsync();
    }
    
    private void poll() {
        this.currentRequestState.setLastPoll(new Date().getTime());
        this.currentGraphRequestPoll = this.getPollRequest().executeAsync();
    }
    
    private void schedulePoll() {
        this.scheduledPoll = DeviceAuthMethodHandler.getBackgroundExecutor().schedule(new Runnable() {
            @Override
            public void run() {
                DeviceAuthDialog.this.poll();
            }
        }, this.currentRequestState.getInterval(), TimeUnit.SECONDS);
    }
    
    private void setCurrentRequestState(final RequestState currentRequestState) {
        this.currentRequestState = currentRequestState;
        this.confirmationCode.setText((CharSequence)currentRequestState.getUserCode());
        this.confirmationCode.setVisibility(0);
        this.progressBar.setVisibility(8);
        if (currentRequestState.withinLastRefreshWindow()) {
            this.schedulePoll();
            return;
        }
        this.poll();
    }
    
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        this.dialog = new Dialog((Context)this.getActivity(), R.style.com_facebook_auth_dialog);
        final View inflate = this.getActivity().getLayoutInflater().inflate(R.layout.com_facebook_device_auth_dialog_fragment, (ViewGroup)null);
        this.progressBar = (ProgressBar)inflate.findViewById(R.id.progress_bar);
        this.confirmationCode = (TextView)inflate.findViewById(R.id.confirmation_code);
        ((Button)inflate.findViewById(R.id.cancel_button)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                DeviceAuthDialog.this.onCancel();
            }
        });
        ((TextView)inflate.findViewById(R.id.com_facebook_device_auth_instructions)).setText((CharSequence)Html.fromHtml(this.getString(R.string.com_facebook_device_auth_instructions)));
        this.dialog.setContentView(inflate);
        return this.dialog;
    }
    
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.deviceAuthMethodHandler = (DeviceAuthMethodHandler)((LoginFragment)((FacebookActivity)this.getActivity()).getCurrentFragment()).getLoginClient().getCurrentHandler();
        if (bundle != null) {
            final RequestState currentRequestState = (RequestState)bundle.getParcelable("request_state");
            if (currentRequestState != null) {
                this.setCurrentRequestState(currentRequestState);
            }
        }
        return onCreateView;
    }
    
    @Override
    public void onDestroy() {
        this.isBeingDestroyed = true;
        this.completed.set(true);
        super.onDestroy();
        if (this.currentGraphRequestPoll != null) {
            this.currentGraphRequestPoll.cancel(true);
        }
        if (this.scheduledPoll != null) {
            this.scheduledPoll.cancel(true);
        }
    }
    
    @Override
    public void onDismiss(final DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (!this.isBeingDestroyed) {
            this.onCancel();
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.currentRequestState != null) {
            bundle.putParcelable("request_state", (Parcelable)this.currentRequestState);
        }
    }
    
    public void startLogin(final LoginClient.Request request) {
        final Bundle bundle = new Bundle();
        bundle.putString("type", "device_code");
        bundle.putString("client_id", FacebookSdk.getApplicationId());
        bundle.putString("scope", TextUtils.join((CharSequence)",", (Iterable)request.getPermissions()));
        new GraphRequest(null, "oauth/device", bundle, HttpMethod.POST, (GraphRequest.Callback)new GraphRequest.Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                if (graphResponse.getError() != null) {
                    DeviceAuthDialog.this.onError(graphResponse.getError().getException());
                    return;
                }
                final JSONObject jsonObject = graphResponse.getJSONObject();
                final RequestState requestState = new RequestState();
                try {
                    requestState.setUserCode(jsonObject.getString("user_code"));
                    requestState.setRequestCode(jsonObject.getString("code"));
                    requestState.setInterval(jsonObject.getLong("interval"));
                    DeviceAuthDialog.this.setCurrentRequestState(requestState);
                }
                catch (JSONException ex) {
                    DeviceAuthDialog.this.onError(new FacebookException((Throwable)ex));
                }
            }
        }).executeAsync();
    }
    
    private static class RequestState implements Parcelable
    {
        public static final Parcelable$Creator<RequestState> CREATOR;
        private long interval;
        private long lastPoll;
        private String requestCode;
        private String userCode;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<RequestState>() {
                public RequestState createFromParcel(final Parcel parcel) {
                    return new RequestState(parcel);
                }
                
                public RequestState[] newArray(final int n) {
                    return new RequestState[n];
                }
            };
        }
        
        RequestState() {
        }
        
        protected RequestState(final Parcel parcel) {
            this.userCode = parcel.readString();
            this.requestCode = parcel.readString();
            this.interval = parcel.readLong();
            this.lastPoll = parcel.readLong();
        }
        
        public int describeContents() {
            return 0;
        }
        
        public long getInterval() {
            return this.interval;
        }
        
        public String getRequestCode() {
            return this.requestCode;
        }
        
        public String getUserCode() {
            return this.userCode;
        }
        
        public void setInterval(final long interval) {
            this.interval = interval;
        }
        
        public void setLastPoll(final long lastPoll) {
            this.lastPoll = lastPoll;
        }
        
        public void setRequestCode(final String requestCode) {
            this.requestCode = requestCode;
        }
        
        public void setUserCode(final String userCode) {
            this.userCode = userCode;
        }
        
        public boolean withinLastRefreshWindow() {
            return this.lastPoll != 0L && new Date().getTime() - this.lastPoll - 1000L * this.interval < 0L;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            parcel.writeString(this.userCode);
            parcel.writeString(this.requestCode);
            parcel.writeLong(this.interval);
            parcel.writeLong(this.lastPoll);
        }
    }
}

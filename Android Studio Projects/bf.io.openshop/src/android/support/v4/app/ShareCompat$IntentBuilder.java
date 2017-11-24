package android.support.v4.app;

import android.app.*;
import java.util.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.support.annotation.*;
import android.text.*;

public static class IntentBuilder
{
    private Activity mActivity;
    private ArrayList<String> mBccAddresses;
    private ArrayList<String> mCcAddresses;
    private CharSequence mChooserTitle;
    private Intent mIntent;
    private ArrayList<Uri> mStreams;
    private ArrayList<String> mToAddresses;
    
    private IntentBuilder(final Activity mActivity) {
        this.mActivity = mActivity;
        (this.mIntent = new Intent().setAction("android.intent.action.SEND")).putExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE", mActivity.getPackageName());
        this.mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY", (Parcelable)mActivity.getComponentName());
        this.mIntent.addFlags(524288);
    }
    
    private void combineArrayExtra(final String s, final ArrayList<String> list) {
        final String[] stringArrayExtra = this.mIntent.getStringArrayExtra(s);
        int length;
        if (stringArrayExtra != null) {
            length = stringArrayExtra.length;
        }
        else {
            length = 0;
        }
        final String[] array = new String[length + list.size()];
        list.toArray(array);
        if (stringArrayExtra != null) {
            System.arraycopy(stringArrayExtra, 0, array, list.size(), length);
        }
        this.mIntent.putExtra(s, array);
    }
    
    private void combineArrayExtra(final String s, final String[] array) {
        final Intent intent = this.getIntent();
        final String[] stringArrayExtra = intent.getStringArrayExtra(s);
        int length;
        if (stringArrayExtra != null) {
            length = stringArrayExtra.length;
        }
        else {
            length = 0;
        }
        final String[] array2 = new String[length + array.length];
        if (stringArrayExtra != null) {
            System.arraycopy(stringArrayExtra, 0, array2, 0, length);
        }
        System.arraycopy(array, 0, array2, length, array.length);
        intent.putExtra(s, array2);
    }
    
    public static IntentBuilder from(final Activity activity) {
        return new IntentBuilder(activity);
    }
    
    public IntentBuilder addEmailBcc(final String s) {
        if (this.mBccAddresses == null) {
            this.mBccAddresses = new ArrayList<String>();
        }
        this.mBccAddresses.add(s);
        return this;
    }
    
    public IntentBuilder addEmailBcc(final String[] array) {
        this.combineArrayExtra("android.intent.extra.BCC", array);
        return this;
    }
    
    public IntentBuilder addEmailCc(final String s) {
        if (this.mCcAddresses == null) {
            this.mCcAddresses = new ArrayList<String>();
        }
        this.mCcAddresses.add(s);
        return this;
    }
    
    public IntentBuilder addEmailCc(final String[] array) {
        this.combineArrayExtra("android.intent.extra.CC", array);
        return this;
    }
    
    public IntentBuilder addEmailTo(final String s) {
        if (this.mToAddresses == null) {
            this.mToAddresses = new ArrayList<String>();
        }
        this.mToAddresses.add(s);
        return this;
    }
    
    public IntentBuilder addEmailTo(final String[] array) {
        this.combineArrayExtra("android.intent.extra.EMAIL", array);
        return this;
    }
    
    public IntentBuilder addStream(final Uri stream) {
        final Uri uri = (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
        if (uri == null) {
            return this.setStream(stream);
        }
        if (this.mStreams == null) {
            this.mStreams = new ArrayList<Uri>();
        }
        if (uri != null) {
            this.mIntent.removeExtra("android.intent.extra.STREAM");
            this.mStreams.add(uri);
        }
        this.mStreams.add(stream);
        return this;
    }
    
    public Intent createChooserIntent() {
        return Intent.createChooser(this.getIntent(), this.mChooserTitle);
    }
    
    Activity getActivity() {
        return this.mActivity;
    }
    
    public Intent getIntent() {
        int n = 1;
        if (this.mToAddresses != null) {
            this.combineArrayExtra("android.intent.extra.EMAIL", this.mToAddresses);
            this.mToAddresses = null;
        }
        if (this.mCcAddresses != null) {
            this.combineArrayExtra("android.intent.extra.CC", this.mCcAddresses);
            this.mCcAddresses = null;
        }
        if (this.mBccAddresses != null) {
            this.combineArrayExtra("android.intent.extra.BCC", this.mBccAddresses);
            this.mBccAddresses = null;
        }
        if (this.mStreams == null || this.mStreams.size() <= n) {
            n = 0;
        }
        final boolean equals = this.mIntent.getAction().equals("android.intent.action.SEND_MULTIPLE");
        if (n == 0 && equals) {
            this.mIntent.setAction("android.intent.action.SEND");
            if (this.mStreams != null && !this.mStreams.isEmpty()) {
                this.mIntent.putExtra("android.intent.extra.STREAM", (Parcelable)this.mStreams.get(0));
            }
            else {
                this.mIntent.removeExtra("android.intent.extra.STREAM");
            }
            this.mStreams = null;
        }
        if (n != 0 && !equals) {
            this.mIntent.setAction("android.intent.action.SEND_MULTIPLE");
            if (this.mStreams != null && !this.mStreams.isEmpty()) {
                this.mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)this.mStreams);
            }
            else {
                this.mIntent.removeExtra("android.intent.extra.STREAM");
            }
        }
        return this.mIntent;
    }
    
    public IntentBuilder setChooserTitle(@StringRes final int n) {
        return this.setChooserTitle(this.mActivity.getText(n));
    }
    
    public IntentBuilder setChooserTitle(final CharSequence mChooserTitle) {
        this.mChooserTitle = mChooserTitle;
        return this;
    }
    
    public IntentBuilder setEmailBcc(final String[] array) {
        this.mIntent.putExtra("android.intent.extra.BCC", array);
        return this;
    }
    
    public IntentBuilder setEmailCc(final String[] array) {
        this.mIntent.putExtra("android.intent.extra.CC", array);
        return this;
    }
    
    public IntentBuilder setEmailTo(final String[] array) {
        if (this.mToAddresses != null) {
            this.mToAddresses = null;
        }
        this.mIntent.putExtra("android.intent.extra.EMAIL", array);
        return this;
    }
    
    public IntentBuilder setHtmlText(final String s) {
        this.mIntent.putExtra("android.intent.extra.HTML_TEXT", s);
        if (!this.mIntent.hasExtra("android.intent.extra.TEXT")) {
            this.setText((CharSequence)Html.fromHtml(s));
        }
        return this;
    }
    
    public IntentBuilder setStream(final Uri uri) {
        if (!this.mIntent.getAction().equals("android.intent.action.SEND")) {
            this.mIntent.setAction("android.intent.action.SEND");
        }
        this.mStreams = null;
        this.mIntent.putExtra("android.intent.extra.STREAM", (Parcelable)uri);
        return this;
    }
    
    public IntentBuilder setSubject(final String s) {
        this.mIntent.putExtra("android.intent.extra.SUBJECT", s);
        return this;
    }
    
    public IntentBuilder setText(final CharSequence charSequence) {
        this.mIntent.putExtra("android.intent.extra.TEXT", charSequence);
        return this;
    }
    
    public IntentBuilder setType(final String type) {
        this.mIntent.setType(type);
        return this;
    }
    
    public void startChooser() {
        this.mActivity.startActivity(this.createChooserIntent());
    }
}

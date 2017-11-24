package com.facebook;

import com.facebook.internal.*;
import android.os.*;
import android.util.*;
import android.text.*;
import org.json.*;
import java.util.*;

public class TestUserManager
{
    private static final String LOG_TAG = "TestUserManager";
    private Map<String, JSONObject> appTestAccounts;
    private String testApplicationId;
    private String testApplicationSecret;
    
    public TestUserManager(final String testApplicationSecret, final String testApplicationId) {
        if (Utility.isNullOrEmpty(testApplicationId) || Utility.isNullOrEmpty(testApplicationSecret)) {
            throw new FacebookException("Must provide app ID and secret");
        }
        this.testApplicationSecret = testApplicationSecret;
        this.testApplicationId = testApplicationId;
    }
    
    private JSONObject createTestAccount(final List<String> list, final Mode mode, final String s) {
        final Bundle bundle = new Bundle();
        bundle.putString("installed", "true");
        bundle.putString("permissions", this.getPermissionsString(list));
        bundle.putString("access_token", this.getAppAccessToken());
        if (mode == Mode.SHARED) {
            bundle.putString("name", String.format("Shared %s Testuser", this.getSharedTestAccountIdentifier(list, s)));
        }
        final GraphResponse executeAndWait = new GraphRequest(null, String.format("%s/accounts/test-users", this.testApplicationId), bundle, HttpMethod.POST).executeAndWait();
        final FacebookRequestError error = executeAndWait.getError();
        JSONObject jsonObject = executeAndWait.getJSONObject();
        if (error != null) {
            jsonObject = null;
        }
        else {
            assert jsonObject != null;
            if (mode == Mode.SHARED) {
                while (true) {
                    try {
                        jsonObject.put("name", (Object)bundle.getString("name"));
                        this.storeTestAccount(jsonObject);
                        return jsonObject;
                    }
                    catch (JSONException ex) {
                        Log.e("TestUserManager", "Could not set name", (Throwable)ex);
                        continue;
                    }
                    break;
                }
            }
        }
        return jsonObject;
    }
    
    private JSONObject findOrCreateSharedTestAccount(final List<String> list, final Mode mode, final String s) {
        final JSONObject testAccountMatchingIdentifier = this.findTestAccountMatchingIdentifier(this.getSharedTestAccountIdentifier(list, s));
        if (testAccountMatchingIdentifier != null) {
            return testAccountMatchingIdentifier;
        }
        return this.createTestAccount(list, mode, s);
    }
    
    private JSONObject findTestAccountMatchingIdentifier(final String s) {
        synchronized (this) {
            for (final JSONObject jsonObject : this.appTestAccounts.values()) {
                if (jsonObject.optString("name").contains(s)) {
                    return jsonObject;
                }
            }
            return null;
        }
    }
    
    private AccessToken getAccessTokenForUser(List<String> list, final Mode mode, final String s) {
        this.retrieveTestAccountsForAppIfNeeded();
        if (Utility.isNullOrEmpty((Collection<Object>)list)) {
            list = Arrays.asList("email", "publish_actions");
        }
        JSONObject jsonObject;
        if (mode == Mode.PRIVATE) {
            jsonObject = this.createTestAccount(list, mode, s);
        }
        else {
            jsonObject = this.findOrCreateSharedTestAccount(list, mode, s);
        }
        return new AccessToken(jsonObject.optString("access_token"), this.testApplicationId, jsonObject.optString("id"), list, null, AccessTokenSource.TEST_USER, null, null);
    }
    
    private String getPermissionsString(final List<String> list) {
        return TextUtils.join((CharSequence)",", (Iterable)list);
    }
    
    private String getSharedTestAccountIdentifier(final List<String> list, final String s) {
        final long n = 0xFFFFFFFFL & this.getPermissionsString(list).hashCode();
        long n2;
        if (s != null) {
            n2 = (0xFFFFFFFFL & s.hashCode());
        }
        else {
            n2 = 0L;
        }
        return this.validNameStringFromInteger(n ^ n2);
    }
    
    private void populateTestAccounts(final JSONArray jsonArray, final JSONObject jsonObject) {
        // monitorenter(this)
        int i = 0;
        try {
            while (i < jsonArray.length()) {
                final JSONObject optJSONObject = jsonArray.optJSONObject(i);
                final JSONObject optJSONObject2 = jsonObject.optJSONObject(optJSONObject.optString("id"));
                try {
                    optJSONObject.put("name", (Object)optJSONObject2.optString("name"));
                    this.storeTestAccount(optJSONObject);
                    ++i;
                }
                catch (JSONException ex) {
                    Log.e("TestUserManager", "Could not set name", (Throwable)ex);
                }
            }
        }
        finally {
        }
        // monitorexit(this)
    }
    // monitorexit(this)
    
    private void retrieveTestAccountsForAppIfNeeded() {
        while (true) {
            final List<GraphResponse> executeBatchAndWait;
            synchronized (this) {
                if (this.appTestAccounts != null) {
                    return;
                }
                this.appTestAccounts = new HashMap<String, JSONObject>();
                GraphRequest.setDefaultBatchApplicationId(this.testApplicationId);
                final Bundle bundle = new Bundle();
                bundle.putString("access_token", this.getAppAccessToken());
                final GraphRequest graphRequest = new GraphRequest(null, "app/accounts/test-users", bundle, null);
                graphRequest.setBatchEntryName("testUsers");
                graphRequest.setBatchEntryOmitResultOnSuccess(false);
                final Bundle bundle2 = new Bundle();
                bundle2.putString("access_token", this.getAppAccessToken());
                bundle2.putString("ids", "{result=testUsers:$.data.*.id}");
                bundle2.putString("fields", "name");
                final GraphRequest graphRequest2 = new GraphRequest(null, "", bundle2, null);
                graphRequest2.setBatchEntryDependsOn("testUsers");
                executeBatchAndWait = GraphRequest.executeBatchAndWait(graphRequest, graphRequest2);
                if (executeBatchAndWait == null || executeBatchAndWait.size() != 2) {
                    throw new FacebookException("Unexpected number of results from TestUsers batch query");
                }
            }
            this.populateTestAccounts(executeBatchAndWait.get(0).getJSONObject().optJSONArray("data"), executeBatchAndWait.get(1).getJSONObject());
        }
    }
    
    private void storeTestAccount(final JSONObject jsonObject) {
        synchronized (this) {
            this.appTestAccounts.put(jsonObject.optString("id"), jsonObject);
        }
    }
    
    private String validNameStringFromInteger(final long n) {
        final String string = Long.toString(n);
        final StringBuilder sb = new StringBuilder("Perm");
        char c = '\0';
        for (char c2 : string.toCharArray()) {
            if (c2 == c) {
                c2 += '\n';
            }
            sb.append((char)(-48 + (c2 + 'a')));
            c = c2;
        }
        return sb.toString();
    }
    
    public AccessToken getAccessTokenForPrivateUser(final List<String> list) {
        return this.getAccessTokenForUser(list, Mode.PRIVATE, null);
    }
    
    public AccessToken getAccessTokenForSharedUser(final List<String> list) {
        return this.getAccessTokenForSharedUser(list, null);
    }
    
    public AccessToken getAccessTokenForSharedUser(final List<String> list, final String s) {
        return this.getAccessTokenForUser(list, Mode.SHARED, s);
    }
    
    final String getAppAccessToken() {
        return this.testApplicationId + "|" + this.testApplicationSecret;
    }
    
    public String getTestApplicationId() {
        synchronized (this) {
            return this.testApplicationId;
        }
    }
    
    public String getTestApplicationSecret() {
        synchronized (this) {
            return this.testApplicationSecret;
        }
    }
    
    private enum Mode
    {
        PRIVATE, 
        SHARED;
    }
}

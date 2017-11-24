package bolts;

import android.content.*;
import android.net.*;
import java.util.*;
import org.json.*;
import java.io.*;
import java.util.concurrent.*;
import java.net.*;
import android.webkit.*;

public class WebViewAppLinkResolver implements AppLinkResolver
{
    private static final String KEY_AL_VALUE = "value";
    private static final String KEY_ANDROID = "android";
    private static final String KEY_APP_NAME = "app_name";
    private static final String KEY_CLASS = "class";
    private static final String KEY_PACKAGE = "package";
    private static final String KEY_SHOULD_FALLBACK = "should_fallback";
    private static final String KEY_URL = "url";
    private static final String KEY_WEB = "web";
    private static final String KEY_WEB_URL = "url";
    private static final String META_TAG_PREFIX = "al";
    private static final String PREFER_HEADER = "Prefer-Html-Meta-Tags";
    private static final String TAG_EXTRACTION_JAVASCRIPT = "javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())";
    private final Context context;
    
    public WebViewAppLinkResolver(final Context context) {
        this.context = context;
    }
    
    private static List<Map<String, Object>> getAlList(final Map<String, Object> map, final String s) {
        List<Map<String, Object>> emptyList = map.get(s);
        if (emptyList == null) {
            emptyList = Collections.emptyList();
        }
        return emptyList;
    }
    
    private static AppLink makeAppLinkFromAlData(final Map<String, Object> map, final Uri uri) {
        final ArrayList<AppLink.Target> list = new ArrayList<AppLink.Target>();
        Object emptyList = map.get("android");
        if (emptyList == null) {
            emptyList = Collections.emptyList();
        }
        for (final Map<String, Object> map2 : emptyList) {
            final List<Map<String, Object>> alList = getAlList(map2, "url");
            final List<Map<String, Object>> alList2 = getAlList(map2, "package");
            final List<Map<String, Object>> alList3 = getAlList(map2, "class");
            final List<Map<String, Object>> alList4 = getAlList(map2, "app_name");
            for (int max = Math.max(alList.size(), Math.max(alList2.size(), Math.max(alList3.size(), alList4.size()))), i = 0; i < max; ++i) {
                Object value;
                if (alList.size() > i) {
                    value = alList.get(i).get("value");
                }
                else {
                    value = null;
                }
                final Uri tryCreateUrl = tryCreateUrl((String)value);
                Object value2;
                if (alList2.size() > i) {
                    value2 = alList2.get(i).get("value");
                }
                else {
                    value2 = null;
                }
                final String s = (String)value2;
                Object value3;
                if (alList3.size() > i) {
                    value3 = alList3.get(i).get("value");
                }
                else {
                    value3 = null;
                }
                final String s2 = (String)value3;
                Object value4;
                if (alList4.size() > i) {
                    value4 = alList4.get(i).get("value");
                }
                else {
                    value4 = null;
                }
                list.add(new AppLink.Target(s, s2, tryCreateUrl, (String)value4));
            }
        }
        Uri tryCreateUrl2 = uri;
        final List<Map> list2 = map.get("web");
        if (list2 != null && list2.size() > 0) {
            final Map<K, List> map3 = list2.get(0);
            final List<Map> list3 = map3.get("url");
            final List<Map> list4 = map3.get("should_fallback");
            if (list4 != null && list4.size() > 0 && Arrays.asList("no", "false", "0").contains(((Map<K, String>)list4.get(0)).get("value").toLowerCase())) {
                tryCreateUrl2 = null;
            }
            if (tryCreateUrl2 != null && list3 != null && list3.size() > 0) {
                tryCreateUrl2 = tryCreateUrl(((Map<K, String>)list3.get(0)).get("value"));
            }
        }
        return new AppLink(uri, list, tryCreateUrl2);
    }
    
    private static Map<String, Object> parseAlData(final JSONArray jsonArray) throws JSONException {
        final HashMap<String, List<Map<String, Object>>> hashMap = (HashMap<String, List<Map<String, Object>>>)new HashMap<String, Object>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            final String[] split = jsonObject.getString("property").split(":");
            if (split[0].equals("al")) {
                Object o = hashMap;
                for (int j = 1; j < split.length; ++j) {
                    List<Map<String, Object>> list = ((Map<String, List<Map<String, Object>>>)o).get(split[j]);
                    if (list == null) {
                        list = new ArrayList<Map<String, Object>>();
                        ((Map<String, List<Map<String, Object>>>)o).put(split[j], list);
                    }
                    Object o2;
                    if (list.size() > 0) {
                        o2 = list.get(-1 + list.size());
                    }
                    else {
                        o2 = null;
                    }
                    if (o2 == null || j == -1 + split.length) {
                        o2 = new HashMap<String, List<Map<String, Object>>>();
                        list.add((Map<String, Object>)o2);
                    }
                    o = o2;
                }
                if (jsonObject.has("content")) {
                    if (jsonObject.isNull("content")) {
                        ((Map<String, List<Map<String, Object>>>)o).put("value", null);
                    }
                    else {
                        ((Map<String, List<Map<String, Object>>>)o).put("value", (List<Map<String, Object>>)jsonObject.getString("content"));
                    }
                }
            }
        }
        return (Map<String, Object>)hashMap;
    }
    
    private static String readFromConnection(final URLConnection urlConnection) throws IOException {
        while (true) {
            Label_0081: {
                if (!(urlConnection instanceof HttpURLConnection)) {
                    break Label_0081;
                }
                final HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
                InputStream inputStream = null;
                ByteArrayOutputStream byteArrayOutputStream = null;
                Label_0089: {
                    try {
                        inputStream = urlConnection.getInputStream();
                        try {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                            final byte[] array = new byte[1024];
                            while (true) {
                                final int read = inputStream.read(array);
                                if (read == -1) {
                                    break Label_0089;
                                }
                                byteArrayOutputStream.write(array, 0, read);
                            }
                        }
                        finally {
                            inputStream.close();
                        }
                    }
                    catch (Exception ex) {
                        inputStream = httpURLConnection.getErrorStream();
                        continue;
                    }
                    break Label_0081;
                }
                String s = urlConnection.getContentEncoding();
                if (s == null) {
                    final String[] split = urlConnection.getContentType().split(";");
                    for (int length = split.length, i = 0; i < length; ++i) {
                        final String trim = split[i].trim();
                        if (trim.startsWith("charset=")) {
                            s = trim.substring("charset=".length());
                            break;
                        }
                    }
                    if (s == null) {
                        s = "UTF-8";
                    }
                }
                final String s2 = new String(byteArrayOutputStream.toByteArray(), s);
                inputStream.close();
                return s2;
            }
            InputStream inputStream = urlConnection.getInputStream();
            continue;
        }
    }
    
    private static Uri tryCreateUrl(final String s) {
        if (s == null) {
            return null;
        }
        return Uri.parse(s);
    }
    
    @Override
    public Task<AppLink> getAppLinkFromUrlInBackground(final Uri uri) {
        final Capture capture = new Capture();
        final Capture capture2 = new Capture();
        return Task.callInBackground((Callable<Object>)new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                URL url = new URL(uri.toString());
                URLConnection openConnection = null;
                while (url != null) {
                    openConnection = url.openConnection();
                    if (openConnection instanceof HttpURLConnection) {
                        ((HttpURLConnection)openConnection).setInstanceFollowRedirects(true);
                    }
                    openConnection.setRequestProperty("Prefer-Html-Meta-Tags", "al");
                    openConnection.connect();
                    if (openConnection instanceof HttpURLConnection) {
                        final HttpURLConnection httpURLConnection = (HttpURLConnection)openConnection;
                        if (httpURLConnection.getResponseCode() >= 300 && httpURLConnection.getResponseCode() < 400) {
                            url = new URL(httpURLConnection.getHeaderField("Location"));
                            httpURLConnection.disconnect();
                        }
                        else {
                            url = null;
                        }
                    }
                    else {
                        url = null;
                    }
                }
                try {
                    capture.set(readFromConnection(openConnection));
                    capture2.set(openConnection.getContentType());
                    return null;
                }
                finally {
                    if (openConnection instanceof HttpURLConnection) {
                        ((HttpURLConnection)openConnection).disconnect();
                    }
                }
            }
        }).onSuccessTask((Continuation<Object, Task<Object>>)new Continuation<Void, Task<JSONArray>>() {
            @Override
            public Task<JSONArray> then(final Task<Void> task) throws Exception {
                final TaskCompletionSource<JSONArray> taskCompletionSource = new TaskCompletionSource<JSONArray>();
                final WebView webView = new WebView(WebViewAppLinkResolver.this.context);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setNetworkAvailable(false);
                webView.setWebViewClient((WebViewClient)new WebViewClient() {
                    private boolean loaded = false;
                    
                    private void runJavaScript(final WebView webView) {
                        if (!this.loaded) {
                            this.loaded = true;
                            webView.loadUrl("javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())");
                        }
                    }
                    
                    public void onLoadResource(final WebView webView, final String s) {
                        super.onLoadResource(webView, s);
                        this.runJavaScript(webView);
                    }
                    
                    public void onPageFinished(final WebView webView, final String s) {
                        super.onPageFinished(webView, s);
                        this.runJavaScript(webView);
                    }
                });
                webView.addJavascriptInterface((Object)new Object() {
                    @JavascriptInterface
                    public void setValue(final String s) {
                        try {
                            taskCompletionSource.trySetResult(new JSONArray(s));
                        }
                        catch (JSONException ex) {
                            taskCompletionSource.trySetError((Exception)ex);
                        }
                    }
                }, "boltsWebViewAppLinkResolverResult");
                final Object value = capture2.get();
                String s = null;
                if (value != null) {
                    s = capture2.get().split(";")[0];
                }
                webView.loadDataWithBaseURL(uri.toString(), (String)capture.get(), s, (String)null, (String)null);
                return taskCompletionSource.getTask();
            }
        }, Task.UI_THREAD_EXECUTOR).onSuccess((Continuation<Object, AppLink>)new Continuation<JSONArray, AppLink>() {
            @Override
            public AppLink then(final Task<JSONArray> task) throws Exception {
                return makeAppLinkFromAlData(parseAlData(task.getResult()), uri);
            }
        });
    }
}

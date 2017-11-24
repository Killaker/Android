package com.facebook;

import com.facebook.internal.*;
import org.json.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class GraphResponse
{
    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    public static final String NON_JSON_RESPONSE_PROPERTY = "FACEBOOK_NON_JSON_RESULT";
    private static final String RESPONSE_LOG_TAG = "Response";
    public static final String SUCCESS_KEY = "success";
    private final HttpURLConnection connection;
    private final FacebookRequestError error;
    private final JSONObject graphObject;
    private final JSONArray graphObjectArray;
    private final String rawResponse;
    private final GraphRequest request;
    
    GraphResponse(final GraphRequest graphRequest, final HttpURLConnection httpURLConnection, final FacebookRequestError facebookRequestError) {
        this(graphRequest, httpURLConnection, null, null, null, facebookRequestError);
    }
    
    GraphResponse(final GraphRequest graphRequest, final HttpURLConnection httpURLConnection, final String s, final JSONArray jsonArray) {
        this(graphRequest, httpURLConnection, s, null, jsonArray, null);
    }
    
    GraphResponse(final GraphRequest graphRequest, final HttpURLConnection httpURLConnection, final String s, final JSONObject jsonObject) {
        this(graphRequest, httpURLConnection, s, jsonObject, null, null);
    }
    
    GraphResponse(final GraphRequest request, final HttpURLConnection connection, final String rawResponse, final JSONObject graphObject, final JSONArray graphObjectArray, final FacebookRequestError error) {
        this.request = request;
        this.connection = connection;
        this.rawResponse = rawResponse;
        this.graphObject = graphObject;
        this.graphObjectArray = graphObjectArray;
        this.error = error;
    }
    
    static List<GraphResponse> constructErrorResponses(final List<GraphRequest> list, final HttpURLConnection httpURLConnection, final FacebookException ex) {
        final int size = list.size();
        final ArrayList list2 = new ArrayList<GraphResponse>(size);
        for (int i = 0; i < size; ++i) {
            list2.add(new GraphResponse(list.get(i), httpURLConnection, new FacebookRequestError(httpURLConnection, ex)));
        }
        return (List<GraphResponse>)list2;
    }
    
    private static GraphResponse createResponseFromObject(final GraphRequest graphRequest, final HttpURLConnection httpURLConnection, Object null, final Object o) throws JSONException {
        if (null instanceof JSONObject) {
            final JSONObject jsonObject = (JSONObject)null;
            final FacebookRequestError checkResponseAndCreateError = FacebookRequestError.checkResponseAndCreateError(jsonObject, o, httpURLConnection);
            if (checkResponseAndCreateError != null) {
                if (checkResponseAndCreateError.getErrorCode() == 190 && Utility.isCurrentAccessToken(graphRequest.getAccessToken())) {
                    AccessToken.setCurrentAccessToken(null);
                }
                return new GraphResponse(graphRequest, httpURLConnection, checkResponseAndCreateError);
            }
            final Object stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jsonObject, "body", "FACEBOOK_NON_JSON_RESULT");
            if (stringPropertyAsJSON instanceof JSONObject) {
                return new GraphResponse(graphRequest, httpURLConnection, stringPropertyAsJSON.toString(), (JSONObject)stringPropertyAsJSON);
            }
            if (stringPropertyAsJSON instanceof JSONArray) {
                return new GraphResponse(graphRequest, httpURLConnection, stringPropertyAsJSON.toString(), (JSONArray)stringPropertyAsJSON);
            }
            null = JSONObject.NULL;
        }
        if (null == JSONObject.NULL) {
            return new GraphResponse(graphRequest, httpURLConnection, null.toString(), (JSONObject)null);
        }
        throw new FacebookException("Got unexpected object type in response, class: " + null.getClass().getSimpleName());
    }
    
    private static List<GraphResponse> createResponsesFromObject(final HttpURLConnection httpURLConnection, final List<GraphRequest> list, Object o) throws FacebookException, JSONException {
        final int size = list.size();
        final ArrayList list2 = new ArrayList<GraphResponse>(size);
        final Object o2 = o;
        while (true) {
            if (size == 1) {
                final GraphRequest graphRequest = list.get(0);
                try {
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("body", o);
                    int responseCode;
                    if (httpURLConnection != null) {
                        responseCode = httpURLConnection.getResponseCode();
                    }
                    else {
                        responseCode = 200;
                    }
                    jsonObject.put("code", responseCode);
                    final JSONArray jsonArray = new JSONArray();
                    jsonArray.put((Object)jsonObject);
                    o = jsonArray;
                    if (!(o instanceof JSONArray) || ((JSONArray)o).length() != size) {
                        throw new FacebookException("Unexpected number of results");
                    }
                }
                catch (JSONException ex) {
                    list2.add(new GraphResponse(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception)ex)));
                    continue;
                }
                catch (IOException ex2) {
                    list2.add(new GraphResponse(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, ex2)));
                    continue;
                }
                final JSONArray jsonArray2 = (JSONArray)o;
                int i = 0;
            Label_0252_Outer:
                while (i < jsonArray2.length()) {
                    final GraphRequest graphRequest2 = list.get(i);
                    while (true) {
                        try {
                            list2.add(createResponseFromObject(graphRequest2, httpURLConnection, jsonArray2.get(i), o2));
                            ++i;
                            continue Label_0252_Outer;
                        }
                        catch (JSONException ex3) {
                            list2.add(new GraphResponse(graphRequest2, httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception)ex3)));
                            continue;
                        }
                        catch (FacebookException ex4) {
                            list2.add(new GraphResponse(graphRequest2, httpURLConnection, new FacebookRequestError(httpURLConnection, ex4)));
                            continue;
                        }
                        break;
                    }
                    break;
                }
                return (List<GraphResponse>)list2;
            }
            continue;
        }
    }
    
    static List<GraphResponse> createResponsesFromStream(final InputStream inputStream, final HttpURLConnection httpURLConnection, final GraphRequestBatch graphRequestBatch) throws FacebookException, JSONException, IOException {
        final String streamToString = Utility.readStreamToString(inputStream);
        Logger.log(LoggingBehavior.INCLUDE_RAW_RESPONSES, "Response", "Response (raw)\n  Size: %d\n  Response:\n%s\n", streamToString.length(), streamToString);
        return createResponsesFromString(streamToString, httpURLConnection, graphRequestBatch);
    }
    
    static List<GraphResponse> createResponsesFromString(final String s, final HttpURLConnection httpURLConnection, final GraphRequestBatch graphRequestBatch) throws FacebookException, JSONException, IOException {
        final List<GraphResponse> responsesFromObject = createResponsesFromObject(httpURLConnection, graphRequestBatch, new JSONTokener(s).nextValue());
        Logger.log(LoggingBehavior.REQUESTS, "Response", "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", graphRequestBatch.getId(), s.length(), responsesFromObject);
        return responsesFromObject;
    }
    
    static List<GraphResponse> fromHttpConnection(final HttpURLConnection httpURLConnection, final GraphRequestBatch graphRequestBatch) {
        InputStream inputStream = null;
        try {
            final int responseCode = httpURLConnection.getResponseCode();
            inputStream = null;
            if (responseCode >= 400) {
                inputStream = httpURLConnection.getErrorStream();
            }
            else {
                inputStream = httpURLConnection.getInputStream();
            }
            return createResponsesFromStream(inputStream, httpURLConnection, graphRequestBatch);
        }
        catch (FacebookException ex) {
            Logger.log(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", ex);
            return constructErrorResponses(graphRequestBatch, httpURLConnection, ex);
        }
        catch (JSONException ex2) {
            Logger.log(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", ex2);
            return constructErrorResponses(graphRequestBatch, httpURLConnection, new FacebookException((Throwable)ex2));
        }
        catch (IOException ex3) {
            Logger.log(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", ex3);
            return constructErrorResponses(graphRequestBatch, httpURLConnection, new FacebookException(ex3));
        }
        catch (SecurityException ex4) {
            Logger.log(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", ex4);
            return constructErrorResponses(graphRequestBatch, httpURLConnection, new FacebookException(ex4));
        }
        finally {
            Utility.closeQuietly(inputStream);
        }
    }
    
    public final HttpURLConnection getConnection() {
        return this.connection;
    }
    
    public final FacebookRequestError getError() {
        return this.error;
    }
    
    public final JSONArray getJSONArray() {
        return this.graphObjectArray;
    }
    
    public final JSONObject getJSONObject() {
        return this.graphObject;
    }
    
    public String getRawResponse() {
        return this.rawResponse;
    }
    
    public GraphRequest getRequest() {
        return this.request;
    }
    
    public GraphRequest getRequestForPagedResults(final PagingDirection pagingDirection) {
        final JSONObject graphObject = this.graphObject;
        String s = null;
        if (graphObject != null) {
            final JSONObject optJSONObject = this.graphObject.optJSONObject("paging");
            s = null;
            if (optJSONObject != null) {
                if (pagingDirection == PagingDirection.NEXT) {
                    s = optJSONObject.optString("next");
                }
                else {
                    s = optJSONObject.optString("previous");
                }
            }
        }
        if (Utility.isNullOrEmpty(s)) {
            return null;
        }
        if (s != null && s.equals(this.request.getUrlForSingleRequest())) {
            return null;
        }
        try {
            return new GraphRequest(this.request.getAccessToken(), new URL(s));
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    @Override
    public String toString() {
        try {
            final Locale us = Locale.US;
            final Object[] array = { null };
            int responseCode;
            if (this.connection != null) {
                responseCode = this.connection.getResponseCode();
            }
            else {
                responseCode = 200;
            }
            array[0] = responseCode;
            final String format = String.format(us, "%d", array);
            return "{Response: " + " responseCode: " + format + ", graphObject: " + this.graphObject + ", error: " + this.error + "}";
        }
        catch (IOException ex) {
            final String format = "unknown";
            return "{Response: " + " responseCode: " + format + ", graphObject: " + this.graphObject + ", error: " + this.error + "}";
        }
    }
    
    public enum PagingDirection
    {
        NEXT, 
        PREVIOUS;
    }
}

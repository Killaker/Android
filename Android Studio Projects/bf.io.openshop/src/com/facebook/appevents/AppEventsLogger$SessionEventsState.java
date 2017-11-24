package com.facebook.appevents;

import java.io.*;
import com.facebook.*;
import com.facebook.internal.*;
import android.os.*;
import org.json.*;
import java.util.*;

static class SessionEventsState
{
    public static final String ENCODED_EVENTS_KEY = "encoded_events";
    public static final String EVENT_COUNT_KEY = "event_count";
    public static final String NUM_SKIPPED_KEY = "num_skipped";
    private final int MAX_ACCUMULATED_LOG_EVENTS;
    private List<AppEvent> accumulatedEvents;
    private String anonymousAppDeviceGUID;
    private AttributionIdentifiers attributionIdentifiers;
    private List<AppEvent> inFlightEvents;
    private int numSkippedEventsDueToFullBuffer;
    private String packageName;
    
    public SessionEventsState(final AttributionIdentifiers attributionIdentifiers, final String packageName, final String anonymousAppDeviceGUID) {
        this.accumulatedEvents = new ArrayList<AppEvent>();
        this.inFlightEvents = new ArrayList<AppEvent>();
        this.MAX_ACCUMULATED_LOG_EVENTS = 1000;
        this.attributionIdentifiers = attributionIdentifiers;
        this.packageName = packageName;
        this.anonymousAppDeviceGUID = anonymousAppDeviceGUID;
    }
    
    private byte[] getStringAsByteArray(final String s) {
        try {
            return s.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            Utility.logd("Encoding exception: ", ex);
            return null;
        }
    }
    
    private void populateRequest(final GraphRequest graphRequest, final int n, final JSONArray jsonArray, final boolean b) {
        while (true) {
            try {
                final JSONObject jsonObjectForGraphAPICall = AppEventsLoggerUtility.getJSONObjectForGraphAPICall(AppEventsLoggerUtility.GraphAPIActivityType.CUSTOM_APP_EVENTS, this.attributionIdentifiers, this.anonymousAppDeviceGUID, b, AppEventsLogger.access$1100());
                if (this.numSkippedEventsDueToFullBuffer > 0) {
                    jsonObjectForGraphAPICall.put("num_skipped_events", n);
                }
                graphRequest.setGraphObject(jsonObjectForGraphAPICall);
                Bundle parameters = graphRequest.getParameters();
                if (parameters == null) {
                    parameters = new Bundle();
                }
                final String string = jsonArray.toString();
                if (string != null) {
                    parameters.putByteArray("custom_events_file", this.getStringAsByteArray(string));
                    graphRequest.setTag(string);
                }
                graphRequest.setParameters(parameters);
            }
            catch (JSONException ex) {
                final JSONObject jsonObjectForGraphAPICall = new JSONObject();
                continue;
            }
            break;
        }
    }
    
    public void accumulatePersistedEvents(final List<AppEvent> list) {
        synchronized (this) {
            this.accumulatedEvents.addAll(list);
        }
    }
    
    public void addEvent(final AppEvent appEvent) {
        synchronized (this) {
            if (this.accumulatedEvents.size() + this.inFlightEvents.size() >= 1000) {
                ++this.numSkippedEventsDueToFullBuffer;
            }
            else {
                this.accumulatedEvents.add(appEvent);
            }
        }
    }
    
    public void clearInFlightAndStats(final boolean b) {
        // monitorenter(this)
        Label_0020: {
            if (!b) {
                break Label_0020;
            }
            try {
                this.accumulatedEvents.addAll(this.inFlightEvents);
                this.inFlightEvents.clear();
                this.numSkippedEventsDueToFullBuffer = 0;
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    public int getAccumulatedEventCount() {
        synchronized (this) {
            return this.accumulatedEvents.size();
        }
    }
    
    public List<AppEvent> getEventsToPersist() {
        synchronized (this) {
            final List<AppEvent> accumulatedEvents = this.accumulatedEvents;
            this.accumulatedEvents = new ArrayList<AppEvent>();
            return accumulatedEvents;
        }
    }
    
    public int populateRequest(final GraphRequest graphRequest, final boolean b, final boolean b2) {
        final int numSkippedEventsDueToFullBuffer;
        final JSONArray jsonArray;
        synchronized (this) {
            numSkippedEventsDueToFullBuffer = this.numSkippedEventsDueToFullBuffer;
            this.inFlightEvents.addAll(this.accumulatedEvents);
            this.accumulatedEvents.clear();
            jsonArray = new JSONArray();
            for (final AppEvent appEvent : this.inFlightEvents) {
                if (b || !appEvent.getIsImplicit()) {
                    jsonArray.put((Object)appEvent.getJSONObject());
                }
            }
        }
        if (jsonArray.length() == 0) {
            // monitorexit(this)
            return 0;
        }
        // monitorexit(this)
        this.populateRequest(graphRequest, numSkippedEventsDueToFullBuffer, jsonArray, b2);
        return jsonArray.length();
    }
}

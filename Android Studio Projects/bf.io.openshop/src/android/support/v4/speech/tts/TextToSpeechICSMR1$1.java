package android.support.v4.speech.tts;

import android.speech.tts.*;

static final class TextToSpeechICSMR1$1 extends UtteranceProgressListener {
    final /* synthetic */ UtteranceProgressListenerICSMR1 val$listener;
    
    public void onDone(final String s) {
        this.val$listener.onDone(s);
    }
    
    public void onError(final String s) {
        this.val$listener.onError(s);
    }
    
    public void onStart(final String s) {
        this.val$listener.onStart(s);
    }
}
package android.support.v4.speech.tts;

import android.speech.tts.*;

static final class TextToSpeechICSMR1$2 implements TextToSpeech$OnUtteranceCompletedListener {
    final /* synthetic */ UtteranceProgressListenerICSMR1 val$listener;
    
    public void onUtteranceCompleted(final String s) {
        this.val$listener.onStart(s);
        this.val$listener.onDone(s);
    }
}
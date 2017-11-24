package bf.io.openshop.listeners;

import android.widget.*;
import android.view.*;

public class OnTouchPasswordListener implements View$OnTouchListener
{
    private int mPreviousInputType;
    private EditText passwordET;
    
    public OnTouchPasswordListener(final EditText passwordET) {
        this.passwordET = passwordET;
    }
    
    private void setInputType(final int inputType, final boolean b) {
        int selectionStart = -1;
        int selectionEnd = -1;
        if (b) {
            selectionStart = this.passwordET.getSelectionStart();
            selectionEnd = this.passwordET.getSelectionEnd();
        }
        this.passwordET.setInputType(inputType);
        if (b) {
            this.passwordET.setSelection(selectionStart, selectionEnd);
        }
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0: {
                this.mPreviousInputType = this.passwordET.getInputType();
                this.setInputType(145, true);
                break;
            }
            case 1:
            case 3: {
                this.setInputType(this.mPreviousInputType, true);
                this.mPreviousInputType = -1;
                break;
            }
        }
        return false;
    }
}

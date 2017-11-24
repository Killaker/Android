package android.support.design.widget;

import android.support.v7.widget.*;
import android.content.*;
import android.util.*;
import android.view.inputmethod.*;
import android.view.*;

public class TextInputEditText extends AppCompatEditText
{
    public TextInputEditText(final Context context) {
        super(context);
    }
    
    public TextInputEditText(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public TextInputEditText(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public InputConnection onCreateInputConnection(final EditorInfo editorInfo) {
        final InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (onCreateInputConnection != null && editorInfo.hintText == null) {
            final ViewParent parent = this.getParent();
            if (parent instanceof TextInputLayout) {
                editorInfo.hintText = ((TextInputLayout)parent).getHint();
            }
        }
        return onCreateInputConnection;
    }
}

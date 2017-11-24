package android.support.design.widget;

import android.text.*;

class TextInputLayout$1 implements TextWatcher {
    public void afterTextChanged(final Editable editable) {
        TextInputLayout.access$100(TextInputLayout.this, true);
        if (TextInputLayout.access$200(TextInputLayout.this)) {
            TextInputLayout.access$300(TextInputLayout.this, editable.length());
        }
    }
    
    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
    
    public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
}
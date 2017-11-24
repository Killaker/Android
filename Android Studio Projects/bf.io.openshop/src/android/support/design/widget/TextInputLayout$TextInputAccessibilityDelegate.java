package android.support.design.widget;

import android.support.v4.view.*;
import android.view.*;
import android.view.accessibility.*;
import android.support.v4.view.accessibility.*;
import android.text.*;

private class TextInputAccessibilityDelegate extends AccessibilityDelegateCompat
{
    @Override
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName((CharSequence)TextInputLayout.class.getSimpleName());
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setClassName(TextInputLayout.class.getSimpleName());
        final CharSequence text = TextInputLayout.access$500(TextInputLayout.this).getText();
        if (!TextUtils.isEmpty(text)) {
            accessibilityNodeInfoCompat.setText(text);
        }
        if (TextInputLayout.access$600(TextInputLayout.this) != null) {
            accessibilityNodeInfoCompat.setLabelFor((View)TextInputLayout.access$600(TextInputLayout.this));
        }
        CharSequence text2;
        if (TextInputLayout.access$400(TextInputLayout.this) != null) {
            text2 = TextInputLayout.access$400(TextInputLayout.this).getText();
        }
        else {
            text2 = null;
        }
        if (!TextUtils.isEmpty(text2)) {
            accessibilityNodeInfoCompat.setContentInvalid(true);
            accessibilityNodeInfoCompat.setError(text2);
        }
    }
    
    @Override
    public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(view, accessibilityEvent);
        final CharSequence text = TextInputLayout.access$500(TextInputLayout.this).getText();
        if (!TextUtils.isEmpty(text)) {
            accessibilityEvent.getText().add(text);
        }
    }
}

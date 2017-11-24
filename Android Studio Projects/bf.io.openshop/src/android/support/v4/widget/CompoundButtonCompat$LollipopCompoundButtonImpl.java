package android.support.v4.widget;

import android.widget.*;
import android.content.res.*;
import android.graphics.*;

static class LollipopCompoundButtonImpl extends BaseCompoundButtonCompat
{
    @Override
    public ColorStateList getButtonTintList(final CompoundButton compoundButton) {
        return CompoundButtonCompatLollipop.getButtonTintList(compoundButton);
    }
    
    @Override
    public PorterDuff$Mode getButtonTintMode(final CompoundButton compoundButton) {
        return CompoundButtonCompatLollipop.getButtonTintMode(compoundButton);
    }
    
    @Override
    public void setButtonTintList(final CompoundButton compoundButton, final ColorStateList list) {
        CompoundButtonCompatLollipop.setButtonTintList(compoundButton, list);
    }
    
    @Override
    public void setButtonTintMode(final CompoundButton compoundButton, final PorterDuff$Mode porterDuff$Mode) {
        CompoundButtonCompatLollipop.setButtonTintMode(compoundButton, porterDuff$Mode);
    }
}

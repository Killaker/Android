package android.support.graphics.drawable;

import android.util.*;
import android.content.res.*;
import org.xmlpull.v1.*;

private static class VClipPath extends VPath
{
    public VClipPath() {
    }
    
    public VClipPath(final VClipPath vClipPath) {
        super((VPath)vClipPath);
    }
    
    private void updateStateFromTypedArray(final TypedArray typedArray) {
        final String string = typedArray.getString(0);
        if (string != null) {
            this.mPathName = string;
        }
        final String string2 = typedArray.getString(1);
        if (string2 != null) {
            this.mNodes = PathParser.createNodesFromPathData(string2);
        }
    }
    
    public void inflate(final Resources resources, final AttributeSet set, final Resources$Theme resources$Theme, final XmlPullParser xmlPullParser) {
        if (!TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
            return;
        }
        final TypedArray obtainAttributes = VectorDrawableCommon.obtainAttributes(resources, resources$Theme, set, AndroidResources.styleable_VectorDrawableClipPath);
        this.updateStateFromTypedArray(obtainAttributes);
        obtainAttributes.recycle();
    }
    
    @Override
    public boolean isClipPath() {
        return true;
    }
}

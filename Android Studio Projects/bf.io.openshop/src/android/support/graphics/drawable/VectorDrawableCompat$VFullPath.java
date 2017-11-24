package android.support.graphics.drawable;

import android.graphics.*;
import org.xmlpull.v1.*;
import android.content.res.*;
import android.util.*;

private static class VFullPath extends VPath
{
    float mFillAlpha;
    int mFillColor;
    int mFillRule;
    float mStrokeAlpha;
    int mStrokeColor;
    Paint$Cap mStrokeLineCap;
    Paint$Join mStrokeLineJoin;
    float mStrokeMiterlimit;
    float mStrokeWidth;
    private int[] mThemeAttrs;
    float mTrimPathEnd;
    float mTrimPathOffset;
    float mTrimPathStart;
    
    public VFullPath() {
        this.mStrokeColor = 0;
        this.mStrokeWidth = 0.0f;
        this.mFillColor = 0;
        this.mStrokeAlpha = 1.0f;
        this.mFillAlpha = 1.0f;
        this.mTrimPathStart = 0.0f;
        this.mTrimPathEnd = 1.0f;
        this.mTrimPathOffset = 0.0f;
        this.mStrokeLineCap = Paint$Cap.BUTT;
        this.mStrokeLineJoin = Paint$Join.MITER;
        this.mStrokeMiterlimit = 4.0f;
    }
    
    public VFullPath(final VFullPath vFullPath) {
        super((VPath)vFullPath);
        this.mStrokeColor = 0;
        this.mStrokeWidth = 0.0f;
        this.mFillColor = 0;
        this.mStrokeAlpha = 1.0f;
        this.mFillAlpha = 1.0f;
        this.mTrimPathStart = 0.0f;
        this.mTrimPathEnd = 1.0f;
        this.mTrimPathOffset = 0.0f;
        this.mStrokeLineCap = Paint$Cap.BUTT;
        this.mStrokeLineJoin = Paint$Join.MITER;
        this.mStrokeMiterlimit = 4.0f;
        this.mThemeAttrs = vFullPath.mThemeAttrs;
        this.mStrokeColor = vFullPath.mStrokeColor;
        this.mStrokeWidth = vFullPath.mStrokeWidth;
        this.mStrokeAlpha = vFullPath.mStrokeAlpha;
        this.mFillColor = vFullPath.mFillColor;
        this.mFillRule = vFullPath.mFillRule;
        this.mFillAlpha = vFullPath.mFillAlpha;
        this.mTrimPathStart = vFullPath.mTrimPathStart;
        this.mTrimPathEnd = vFullPath.mTrimPathEnd;
        this.mTrimPathOffset = vFullPath.mTrimPathOffset;
        this.mStrokeLineCap = vFullPath.mStrokeLineCap;
        this.mStrokeLineJoin = vFullPath.mStrokeLineJoin;
        this.mStrokeMiterlimit = vFullPath.mStrokeMiterlimit;
    }
    
    private Paint$Cap getStrokeLineCap(final int n, final Paint$Cap paint$Cap) {
        switch (n) {
            default: {
                return paint$Cap;
            }
            case 0: {
                return Paint$Cap.BUTT;
            }
            case 1: {
                return Paint$Cap.ROUND;
            }
            case 2: {
                return Paint$Cap.SQUARE;
            }
        }
    }
    
    private Paint$Join getStrokeLineJoin(final int n, final Paint$Join paint$Join) {
        switch (n) {
            default: {
                return paint$Join;
            }
            case 0: {
                return Paint$Join.MITER;
            }
            case 1: {
                return Paint$Join.ROUND;
            }
            case 2: {
                return Paint$Join.BEVEL;
            }
        }
    }
    
    private void updateStateFromTypedArray(final TypedArray typedArray, final XmlPullParser xmlPullParser) {
        this.mThemeAttrs = null;
        if (!TypedArrayUtils.hasAttribute(xmlPullParser, "pathData")) {
            return;
        }
        final String string = typedArray.getString(0);
        if (string != null) {
            this.mPathName = string;
        }
        final String string2 = typedArray.getString(2);
        if (string2 != null) {
            this.mNodes = PathParser.createNodesFromPathData(string2);
        }
        this.mFillColor = TypedArrayUtils.getNamedColor(typedArray, xmlPullParser, "fillColor", 1, this.mFillColor);
        this.mFillAlpha = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "fillAlpha", 12, this.mFillAlpha);
        this.mStrokeLineCap = this.getStrokeLineCap(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.mStrokeLineCap);
        this.mStrokeLineJoin = this.getStrokeLineJoin(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.mStrokeLineJoin);
        this.mStrokeMiterlimit = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.mStrokeMiterlimit);
        this.mStrokeColor = TypedArrayUtils.getNamedColor(typedArray, xmlPullParser, "strokeColor", 3, this.mStrokeColor);
        this.mStrokeAlpha = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeAlpha", 11, this.mStrokeAlpha);
        this.mStrokeWidth = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "strokeWidth", 4, this.mStrokeWidth);
        this.mTrimPathEnd = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathEnd", 6, this.mTrimPathEnd);
        this.mTrimPathOffset = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathOffset", 7, this.mTrimPathOffset);
        this.mTrimPathStart = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "trimPathStart", 5, this.mTrimPathStart);
    }
    
    @Override
    public void applyTheme(final Resources$Theme resources$Theme) {
        if (this.mThemeAttrs == null) {}
    }
    
    @Override
    public boolean canApplyTheme() {
        return this.mThemeAttrs != null;
    }
    
    float getFillAlpha() {
        return this.mFillAlpha;
    }
    
    int getFillColor() {
        return this.mFillColor;
    }
    
    float getStrokeAlpha() {
        return this.mStrokeAlpha;
    }
    
    int getStrokeColor() {
        return this.mStrokeColor;
    }
    
    float getStrokeWidth() {
        return this.mStrokeWidth;
    }
    
    float getTrimPathEnd() {
        return this.mTrimPathEnd;
    }
    
    float getTrimPathOffset() {
        return this.mTrimPathOffset;
    }
    
    float getTrimPathStart() {
        return this.mTrimPathStart;
    }
    
    public void inflate(final Resources resources, final AttributeSet set, final Resources$Theme resources$Theme, final XmlPullParser xmlPullParser) {
        final TypedArray obtainAttributes = VectorDrawableCommon.obtainAttributes(resources, resources$Theme, set, AndroidResources.styleable_VectorDrawablePath);
        this.updateStateFromTypedArray(obtainAttributes, xmlPullParser);
        obtainAttributes.recycle();
    }
    
    void setFillAlpha(final float mFillAlpha) {
        this.mFillAlpha = mFillAlpha;
    }
    
    void setFillColor(final int mFillColor) {
        this.mFillColor = mFillColor;
    }
    
    void setStrokeAlpha(final float mStrokeAlpha) {
        this.mStrokeAlpha = mStrokeAlpha;
    }
    
    void setStrokeColor(final int mStrokeColor) {
        this.mStrokeColor = mStrokeColor;
    }
    
    void setStrokeWidth(final float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
    }
    
    void setTrimPathEnd(final float mTrimPathEnd) {
        this.mTrimPathEnd = mTrimPathEnd;
    }
    
    void setTrimPathOffset(final float mTrimPathOffset) {
        this.mTrimPathOffset = mTrimPathOffset;
    }
    
    void setTrimPathStart(final float mTrimPathStart) {
        this.mTrimPathStart = mTrimPathStart;
    }
}

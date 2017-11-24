package android.support.graphics.drawable;

import android.content.res.*;
import android.util.*;
import android.graphics.*;

private static class VPath
{
    int mChangingConfigurations;
    protected PathParser.PathDataNode[] mNodes;
    String mPathName;
    
    public VPath() {
        this.mNodes = null;
    }
    
    public VPath(final VPath vPath) {
        this.mNodes = null;
        this.mPathName = vPath.mPathName;
        this.mChangingConfigurations = vPath.mChangingConfigurations;
        this.mNodes = PathParser.deepCopyNodes(vPath.mNodes);
    }
    
    public String NodesToString(final PathParser.PathDataNode[] array) {
        String s = " ";
        for (int i = 0; i < array.length; ++i) {
            s = s + array[i].type + ":";
            final float[] params = array[i].params;
            for (int j = 0; j < params.length; ++j) {
                s = s + params[j] + ",";
            }
        }
        return s;
    }
    
    public void applyTheme(final Resources$Theme resources$Theme) {
    }
    
    public boolean canApplyTheme() {
        return false;
    }
    
    public PathParser.PathDataNode[] getPathData() {
        return this.mNodes;
    }
    
    public String getPathName() {
        return this.mPathName;
    }
    
    public boolean isClipPath() {
        return false;
    }
    
    public void printVPath(final int n) {
        String string = "";
        for (int i = 0; i < n; ++i) {
            string += "    ";
        }
        Log.v("VectorDrawableCompat", string + "current path is :" + this.mPathName + " pathData is " + this.NodesToString(this.mNodes));
    }
    
    public void setPathData(final PathParser.PathDataNode[] array) {
        if (!PathParser.canMorph(this.mNodes, array)) {
            this.mNodes = PathParser.deepCopyNodes(array);
            return;
        }
        PathParser.updateNodes(this.mNodes, array);
    }
    
    public void toPath(final Path path) {
        path.reset();
        if (this.mNodes != null) {
            PathParser.PathDataNode.nodesToPath(this.mNodes, path);
        }
    }
}

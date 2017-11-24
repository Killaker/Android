package android.support.v4.app;

import java.util.*;
import android.view.*;
import android.support.v4.util.*;

public class TransitionState
{
    public FragmentTransitionCompat21.EpicenterView enteringEpicenterView;
    public ArrayList<View> hiddenFragmentViews;
    public ArrayMap<String, String> nameOverrides;
    public View nonExistentView;
    
    public TransitionState() {
        this.nameOverrides = new ArrayMap<String, String>();
        this.hiddenFragmentViews = new ArrayList<View>();
        this.enteringEpicenterView = new FragmentTransitionCompat21.EpicenterView();
    }
}

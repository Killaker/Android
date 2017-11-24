package android.support.design.widget;

import android.support.v7.app.*;
import android.os.*;
import android.app.*;
import android.content.*;

public class BottomSheetDialogFragment extends AppCompatDialogFragment
{
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        return new BottomSheetDialog((Context)this.getActivity(), this.getTheme());
    }
}

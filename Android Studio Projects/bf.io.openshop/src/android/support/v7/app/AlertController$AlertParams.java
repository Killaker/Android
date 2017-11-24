package android.support.v7.app;

import android.database.*;
import android.graphics.drawable.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.os.*;

public static class AlertParams
{
    public ListAdapter mAdapter;
    public boolean mCancelable;
    public int mCheckedItem;
    public boolean[] mCheckedItems;
    public final Context mContext;
    public Cursor mCursor;
    public View mCustomTitleView;
    public boolean mForceInverseBackground;
    public Drawable mIcon;
    public int mIconAttrId;
    public int mIconId;
    public final LayoutInflater mInflater;
    public String mIsCheckedColumn;
    public boolean mIsMultiChoice;
    public boolean mIsSingleChoice;
    public CharSequence[] mItems;
    public String mLabelColumn;
    public CharSequence mMessage;
    public DialogInterface$OnClickListener mNegativeButtonListener;
    public CharSequence mNegativeButtonText;
    public DialogInterface$OnClickListener mNeutralButtonListener;
    public CharSequence mNeutralButtonText;
    public DialogInterface$OnCancelListener mOnCancelListener;
    public DialogInterface$OnMultiChoiceClickListener mOnCheckboxClickListener;
    public DialogInterface$OnClickListener mOnClickListener;
    public DialogInterface$OnDismissListener mOnDismissListener;
    public AdapterView$OnItemSelectedListener mOnItemSelectedListener;
    public DialogInterface$OnKeyListener mOnKeyListener;
    public OnPrepareListViewListener mOnPrepareListViewListener;
    public DialogInterface$OnClickListener mPositiveButtonListener;
    public CharSequence mPositiveButtonText;
    public boolean mRecycleOnMeasure;
    public CharSequence mTitle;
    public View mView;
    public int mViewLayoutResId;
    public int mViewSpacingBottom;
    public int mViewSpacingLeft;
    public int mViewSpacingRight;
    public boolean mViewSpacingSpecified;
    public int mViewSpacingTop;
    
    public AlertParams(final Context mContext) {
        this.mIconId = 0;
        this.mIconAttrId = 0;
        this.mViewSpacingSpecified = false;
        this.mCheckedItem = -1;
        this.mRecycleOnMeasure = true;
        this.mContext = mContext;
        this.mCancelable = true;
        this.mInflater = (LayoutInflater)mContext.getSystemService("layout_inflater");
    }
    
    private void createListView(final AlertController alertController) {
        final ListView listView = (ListView)this.mInflater.inflate(AlertController.access$1100(alertController), (ViewGroup)null);
        Object mAdapter;
        if (this.mIsMultiChoice) {
            if (this.mCursor == null) {
                mAdapter = new ArrayAdapter<CharSequence>(this.mContext, AlertController.access$1200(alertController), 16908308, this.mItems) {
                    public View getView(final int n, final View view, final ViewGroup viewGroup) {
                        final View view2 = super.getView(n, view, viewGroup);
                        if (AlertParams.this.mCheckedItems != null && AlertParams.this.mCheckedItems[n]) {
                            listView.setItemChecked(n, true);
                        }
                        return view2;
                    }
                };
            }
            else {
                mAdapter = new CursorAdapter(this.mContext, this.mCursor, false) {
                    private final int mIsCheckedIndex;
                    private final int mLabelIndex;
                    
                    {
                        final Cursor cursor2 = this.getCursor();
                        this.mLabelIndex = cursor2.getColumnIndexOrThrow(AlertParams.this.mLabelColumn);
                        this.mIsCheckedIndex = cursor2.getColumnIndexOrThrow(AlertParams.this.mIsCheckedColumn);
                    }
                    
                    public void bindView(final View view, final Context context, final Cursor cursor) {
                        boolean b = true;
                        ((CheckedTextView)view.findViewById(16908308)).setText((CharSequence)cursor.getString(this.mLabelIndex));
                        final ListView val$listView = listView;
                        final int position = cursor.getPosition();
                        if (cursor.getInt(this.mIsCheckedIndex) != (b ? 1 : 0)) {
                            b = false;
                        }
                        val$listView.setItemChecked(position, b);
                    }
                    
                    public View newView(final Context context, final Cursor cursor, final ViewGroup viewGroup) {
                        return AlertParams.this.mInflater.inflate(AlertController.access$1200(alertController), viewGroup, false);
                    }
                };
            }
        }
        else {
            int n;
            if (this.mIsSingleChoice) {
                n = AlertController.access$1300(alertController);
            }
            else {
                n = AlertController.access$1400(alertController);
            }
            if (this.mCursor != null) {
                mAdapter = new SimpleCursorAdapter(this.mContext, n, this.mCursor, new String[] { this.mLabelColumn }, new int[] { 16908308 });
            }
            else if (this.mAdapter != null) {
                mAdapter = this.mAdapter;
            }
            else {
                mAdapter = new CheckedItemAdapter(this.mContext, n, 16908308, this.mItems);
            }
        }
        if (this.mOnPrepareListViewListener != null) {
            this.mOnPrepareListViewListener.onPrepareListView(listView);
        }
        AlertController.access$1502(alertController, (ListAdapter)mAdapter);
        AlertController.access$1602(alertController, this.mCheckedItem);
        if (this.mOnClickListener != null) {
            listView.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    AlertParams.this.mOnClickListener.onClick((DialogInterface)AlertController.access$600(alertController), n);
                    if (!AlertParams.this.mIsSingleChoice) {
                        AlertController.access$600(alertController).dismiss();
                    }
                }
            });
        }
        else if (this.mOnCheckboxClickListener != null) {
            listView.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    if (AlertParams.this.mCheckedItems != null) {
                        AlertParams.this.mCheckedItems[n] = listView.isItemChecked(n);
                    }
                    AlertParams.this.mOnCheckboxClickListener.onClick((DialogInterface)AlertController.access$600(alertController), n, listView.isItemChecked(n));
                }
            });
        }
        if (this.mOnItemSelectedListener != null) {
            listView.setOnItemSelectedListener(this.mOnItemSelectedListener);
        }
        if (this.mIsSingleChoice) {
            listView.setChoiceMode(1);
        }
        else if (this.mIsMultiChoice) {
            listView.setChoiceMode(2);
        }
        AlertController.access$1002(alertController, listView);
    }
    
    public void apply(final AlertController alertController) {
        if (this.mCustomTitleView != null) {
            alertController.setCustomTitle(this.mCustomTitleView);
        }
        else {
            if (this.mTitle != null) {
                alertController.setTitle(this.mTitle);
            }
            if (this.mIcon != null) {
                alertController.setIcon(this.mIcon);
            }
            if (this.mIconId != 0) {
                alertController.setIcon(this.mIconId);
            }
            if (this.mIconAttrId != 0) {
                alertController.setIcon(alertController.getIconAttributeResId(this.mIconAttrId));
            }
        }
        if (this.mMessage != null) {
            alertController.setMessage(this.mMessage);
        }
        if (this.mPositiveButtonText != null) {
            alertController.setButton(-1, this.mPositiveButtonText, this.mPositiveButtonListener, null);
        }
        if (this.mNegativeButtonText != null) {
            alertController.setButton(-2, this.mNegativeButtonText, this.mNegativeButtonListener, null);
        }
        if (this.mNeutralButtonText != null) {
            alertController.setButton(-3, this.mNeutralButtonText, this.mNeutralButtonListener, null);
        }
        if (this.mItems != null || this.mCursor != null || this.mAdapter != null) {
            this.createListView(alertController);
        }
        if (this.mView != null) {
            if (!this.mViewSpacingSpecified) {
                alertController.setView(this.mView);
                return;
            }
            alertController.setView(this.mView, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
        }
        else if (this.mViewLayoutResId != 0) {
            alertController.setView(this.mViewLayoutResId);
        }
    }
    
    public interface OnPrepareListViewListener
    {
        void onPrepareListView(final ListView p0);
    }
}

package android.support.design.widget;

import android.view.*;
import android.util.*;
import android.os.*;
import android.support.v4.os.*;

protected static class SavedState extends View$BaseSavedState
{
    public static final Parcelable$Creator<SavedState> CREATOR;
    SparseArray<Parcelable> behaviorStates;
    
    static {
        CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<SavedState>)new ParcelableCompatCreatorCallbacks<SavedState>() {
            @Override
            public SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }
            
            @Override
            public SavedState[] newArray(final int n) {
                return new SavedState[n];
            }
        });
    }
    
    public SavedState(final Parcel parcel, final ClassLoader classLoader) {
        super(parcel);
        final int int1 = parcel.readInt();
        final int[] array = new int[int1];
        parcel.readIntArray(array);
        final Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
        this.behaviorStates = (SparseArray<Parcelable>)new SparseArray(int1);
        for (int i = 0; i < int1; ++i) {
            this.behaviorStates.append(array[i], (Object)parcelableArray[i]);
        }
    }
    
    public SavedState(final Parcelable parcelable) {
        super(parcelable);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        int size;
        if (this.behaviorStates != null) {
            size = this.behaviorStates.size();
        }
        else {
            size = 0;
        }
        parcel.writeInt(size);
        final int[] array = new int[size];
        final Parcelable[] array2 = new Parcelable[size];
        for (int i = 0; i < size; ++i) {
            array[i] = this.behaviorStates.keyAt(i);
            array2[i] = (Parcelable)this.behaviorStates.valueAt(i);
        }
        parcel.writeIntArray(array);
        parcel.writeParcelableArray(array2, n);
    }
}

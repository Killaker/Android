package android.support.v7.app;

import android.view.*;
import android.content.*;
import android.support.v7.view.menu.*;
import android.support.v7.appcompat.*;
import android.util.*;
import android.support.v7.view.*;
import android.content.res.*;
import android.os.*;
import android.support.v4.os.*;

private static final class PanelFeatureState
{
    int background;
    View createdPanelView;
    ViewGroup decorView;
    int featureId;
    Bundle frozenActionViewState;
    Bundle frozenMenuState;
    int gravity;
    boolean isHandled;
    boolean isOpen;
    boolean isPrepared;
    ListMenuPresenter listMenuPresenter;
    Context listPresenterContext;
    MenuBuilder menu;
    public boolean qwertyMode;
    boolean refreshDecorView;
    boolean refreshMenuContent;
    View shownPanelView;
    boolean wasLastOpen;
    int windowAnimations;
    int x;
    int y;
    
    PanelFeatureState(final int featureId) {
        this.featureId = featureId;
        this.refreshDecorView = false;
    }
    
    void applyFrozenState() {
        if (this.menu != null && this.frozenMenuState != null) {
            this.menu.restorePresenterStates(this.frozenMenuState);
            this.frozenMenuState = null;
        }
    }
    
    public void clearMenuPresenters() {
        if (this.menu != null) {
            this.menu.removeMenuPresenter(this.listMenuPresenter);
        }
        this.listMenuPresenter = null;
    }
    
    MenuView getListMenuView(final MenuPresenter.Callback callback) {
        if (this.menu == null) {
            return null;
        }
        if (this.listMenuPresenter == null) {
            (this.listMenuPresenter = new ListMenuPresenter(this.listPresenterContext, R.layout.abc_list_menu_item_layout)).setCallback(callback);
            this.menu.addMenuPresenter(this.listMenuPresenter);
        }
        return this.listMenuPresenter.getMenuView(this.decorView);
    }
    
    public boolean hasPanelItems() {
        boolean b = true;
        if (this.shownPanelView == null) {
            b = false;
        }
        else if (this.createdPanelView == null && this.listMenuPresenter.getAdapter().getCount() <= 0) {
            return false;
        }
        return b;
    }
    
    void onRestoreInstanceState(final Parcelable parcelable) {
        final SavedState savedState = (SavedState)parcelable;
        this.featureId = savedState.featureId;
        this.wasLastOpen = savedState.isOpen;
        this.frozenMenuState = savedState.menuState;
        this.shownPanelView = null;
        this.decorView = null;
    }
    
    Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState();
        savedState.featureId = this.featureId;
        savedState.isOpen = this.isOpen;
        if (this.menu != null) {
            savedState.menuState = new Bundle();
            this.menu.savePresenterStates(savedState.menuState);
        }
        return (Parcelable)savedState;
    }
    
    void setMenu(final MenuBuilder menu) {
        if (menu != this.menu) {
            if (this.menu != null) {
                this.menu.removeMenuPresenter(this.listMenuPresenter);
            }
            this.menu = menu;
            if (menu != null && this.listMenuPresenter != null) {
                menu.addMenuPresenter(this.listMenuPresenter);
            }
        }
    }
    
    void setStyle(final Context context) {
        final TypedValue typedValue = new TypedValue();
        final Resources$Theme theme = context.getResources().newTheme();
        theme.setTo(context.getTheme());
        theme.resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true);
        if (typedValue.resourceId != 0) {
            theme.applyStyle(typedValue.resourceId, true);
        }
        theme.resolveAttribute(R.attr.panelMenuListTheme, typedValue, true);
        if (typedValue.resourceId != 0) {
            theme.applyStyle(typedValue.resourceId, true);
        }
        else {
            theme.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
        }
        final ContextThemeWrapper listPresenterContext = new ContextThemeWrapper(context, 0);
        ((Context)listPresenterContext).getTheme().setTo(theme);
        this.listPresenterContext = (Context)listPresenterContext;
        final TypedArray obtainStyledAttributes = ((Context)listPresenterContext).obtainStyledAttributes(R.styleable.AppCompatTheme);
        this.background = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTheme_panelBackground, 0);
        this.windowAnimations = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
        obtainStyledAttributes.recycle();
    }
    
    private static class SavedState implements Parcelable
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        int featureId;
        boolean isOpen;
        Bundle menuState;
        
        static {
            CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<SavedState>)new ParcelableCompatCreatorCallbacks<SavedState>() {
                @Override
                public SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
                    return readFromParcel(parcel, classLoader);
                }
                
                @Override
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            });
        }
        
        private static SavedState readFromParcel(final Parcel parcel, final ClassLoader classLoader) {
            boolean isOpen = true;
            final SavedState savedState = new SavedState();
            savedState.featureId = parcel.readInt();
            if (parcel.readInt() != (isOpen ? 1 : 0)) {
                isOpen = false;
            }
            savedState.isOpen = isOpen;
            if (savedState.isOpen) {
                savedState.menuState = parcel.readBundle(classLoader);
            }
            return savedState;
        }
        
        public int describeContents() {
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            parcel.writeInt(this.featureId);
            int n2;
            if (this.isOpen) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            parcel.writeInt(n2);
            if (this.isOpen) {
                parcel.writeBundle(this.menuState);
            }
        }
    }
}

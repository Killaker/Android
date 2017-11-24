package bf.io.openshop.ux.adapters;

import android.support.v7.widget.*;
import android.widget.*;
import bf.io.openshop.interfaces.*;
import android.view.*;

public static class ViewHolder extends RecyclerView.ViewHolder
{
    View aa;
    int position;
    ImageView productImage;
    
    public ViewHolder(final View view, final ProductImagesRecyclerInterface productImagesRecyclerInterface) {
        super(view);
        this.productImage = (ImageView)view.findViewById(2131624331);
        view.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (productImagesRecyclerInterface != null) {
                    productImagesRecyclerInterface.onImageSelected(view, ViewHolder.this.position);
                }
            }
        });
    }
    
    public void setPosition(final int position) {
        this.position = position;
    }
}

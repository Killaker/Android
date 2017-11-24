package bf.io.openshop.interfaces;

import bf.io.openshop.entities.cart.*;

public interface CartRecyclerInterface
{
    void onDiscountDelete(final CartDiscountItem p0);
    
    void onProductDelete(final CartProductItem p0);
    
    void onProductSelect(final long p0);
    
    void onProductUpdate(final CartProductItem p0);
}

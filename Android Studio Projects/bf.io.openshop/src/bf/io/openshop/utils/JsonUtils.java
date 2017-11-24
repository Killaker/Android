package bf.io.openshop.utils;

import bf.io.openshop.entities.order.*;
import timber.log.*;
import org.json.*;

public class JsonUtils
{
    public static final String TAG_CITY = "city";
    public static final String TAG_CODE = "code";
    public static final String TAG_DATE_CREATED = "date_created";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_FB_ACCESS_TOKEN = "fb_access_token";
    public static final String TAG_FB_ID = "fb_id";
    public static final String TAG_GENDER = "gender";
    public static final String TAG_HOUSE_NUMBER = "house_number";
    public static final String TAG_ID = "id";
    public static final String TAG_IS_IN_WISHLIST = "is_in_wishlist";
    public static final String TAG_NAME = "name";
    public static final String TAG_NEW_PASSWORD = "new_password";
    public static final String TAG_NOTE = "note";
    public static final String TAG_OLD_PASSWORD = "old_password";
    public static final String TAG_PASSWORD = "password";
    public static final String TAG_PAYMENT_TYPE = "payment_type";
    public static final String TAG_PHONE = "phone";
    public static final String TAG_PRODUCT_COUNT = "product_count";
    public static final String TAG_PRODUCT_VARIANT_ID = "product_variant_id";
    public static final String TAG_QUANTITY = "quantity";
    public static final String TAG_REGION = "region";
    public static final String TAG_SHIPPING_NAME = "shipping_name";
    public static final String TAG_SHIPPING_PRICE_FORMATTED = "shipping_price_formatted";
    public static final String TAG_SHIPPING_TYPE = "shipping_type";
    public static final String TAG_STATUS = "status";
    public static final String TAG_STREET = "street";
    public static final String TAG_TOTAL = "total";
    public static final String TAG_TOTAL_FORMATTED = "total_formatted";
    public static final String TAG_WISHLIST_PRODUCT_ID = "wishlist_product_id";
    public static final String TAG_ZIP = "zip";
    
    public static JSONObject createOrderJson(final Order order) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("shipping_type", order.getShippingType());
        if (order.getPaymentType() == -1L) {
            jsonObject.put("payment_type", (Object)null);
        }
        else {
            jsonObject.put("payment_type", order.getPaymentType());
        }
        jsonObject.put("name", (Object)order.getName());
        jsonObject.put("street", (Object)order.getStreet());
        jsonObject.put("house_number", (Object)order.getHouseNumber());
        jsonObject.put("city", (Object)order.getCity());
        jsonObject.put("zip", (Object)order.getZip());
        jsonObject.put("email", (Object)order.getEmail());
        jsonObject.put("phone", (Object)order.getPhone());
        if (order.getNote() != null) {
            jsonObject.put("note", (Object)order.getNote());
        }
        if (order.getRegion() != null) {
            jsonObject.put("region", order.getRegion().getId());
        }
        Timber.d("JSONParser postOrder: " + jsonObject.toString(), new Object[0]);
        return jsonObject;
    }
    
    public static JSONObject createUserAuthentication(final String s, final String s2) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", (Object)s);
        jsonObject.put("password", (Object)s2);
        return jsonObject;
    }
}

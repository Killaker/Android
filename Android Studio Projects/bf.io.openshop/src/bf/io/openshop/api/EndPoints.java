package bf.io.openshop.api;

public class EndPoints
{
    public static final String API_URL = "http://77.93.198.186/v1.2/";
    public static final String BANNERS;
    public static final String BRANCHES;
    public static final String CART;
    public static final String CART_DELIVERY_INFO;
    public static final String CART_DISCOUNTS;
    public static final String CART_DISCOUNTS_SINGLE;
    public static final String CART_INFO;
    public static final String CART_ITEM;
    public static final String NAVIGATION_DRAWER;
    public static final String NOTIFICATION_IMAGE_URL = "image_url";
    public static final String NOTIFICATION_LINK = "link";
    public static final String NOTIFICATION_MESSAGE = "message";
    public static final String NOTIFICATION_SHOP_ID = "shop_id";
    public static final String NOTIFICATION_TITLE = "title";
    public static final String NOTIFICATION_UTM = "utm";
    public static final String ORDERS;
    public static final String ORDERS_SINGLE;
    public static final String PAGES_SINGLE;
    public static final String PAGES_TERMS_AND_COND;
    public static final String PRODUCTS;
    public static final String PRODUCTS_SINGLE;
    public static final String PRODUCTS_SINGLE_RELATED;
    public static final String REGISTER_NOTIFICATION;
    public static final String SHOPS;
    public static final String SHOPS_SINGLE;
    public static final String USER_CHANGE_PASSWORD;
    public static final String USER_LOGIN_EMAIL;
    public static final String USER_LOGIN_FACEBOOK;
    public static final String USER_REGISTER;
    public static final String USER_RESET_PASSWORD;
    public static final String USER_SINGLE;
    public static final String WISHLIST;
    public static final String WISHLIST_IS_IN_WISHLIST;
    public static final String WISHLIST_SINGLE;
    
    static {
        SHOPS = "http://77.93.198.186/v1.2/".concat("4/shops");
        SHOPS_SINGLE = "http://77.93.198.186/v1.2/".concat("4/shops/%d");
        NAVIGATION_DRAWER = "http://77.93.198.186/v1.2/".concat("%d/navigation_drawer");
        BANNERS = "http://77.93.198.186/v1.2/".concat("%d/banners");
        PAGES_SINGLE = "http://77.93.198.186/v1.2/".concat("%d/pages/%d");
        PAGES_TERMS_AND_COND = "http://77.93.198.186/v1.2/".concat("%d/pages/terms");
        PRODUCTS = "http://77.93.198.186/v1.2/".concat("%d/products");
        PRODUCTS_SINGLE = "http://77.93.198.186/v1.2/".concat("%d/products/%d");
        PRODUCTS_SINGLE_RELATED = "http://77.93.198.186/v1.2/".concat("%d/products/%d?include=related");
        USER_REGISTER = "http://77.93.198.186/v1.2/".concat("%d/users/register");
        USER_LOGIN_EMAIL = "http://77.93.198.186/v1.2/".concat("%d/login/email");
        USER_LOGIN_FACEBOOK = "http://77.93.198.186/v1.2/".concat("%d/login/facebook");
        USER_RESET_PASSWORD = "http://77.93.198.186/v1.2/".concat("%d/users/reset-password");
        USER_SINGLE = "http://77.93.198.186/v1.2/".concat("%d/users/%d");
        USER_CHANGE_PASSWORD = "http://77.93.198.186/v1.2/".concat("%d/users/%d/password");
        CART = "http://77.93.198.186/v1.2/".concat("%d/cart");
        CART_INFO = "http://77.93.198.186/v1.2/".concat("%d/cart/info");
        CART_ITEM = "http://77.93.198.186/v1.2/".concat("%d/cart/%d");
        CART_DELIVERY_INFO = "http://77.93.198.186/v1.2/".concat("%d/cart/delivery-info");
        CART_DISCOUNTS = "http://77.93.198.186/v1.2/".concat("%d/cart/discounts");
        CART_DISCOUNTS_SINGLE = "http://77.93.198.186/v1.2/".concat("%d/cart/discounts/%d");
        ORDERS = "http://77.93.198.186/v1.2/".concat("%d/orders");
        ORDERS_SINGLE = "http://77.93.198.186/v1.2/".concat("%d/orders/%d");
        BRANCHES = "http://77.93.198.186/v1.2/".concat("%d/branches");
        WISHLIST = "http://77.93.198.186/v1.2/".concat("%d/wishlist");
        WISHLIST_SINGLE = "http://77.93.198.186/v1.2/".concat("%d/wishlist/%d");
        WISHLIST_IS_IN_WISHLIST = "http://77.93.198.186/v1.2/".concat("%d/wishlist/is-in-wishlist/%d");
        REGISTER_NOTIFICATION = "http://77.93.198.186/v1.2/".concat("%d/devices");
    }
}

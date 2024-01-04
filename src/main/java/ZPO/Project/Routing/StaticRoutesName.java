package ZPO.Project.Routing;

public class StaticRoutesName {
    public static final String HOME = "/";
    public static final String PAGE_1 = "/page1";
    public static final String PAGE_2 = "/page2";
    public static final String PAGE_3 = "/page3";
    public static final String BEE_SYRUP_CALCULATOR = "/bee-syrup-calculator";
    public static final String SHOP = "/shop";
    public static final String FORUM = "/forum";
    public static final String CART = "/cart";
    public static final String ORDER = "/order";
    public static final String PAYMENT = "/payment/{orderId}";
    public static final String FINALIZE_ORDER = "/finalize-order/{orderId}/";
    public static final String GOOD = "good";
    public static final String BAD = "bad";
    public static String GetPaymentURL(Long id){
        return PAYMENT.replace("{orderId}",id.toString());
    }
    public static String GetFinalizeURL(Long id){
        return FINALIZE_ORDER.replace("{orderId}",id.toString());
    }
    public StaticRoutesName() {
    }
}

package ZPO.Project.Routing;

import org.springframework.stereotype.Component;

@Component
public class APIRoutesName {

    public static final String PREFIX = "/api";
    public static final String ADD = "/add";
    public static final String GET = "/get";
    public static final String CART = "/cart";
    public static final String CALCULATE = "/calculate";
    public static final String OFFER = "/offer";
    public static final String ANSWER = "/answer/";
    public static final String QUESTION = "/question/";
    public static final String ORDER = "/order";

    public static final String PATHPARAM_QUESTIONID = "{questionId}";

    public static String GetCALCULATE(){
        return PREFIX+CALCULATE;
    }
    public static String GetOFFER(){
        return PREFIX+OFFER;
    }
    public static String GetANSWER(){
        return PREFIX+ANSWER;
    }
    public static String GetCART(){
        return PREFIX+CART;
    }
    public static String GetANSWER_ADD(){
        return PREFIX+ANSWER+PATHPARAM_QUESTIONID;
    }
    public static String GetQUESTION_ADD(){
        return PREFIX+ QUESTION;
    }
    public static String GetORDER_CREATE(){
        return PREFIX+ ORDER;
    }
    public static String GetFullPath(String path) {
        return PREFIX+path;
    }
}

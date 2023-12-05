package ZPO.Project.Routing;

import org.springframework.stereotype.Component;

@Component
public class APIRoutesName {

    public static final String PREFIX = "/api";
    public static final String ADD = "/add";
    public static final String GET = "/get";
    public static final String CART_API = "/cart_api";
    public static final String CALCULATE = "/calculate";
    public static final String OFFER = "/offer";
    public static final String ANSWER = "/answer/";
    public static final String ANSWER_ADD = "/answer/add/";
    public static final String QUESTION_ADD = "/question/add/";
    public static final String ORDER_CREATE = "/order/create";

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
    public static String GetANSWER_ADD(){
        return PREFIX+ANSWER_ADD+PATHPARAM_QUESTIONID;
    }
    public static String GetQUESTION_ADD(){
        return PREFIX+QUESTION_ADD;
    }
    public static String GetCART_ADD(){
        return CART_API+ADD;
    }
    public static String GetCART_GET(){
        return CART_API+GET;
    }
    public static String GetORDER_CREATE(){
        return PREFIX+ORDER_CREATE;
    }
    public static String GetFullPath(String path) {
        return PREFIX+path;
    }
}

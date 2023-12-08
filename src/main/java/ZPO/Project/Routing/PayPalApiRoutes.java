package ZPO.Project.Routing;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.io.Serializable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
public class PayPalApiRoutes implements Serializable {
    public static final String PREFIX = "/api-paypal";
    public static final String SEPARATOR = "/";
    public static final String CREATE_ORDER = "/CreateOrder/";
    public static final String APPROVE_ORDER = "/ApproveOrder/";
    public static final String PP_ZamId = "{ZamId}";
    public static final String PP_PayPalId = "{PayPalId}";
    public static final String GetCreateOrder(String ZamId){
        return PREFIX + CREATE_ORDER + ZamId;
    }
    public static final String GetApproveOrder(String ZamId, String PayPalId){
        return PREFIX + APPROVE_ORDER + ZamId + "/" + PayPalId;
    }
    public static final String GetApproveOrder(String ZamId){
        return PREFIX + APPROVE_ORDER + ZamId + SEPARATOR + PP_PayPalId;
    }


    public final String PP_ZamId_JS = PP_ZamId;
    public final String PP_PayPalId_JS = PP_PayPalId;
}

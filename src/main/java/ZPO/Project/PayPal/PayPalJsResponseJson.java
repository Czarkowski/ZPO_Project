package ZPO.Project.PayPal;

import java.io.Serializable;

public class PayPalJsResponseJson implements Serializable {
    public String redirectUrl;
    public String orderId;
    public boolean statusOk;
    public String exceptionName;
    public PayPalExceptionJson payPalExceptionJson;
}

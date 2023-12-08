package ZPO.Project.Models;

import java.io.Serializable;

public class PaymentStatusModel implements Serializable {
    public String Status;
    public Long OrderId;
    public String PayPalId;
    public String ReturnUrl;
    public boolean IsOk;
}

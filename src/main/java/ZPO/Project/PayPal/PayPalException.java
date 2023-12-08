package ZPO.Project.PayPal;

public class PayPalException extends Exception{
    public PayPalExceptionJson PayPalExceptionJson;
    public PayPalException(PayPalExceptionJson payPalExceptionJSON)
    {
        super(PayPalUtilities.SerializeObject(payPalExceptionJSON));
        PayPalExceptionJson = payPalExceptionJSON;
    }
}

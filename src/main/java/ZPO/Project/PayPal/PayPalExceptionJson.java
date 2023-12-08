package ZPO.Project.PayPal;

import java.io.Serializable;
import java.util.List;

public class PayPalExceptionJson implements Serializable {
    public String name;
    public String message;
    public String debug_id;
    public List<PayPalExceptionDetails> details;
    public List<Link> links;

    public static class PayPalExceptionDetails implements Serializable
    {
        public String field;
        public String value;
        public String location;
        public String issue;
        public String description;
    }

    public static class Link implements Serializable{
        public String href;
        public String rel;
        public String method;
        public String encType;
    }
}

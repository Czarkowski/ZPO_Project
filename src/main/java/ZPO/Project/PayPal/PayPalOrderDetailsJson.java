package ZPO.Project.PayPal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class PayPalOrderDetailsJson implements Serializable {

    public String id;
    public String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String intent;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String create_time;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String update_time;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public PayPalPaymentSource payment_source;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<PayPalPurchaseUnit> purchase_units;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public PayPalOrderDetailsPayer payer;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Link> links;

    public static class Link {
        public String href;
        public String rel;
        public String method;
    }

    public static class PayPalOrderDetailsPayer {
        public PayPalOrderDetailsPayerName name;
        public String email_address;
        public String payer_id;
        public PayPalOrderDetailsPayerAddress address;

        public static class PayPalOrderDetailsPayerName {
            public String given_name;
            public String surname;
        }

        public static class PayPalOrderDetailsPayerAddress {
            public String country_code;
        }
    }

    public static class PayPalPurchaseUnit {
        public String reference_id;
        public PayPalAmount amount;
        public List<PayPalItem> items;
        public PayPalShipping shipping;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public Payee payee;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public Payments payments;

        public static class PayPalAmount {
            public String currency_code;
            public String value;
            public PayPalAmountBreakdown breakdown;

            public static class PayPalAmountBreakdown {
                public PayPalAmountBreakdownItemTotal item_total;

                public static class PayPalAmountBreakdownItemTotal {
                    public String currency_code;
                    public String value;
                }
            }
        }

        public static class PayPalItem {
            public String name;
            public String description;
            public String quantity;
            public PayPalUnitAmount unit_amount;

            public static class PayPalUnitAmount {
                public String currency_code;
                public String value;
            }
        }

        public static class PayPalShipping {
            public String type;
            public PayPalShippingName name;
            public PayPalShippingAddress address;

            public static class PayPalShippingName {
                public String full_name;
                public PayPalShippingName(){}

            }

            public static class PayPalShippingAddress {
                public String address_line_1;
                public String address_line_2;
                public String admin_area_1;
                public String admin_area_2;
                public String postal_code;
                public String country_code;
            }
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public String soft_descriptor;
    }

    public static class Payments {
        public List<Captures> captures;

        public static class Captures {
            public String id;
            public String status;
            public boolean final_capture;
            public String create_time;
            public String update_time;
            public SellerProtection seller_protection;
            public SellerReceivableBreakdown seller_receivable_breakdown;
            public List<Link> links;
            public Amount amount;

            public static class SellerProtection {
                public String status;
                public List<String> dispute_categories;
            }

            public static class SellerReceivableBreakdown {
                public Amount gross_amount;
                public Amount paypal_fee;
                public Amount net_amount;
                public Amount receivable_amount;
                public ExchangeRate exchange_rate;

                public static class ExchangeRate {
                    public String source_currency;
                    public String target_currency;
                    public String value;
                }
            }
        }
    }

    public static class PayPalPaymentSource {
        public PayPal paypal;

        public static class PayPal {
            public String email_address;
            public String account_id;
            public String account_status;
            public Name name;
            public PhoneNumber phone_number;
            public Address address;
            public Attributes attributes;

            public static class Name {
                public String given_name;
                public String surname;
            }

            public static class PhoneNumber {
                public String national_number;
            }

            public static class Address {
                public String country_code;
            }

            public static class Attributes {
                public List<CobrandedCards> cobranded_cards;

                public static class CobrandedCards {
                    public List<String> labels;
                    public Payee payee;
                    public Amount amount;
                }
            }
        }
    }

    public static class Amount {
        public String currency_code;
        public String value;
    }

    public static class Payee {
        public String name;
        public String email_address;
        public String merchant_id;
        public DisplayData display_data;

        public static class DisplayData {
            public String brand_name;
        }
    }
}
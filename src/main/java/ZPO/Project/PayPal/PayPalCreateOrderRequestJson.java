package ZPO.Project.PayPal;

import ZPO.Project.Entities.Pozycja;
import ZPO.Project.Entities.Zamowienie;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PayPalCreateOrderRequestJson implements Serializable
{
        @JsonIgnore
        public PayPalConfigurationInfo Config;
//        public static PayPalCreateOrderRequestJson GetTest()
//        {
//            PayPalCreateOrderRequestJson ret = new PayPalCreateOrderRequestJson();
//            ret.intent = "CAPTURE";
//            ret.purchase_units = new List<PayPalPurchaseUnit>();
//            var purchaseUnit = new PayPalPurchaseUnit();
//            purchaseUnit.reference_id = "d9f80740-38f0-11e8-b467-0ed5f89f718b";
//            purchaseUnit.amount = new PayPalPurchaseUnit.PayPalAmount();
//            purchaseUnit.amount.currency_code = "USD";
//            purchaseUnit.amount.value = "100.00";
//
//            ret.purchase_units.Add(purchaseUnit);
//            ret.payment_source = new PayPalPaymentSource();
//            ret.payment_source.paypal = new PayPalPaymentSource.PayPalPaymentSourcePayPal();
//            ret.payment_source.paypal.experience_context = new PayPalPaymentSource.PayPalPaymentSourcePayPal.PayPalPaymentSourcePayPalExperienceContext();
//            ret.payment_source.paypal.experience_context.payment_method_preference = "IMMEDIATE_PAYMENT_REQUIRED";
//            ret.payment_source.paypal.experience_context.brand_name = "EXAMPLE INC";
//            ret.payment_source.paypal.experience_context.locale = "en-US";
//            ret.payment_source.paypal.experience_context.landing_page = "LOGIN";
//            ret.payment_source.paypal.experience_context.user_action = "PAY_NOW";
//            ret.payment_source.paypal.experience_context.return_url = "https://example.com/returnUrl";
//            ret.payment_source.paypal.experience_context.cancel_url = "https://example.com/cancelUrl";
//
//            return ret;
//        }
        public PayPalCreateOrderRequestJson()
        {
        }

        public PayPalCreateOrderRequestJson(Zamowienie orders, PayPalConfigurationInfo config,
                                            String returnUrl, String cancleUrl)
        {
            Config = config;
            intent = "CAPTURE";
            var list = new ArrayList<PayPalPurchaseUnit>();
            list.add(new PayPalPurchaseUnit(orders, config));
            purchase_units = list;
            payment_source = new PayPalPaymentSource(config, returnUrl, cancleUrl);
        }

        public String intent;
        public List<PayPalPurchaseUnit> purchase_units;
        //Ekwiwalent paczki
        public class PayPalPurchaseUnit implements Serializable
        {
            public PayPalPurchaseUnit() { }
            public PayPalPurchaseUnit(Zamowienie orders, PayPalConfigurationInfo config)
            {
                amount = new PayPalAmount(orders, config);

                reference_id = orders.getId().toString();
                items = new ArrayList<PayPalItem>();
                orders.getPozycje().forEach(x -> items.add(new PayPalItem(x, config)));

                shipping = new PayPalShipping(orders, config);

            }
            public String reference_id;
            public PayPalAmount amount;
            public class PayPalAmount implements Serializable
            {
                public PayPalAmount()
                {
                }
                public PayPalAmount(Zamowienie orders, PayPalConfigurationInfo config)
                {
                    value = String.valueOf(orders.getPozycje().stream().map(x -> x.getCenaValue().multiply(BigDecimal.valueOf(x.getIlosc()))).reduce(BigDecimal.ZERO, BigDecimal::add));
                    currency_code = config.CurrencyCode;
                    breakdown = new PayPalAmountBreakdown(this);
                }

                public String currency_code;
                public String value;
                public PayPalAmountBreakdown breakdown;
                public class PayPalAmountBreakdown implements Serializable
                {
                    public PayPalAmountBreakdown(PayPalAmount a)
                    {
                        item_total = new PayPalAmountBreakdownItemTotal();
                        item_total.value = a.value;
                        item_total.currency_code = a.currency_code;
                    }

                    public PayPalAmountBreakdownItemTotal item_total;
                    public class PayPalAmountBreakdownItemTotal implements Serializable
                    {
                        public String currency_code;
                        public String value;
                    }
                }
            }
            public List<PayPalItem> items;
            //Ekwiwalent zamowienia
            public class PayPalItem implements Serializable
            {
                public PayPalItem(Pozycja item, PayPalConfigurationInfo config)
                {
                    name = item.getId().toString();
                    description = item.getName().length() >= 128 ? item.getName().substring(0,127) : item.getName();
                    quantity =  String.valueOf(item.getIlosc());
                    unit_amount = new PayPalUnitAmount();
                    unit_amount.value = String.valueOf(item.getCenaValue());
                    unit_amount.currency_code = config.CurrencyCode;
                }

                public String name;
                public String description;
                public String quantity;
                public PayPalUnitAmount unit_amount;
                public class PayPalUnitAmount implements Serializable
                {
                    public String currency_code;
                    public String value;
                }
            }
            public PayPalShipping shipping;
            public class PayPalShipping implements Serializable
            {
                public PayPalShipping(Zamowienie order, PayPalConfigurationInfo config)
                {
                    type = "SHIPPING";
                    name = new PayPalShippingName(order.getImie() + " " + order.getNazwisko());
                    address = new PayPalShippingAddress(order, config.CountryCode);
                }

                public String type;
                public PayPalShippingName name;
                public class PayPalShippingName implements Serializable
                {
                    public PayPalShippingName(String name)
                    {
                        full_name = name;
                    }
                    public String full_name;
                }
                public PayPalShippingAddress address;
                public class PayPalShippingAddress implements Serializable
                {
                    public PayPalShippingAddress(Zamowienie order, String countyCode)
                    {
                        address_line_1 = order.getUlica();
                        admin_area_2 = order.getMiejscowosc();
                        postal_code = order.getKodPocztowy();
                        country_code = countyCode;
                    }

                    public String address_line_1;
                    public String address_line_2;
                    public String admin_area_1;
                    public String admin_area_2;
                    public String postal_code;
                    public String country_code;
                }
            }
        }

        public PayPalPaymentSource payment_source;
        public class PayPalPaymentSource implements Serializable
        {
            public PayPalPaymentSource() { }
            public PayPalPaymentSource(PayPalConfigurationInfo config, String returnUrl, String cancleUrl)
            {
                paypal = new PayPalPaymentSourcePayPal(config, returnUrl, cancleUrl);
            }
            public PayPalPaymentSourcePayPal paypal;
            public class PayPalPaymentSourcePayPal implements Serializable
            {
                public PayPalPaymentSourcePayPal() { }
                public PayPalPaymentSourcePayPal(PayPalConfigurationInfo config, String returnUrl, String cancleUrl)
                {
                    experience_context = new PayPalPaymentSourcePayPalExperienceContext(config, returnUrl, cancleUrl);
                }
                public PayPalPaymentSourcePayPalExperienceContext experience_context;
                public class PayPalPaymentSourcePayPalExperienceContext implements Serializable
                {
                    public PayPalPaymentSourcePayPalExperienceContext() { }
                    public PayPalPaymentSourcePayPalExperienceContext(PayPalConfigurationInfo config, String returnUrl, String cancleUrl)
                    {
                        payment_method_preference = "IMMEDIATE_PAYMENT_REQUIRED";
                        brand_name = "Example INC";
                        locale = config.Locale;
                        landing_page = "LOGIN";
                        user_action = "PAY_NOW";
                        return_url = returnUrl;
                        cancel_url = cancleUrl;
                        shipping_preference = "SET_PROVIDED_ADDRESS";
                    }
                    public String payment_method_preference;
                    public String brand_name;
                    public String locale;
                    public String landing_page ;
                    public String shipping_preference;
                    public String user_action;
                    public String return_url;
                    public String cancel_url;
                }
            }
        }

    }




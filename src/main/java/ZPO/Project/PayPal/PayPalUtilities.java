package ZPO.Project.PayPal;

import ZPO.Project.Entities.Platnosc;
import ZPO.Project.Entities.Zamowienie;
import ZPO.Project.Services.PasiekaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PayPalUtilities {

    @Autowired
    private PasiekaService pasiekaService;

    public PayPalUtilities() {
    }

    public static String CreateOrder(Zamowienie order, PayPalConfigurationInfo config,
                                     String returnUrl, String cancelUrl) throws Exception {
        String apiUrl = config.APIServer;
        String authUserPass = config.ClientId + ":" + config.Secret;
        String authBase = "Basic " + java.util.Base64.getEncoder().encodeToString(authUserPass.getBytes());

        PayPalCreateOrderRequestJson payPalCreateOrderBody = new PayPalCreateOrderRequestJson(order, config, null, null);

        URL url = new URL(apiUrl + "/checkout/orders");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Authorization", authBase);
        httpURLConnection.setDoOutput(true);

        String body = SerializeObject(payPalCreateOrderBody);
        System.out.println(body);
        setBody(httpURLConnection, body);
        String response = getBody(httpURLConnection);
        return response;

    }

    public static String GetOrderDetails(String orderId, PayPalConfigurationInfo config) throws Exception {
        String apiUrl = config.APIServer;
        String authUserPass = config.ClientId + ":" + config.Secret;
        String authBase = "Basic " + java.util.Base64.getEncoder().encodeToString(authUserPass.getBytes());

        URL url = new URL(apiUrl + "/checkout/orders/" + orderId);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Authorization", authBase);

        String response = getBody(httpURLConnection);
        return response;
    }

    public void SaveNewPayPalPayment(PayPalOrderDetailsJson payPalOrderDetailsJson, Long orderId) {
//        Zamowienie zamowienie = pasiekaService.GetZamowieniebyId(Long.valueOf(payPalOrderDetailsJson.purchase_units.getFirst().reference_id));
        Zamowienie zamowienie = pasiekaService.GetZamowieniebyId(orderId);
        Platnosc platnosc = new Platnosc(zamowienie, payPalOrderDetailsJson);
        pasiekaService.SavePlatnosc(platnosc);
    }

    public void UpdatePayPalPayment(PayPalOrderDetailsJson payPalOrderDetailsJson) {
        Platnosc platnosc = pasiekaService.GetPlatnoscByPayPalId(payPalOrderDetailsJson.id);
        platnosc.UpdateStan(payPalOrderDetailsJson);
        pasiekaService.SavePlatnosc(platnosc);
    }

    public static String ApproveOrder(String orderId, PayPalConfigurationInfo config) throws Exception {
        String apiUrl = config.APIServer;
        String authUserPass = config.ClientId + ":" + config.Secret;
        String authBase = "Basic " + java.util.Base64.getEncoder().encodeToString(authUserPass.getBytes());

        URL url = new URL(apiUrl + "/checkout/orders/" + orderId + "/capture");
        System.out.println(url.toString());
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Authorization", authBase);
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoOutput(true);

        String response = getBody(httpURLConnection);

        return response;
    }

    public static boolean isCompleted(Long orderPackId, PayPalOrderDetailsJson detailsJson) {
        return detailsJson.status.equals("COMPLETED")
                && detailsJson.purchase_units.get(0).reference_id.equals(String.valueOf(orderPackId));
    }

    public static boolean CheckFinalizePayment(Long orderPackId, String payPalOrderId, PayPalConfigurationInfo config) {
        try {
            if (payPalOrderId == null || payPalOrderId.isEmpty()) {
                return false;
            }
            String response = GetOrderDetails(payPalOrderId, config);
            PayPalOrderDetailsJson payPalOrderDetails = GetObiectFromJsonString(response, PayPalOrderDetailsJson.class);
            return isCompleted(orderPackId, payPalOrderDetails);
        } catch (Exception e) {
            return false;
        }
    }

    public static <T> T GetObiectFromJsonString(String jsonString, Class<T> clazz) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, clazz);

    }

//    public static boolean isPayPalWebException(WebException webEx, PayPalExceptionJson payPalExceptionJson) {
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(webEx.getResponse().getInputStream()))) {
//            StringBuilder errorResponse = new StringBuilder();
//            String line;
//            while ((line = br.readLine()) != null) {
//                errorResponse.append(line);
//            }
//
//            payPalExceptionJson = getObiectFromJsonString(errorResponse.toString(), PayPalExceptionJson.class, false);
//            return payPalExceptionJson != null && payPalExceptionJson.getName() != null && payPalExceptionJson.getDebug_id() != null;
//        } catch (IOException e) {
//            handleException(e);
//        }
//
//        return false;
//    }

    private static void handleException(Exception e) {
        e.printStackTrace();  // Handle exception as needed
    }

    private static void setBody(HttpURLConnection httpURLConnection, String body) throws IOException {
        try (OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = body.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
    }

    private static String getBody(HttpURLConnection httpURLConnection) throws Exception {

        int responseCode = httpURLConnection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        if (responseCode >= HttpURLConnection.HTTP_OK && responseCode <= 300) {
            // Odczytaj odpowiedź, jeśli jest pozytywna (HTTP 200 OK)
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            System.out.println("Response Body: " + response.toString());
            return response.toString();
        } else {
            // Odczytaj informacje o błędzie, jeśli odpowiedź jest inna niż HTTP 200 OK
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
            String errorLine;
            StringBuilder errorResponse = new StringBuilder();

            while ((errorLine = errorReader.readLine()) != null) {
                errorResponse.append(errorLine);
            }
            errorReader.close();
            System.out.println("Error Response: " + errorResponse.toString());
            try {
                var expJson = GetObiectFromJsonString(errorResponse.toString(), PayPalExceptionJson.class);
                if (expJson != null)
                    throw new PayPalException(expJson);
                return null;
            } catch (PayPalException ppe) {
                throw ppe;
            }
            catch (Exception e) {
                throw new Exception(e.getMessage() + errorResponse.toString());
            }
        }
    }
    public static String SerializeObject(Serializable object) {
        try {
            // Inicjalizujemy obiekt ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            // Konwertujemy obiekt na JSON
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }
}



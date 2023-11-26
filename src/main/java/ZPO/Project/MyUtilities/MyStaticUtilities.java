package ZPO.Project.MyUtilities;

import ZPO.Project.ApiModels.CalculationRequest;
import ZPO.Project.ApiModels.CalculationResponse;
import ZPO.Project.Enumes.EnumSugarWaterRatio;
import ZPO.Project.Enumes.EnumSyrupBaseElement;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.http.ResponseEntity;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

public class MyStaticUtilities {
    public static String GetJsonString(Object[] enumeration) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Object value : enumeration) {
            jsonArrayBuilder.add(value.toString());
        }
        JsonArray jsonArray = jsonArrayBuilder.build();
        return jsonArray.toString();
    }

    public static CalculationResponse GetCalculationResponse(CalculationRequest calculationRequest) {
        double fraction, multiplier;
        switch (EnumSugarWaterRatio.valueOf(calculationRequest.getRatio())) {
            case JedenDoJeden -> {
                fraction = 1.;
                multiplier = 1.6;
            }
            case TrzyDoDwoch -> {
                fraction = 2. / 3;
                multiplier = 2.;
            }
            case DwaDoJeden -> {
                fraction = 1. / 2;
                multiplier = 2.25;
            }
            default -> {
                fraction = 0.;
                multiplier = 0.;
            }
        }
        double w = 0, c = 0, s = 0;
        CalculationResponse response = new CalculationResponse();
        try {
            switch (EnumSyrupBaseElement.valueOf(calculationRequest.getBase())) {
                case Syrop -> {
                    s = calculationRequest.getAmount();
                    w = s / multiplier;
                    c = w / fraction;
                }
                case Cukier -> {
                    c = calculationRequest.getAmount();
                    w = c * fraction;
                    s = w * multiplier;
                }
            }
        } catch (MatchException e) {
            c = 0;
            w = 0;
            s = 0;
        }finally {
            response.setWater(w);
            response.setSyrup(s);
            response.setSugar(c);
        }
        return response;
    }
}

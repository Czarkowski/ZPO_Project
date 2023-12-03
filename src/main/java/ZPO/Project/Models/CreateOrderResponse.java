package ZPO.Project.Models;

import ZPO.Project.Entities.Cena;
import ZPO.Project.Entities.Oferta;
import ZPO.Project.Entities.Opis;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateOrderResponse implements Serializable {

    private String redirectUrl;

    public CreateOrderResponse(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
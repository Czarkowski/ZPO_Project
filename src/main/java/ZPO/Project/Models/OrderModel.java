package ZPO.Project.Models;

import ZPO.Project.Entities.Pytanie;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderModel {

    private String name;
    private String surname;
    private String email;
    private String street;
    private String city;
    private String postalCode;
    private String phoneNumber;

}

package ZPO.Project.ApiModels;

import lombok.Data;

@Data
public class CalculationRequest {
    private String base;
    private String ratio;
    private double amount;
}

package uz.pdp.eticket1.distance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DistanceRequestDTO {
    private String adminId;
    private String station1;
    private String station2;
    private double km;
}

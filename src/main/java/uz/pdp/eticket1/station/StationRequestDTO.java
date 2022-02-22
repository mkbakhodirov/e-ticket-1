package uz.pdp.eticket1.station;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.eticket1.base.BaseModel;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StationRequestDTO {
    private String station1;
    private String station2;
    private double km;
}

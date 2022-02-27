package uz.pdp.eticket1.station;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class StationRequestDTO {
    private String adminId;
    private String name;
    private String city;
}

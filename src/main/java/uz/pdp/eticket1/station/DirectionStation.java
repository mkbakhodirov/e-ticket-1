package uz.pdp.eticket1.station;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DirectionStation {
    private String id;
    private String name;
    private String city;
    private double km;
}

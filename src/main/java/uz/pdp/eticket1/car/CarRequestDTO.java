package uz.pdp.eticket1.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarRequestDTO {
    private String adminId;
    private String type;
    private short totalSeats;
}

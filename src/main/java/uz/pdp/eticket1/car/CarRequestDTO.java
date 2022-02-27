package uz.pdp.eticket1.car;

import lombok.Data;

@Data
public class CarRequestDTO {
    private String adminId;
    private String type;
    private short totalSeats;
}

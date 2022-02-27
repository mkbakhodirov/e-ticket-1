package uz.pdp.eticket1.direction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Schedule {
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}

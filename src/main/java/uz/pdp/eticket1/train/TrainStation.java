package uz.pdp.eticket1.train;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.eticket1.car.DirectionTrainCar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrainStation {
    private LocalDateTime arrivalDateTime;
    private LocalDateTime departureDateTime;
    private Map<String, Short> availableSeats = new HashMap<>(); // carId, available seats
    private List<DirectionTrainCar> cars = new ArrayList<>();
}

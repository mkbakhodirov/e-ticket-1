package uz.pdp.eticket1.train;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.eticket1.car.Car;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrainResponseDTO {
    private String number;
    private String type;
    private List<Car> cars = new ArrayList<>();
    private List<TrainStation> stationsTime;
    private Map<String, Short> availableSeats = new HashMap<>();
}

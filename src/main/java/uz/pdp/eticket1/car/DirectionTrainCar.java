package uz.pdp.eticket1.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DirectionTrainCar {
    private TrainCar carInfo;
    private Map<Short, Byte> seatsStatus = new HashMap<>(); // seatNumber, status
}

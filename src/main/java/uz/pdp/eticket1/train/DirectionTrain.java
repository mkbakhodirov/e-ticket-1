package uz.pdp.eticket1.train;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.eticket1.car.Car;
import uz.pdp.eticket1.car.DirectionTrainCar;
import uz.pdp.eticket1.direction.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DirectionTrain {
    private String id;
    private String number;
    private String type;
    private List<TrainStation> stations = new ArrayList<>();
}

package uz.pdp.eticket1.direction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.eticket1.station.StationRequestDTO;
import uz.pdp.eticket1.train.TrainStation;

import java.util.List;

@Data
public class DirectionRequestDTO {
    private String adminId;
    private List<StationRequestDTO> stations;
}

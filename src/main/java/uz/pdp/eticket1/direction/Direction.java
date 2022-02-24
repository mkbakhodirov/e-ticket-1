package uz.pdp.eticket1.direction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.pdp.eticket1.base.BaseModel;
import uz.pdp.eticket1.station.DirectionStationResponseDTO;
import uz.pdp.eticket1.train.TrainResponseDTO;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "directions")
public class Direction extends BaseModel {
    private List<DirectionStationResponseDTO> stations;
    private List<TrainResponseDTO> trains;
}

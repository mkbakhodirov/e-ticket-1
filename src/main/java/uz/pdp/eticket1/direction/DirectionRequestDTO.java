package uz.pdp.eticket1.direction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.eticket1.station.StationRequestDTO;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DirectionRequestDTO {
    private String trainId;
    private String trainNumber;
    private List<StationRequestDTO> stationsId;
}

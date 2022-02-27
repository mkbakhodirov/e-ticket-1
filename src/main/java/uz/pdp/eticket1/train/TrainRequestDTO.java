package uz.pdp.eticket1.train;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class TrainRequestDTO {
    private String adminId;
    private String type;
    private List<String> cars;
}

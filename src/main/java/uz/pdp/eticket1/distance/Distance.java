package uz.pdp.eticket1.distance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.pdp.eticket1.base.BaseModel;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "distances")
public class Distance extends BaseModel {
    private String fromStationId;
    private String toStationId;
    private double km;
}

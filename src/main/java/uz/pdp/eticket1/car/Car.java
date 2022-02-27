package uz.pdp.eticket1.car;

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
@Document(collection = "cars")
public class Car extends BaseModel {
    private String type;
    private short totalSeats;
}

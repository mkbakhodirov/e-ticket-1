package uz.pdp.eticket1.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.pdp.eticket1.base.BaseModel;
import uz.pdp.eticket1.user.AdminResponseDTO;
import uz.pdp.eticket1.user.User;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "cars")
public class Car extends BaseModel {
//    @Id
//    private String _id;
    private String type;
    private short totalSeats;
//    private AdminResponseDTO updateBy;
}

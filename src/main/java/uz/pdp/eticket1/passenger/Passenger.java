package uz.pdp.eticket1.passenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.pdp.eticket1.base.BaseModel;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "passengers")
public class Passenger extends BaseModel {
    String passengerType;
    String firstName;
    String lastName;
    String patronymic;
    String gender;
    String documentType;
    String documentNumber;
    String country;
    LocalDate birthDate;
}

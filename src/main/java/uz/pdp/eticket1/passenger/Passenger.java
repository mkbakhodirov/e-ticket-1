package uz.pdp.eticket1.passenger;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.pdp.eticket1.base.BaseModel;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "passengers")
public class Passenger extends BaseModel {
    private String userId;
    private String passengerType;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String gender;
    private String documentType;
    private String documentNumber;
    private String country;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}

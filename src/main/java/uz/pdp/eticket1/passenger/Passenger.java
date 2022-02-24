package uz.pdp.eticket1.passenger;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Passenger {
    String passengerType;
    String firstName;
    String lastName;
    String patronymic;
    String gender;
    String documentType;
    String documentNumber;
    String country;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate birthDate;
}

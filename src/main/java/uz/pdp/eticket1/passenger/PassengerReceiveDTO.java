package uz.pdp.eticket1.passenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PassengerReceiveDTO {
    String userId;
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

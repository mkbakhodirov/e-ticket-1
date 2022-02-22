package uz.pdp.eticket1.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.pdp.eticket1.ticket.Ticket;
import uz.pdp.eticket1.base.BaseModel;
import uz.pdp.eticket1.passenger.Passenger;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "users")
public class User extends BaseModel {
    String email;
    String phoneNumber;
    String password;
    List<Passenger> passengers = new ArrayList<>();
    List<Ticket> tickets = new ArrayList<>();
}
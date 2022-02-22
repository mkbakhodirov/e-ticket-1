package uz.pdp.eticket1.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.pdp.eticket1.base.BaseModel;
import uz.pdp.eticket1.passenger.Passenger;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "tickets")
public class Ticket extends BaseModel {
    Passenger passenger;
    String trainNumber;
    String trainType;
    String carNumber;
    String carType;
    String seat;
    String fromStation;
    String toStation;
    Date arrivalDate;
    Date departureDate;
    BigDecimal price;
}

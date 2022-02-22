package uz.pdp.eticket1.passenger;

import org.springframework.data.mongodb.repository.MongoRepository;
import uz.pdp.eticket1.passenger.Passenger;

public interface PassengerRepository extends MongoRepository<Passenger, String> {
    
}

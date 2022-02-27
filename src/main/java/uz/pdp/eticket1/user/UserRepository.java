package uz.pdp.eticket1.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uz.pdp.eticket1.passenger.Passenger;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    User findUserByUsernameAndPassword(String username, String password);

    User findUserByPhoneNumber(String phoneNumber);

    User findUserByUsername(String username);

}

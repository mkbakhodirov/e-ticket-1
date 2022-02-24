package uz.pdp.eticket1.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uz.pdp.eticket1.passenger.Passenger;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'username': ?0}")
    User findUserByUsername(String username);

    @Query("{'phoneNumber': ?0}")
    User findUserByPhoneNumber(String phoneNumber);

}

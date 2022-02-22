package uz.pdp.eticket1.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uz.pdp.eticket1.passenger.Passenger;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'email': ?0}")
    User findUserByEmail(String email);

    @Query("{'phoneNumber': ?0}")
    User findUserByPhoneNumber(String phoneNumber);

    default void addPassenger(String userId, Passenger passenger) {;
        Optional<User> optional = findById(userId);
        if (optional.isPresent()) {
            User user = optional.get();
            List<Passenger> passengers = user.getPassengers();
            for (Passenger userPassenger : passengers) {
                if (userPassenger.getDocumentType().equals(passenger.getDocumentType()) &&
                        userPassenger.getDocumentNumber().equals(passenger.getDocumentNumber()))
                    return;
            }
            passengers.add(passenger);
            save(user);
        }
    }

    default List<Passenger> getPassengers(String userId) {
        Optional<User> optional = findById(userId);
        if (optional.isPresent()) {
            User user = optional.get();
            return user.getPassengers();
        }
        return null;
    }
}

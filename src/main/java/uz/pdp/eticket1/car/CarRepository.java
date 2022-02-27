package uz.pdp.eticket1.car;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarRepository extends MongoRepository<Car, String> {
    List<Car> findAllByActive(boolean active);
}

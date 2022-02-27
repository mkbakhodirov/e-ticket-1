package uz.pdp.eticket1.train;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainRepository extends MongoRepository<Train, String> {
}

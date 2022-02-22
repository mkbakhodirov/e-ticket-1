package uz.pdp.eticket1.passenger;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.eticket1.passenger.Passenger;
import uz.pdp.eticket1.passenger.PassengerRepository;
import uz.pdp.eticket1.user.UserRepository;
import uz.pdp.eticket1.base.BaseResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passenger")
public class PassengerController implements BaseResponse {
    private final PassengerRepository passengerRepository;
    private final UserRepository userRepository;

    @PostMapping("/add/{userId}")
    public ResponseEntity<Passenger> add(@PathVariable String userId,
                              @RequestBody Passenger passenger
    ) {
        Passenger passenger1 = passengerRepository.save(passenger);
        userRepository.addPassenger(userId, passenger);
        return new ResponseEntity<>(passenger1, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Passenger>> get() {
        List<Passenger> list = passengerRepository.findAll();
        if (!list.isEmpty())
            return new ResponseEntity<>(list, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Passenger> get(@PathVariable String id) {
        Optional<Passenger> optional = passengerRepository.findById(id);
        Passenger passenger;
        if (optional.isPresent()) {
            passenger = optional.get();
            return new ResponseEntity<>(passenger, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getByUser/{userId}")
    public ResponseEntity<List<Passenger>> getByUser(@PathVariable("userId") String userId) {
        List<Passenger> list = userRepository.getPassengers(userId);
        if (list == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

package uz.pdp.eticket1.passenger;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.pdp.eticket1.passenger.Passenger;
import uz.pdp.eticket1.passenger.PassengerRepository;
import uz.pdp.eticket1.user.UserRepository;
import uz.pdp.eticket1.base.BaseResponse;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passengers")
public class PassengerController implements BaseResponse {
    private final PassengerService passengerService;
    private final PassengerRepository passengerRepository;
    private final UserRepository userRepository;


    @PostMapping
    public ResponseEntity<Object> add(@RequestParam(name = "userId") String userId,
                              @RequestBody PassengerReceiveDTO passengerReceiveDTO
    ) {
        passengerReceiveDTO.setUserId(userId);
        String documentNumber = passengerService.add(passengerReceiveDTO);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().queryParam("documentNumber")
                        .buildAndExpand(documentNumber).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public List<PassengerResponseDTO> getList(@RequestParam(name = "userId") String userId) {
        return passengerService.getList(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> get(@PathVariable String id) {
        Optional<Passenger> optional = passengerRepository.findById(id);
        Passenger passenger;
        if (optional.isPresent()) {
            passenger = optional.get();
            return new ResponseEntity<>(passenger, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

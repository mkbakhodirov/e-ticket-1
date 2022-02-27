package uz.pdp.eticket1.passenger;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.pdp.eticket1.user.UserRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passengers")
public class PassengerController {
    private final PassengerService passengerService;
    private final PassengerRepository passengerRepository;
    private final UserRepository userRepository;


    @PostMapping
    public ResponseEntity<Object> add(@RequestParam(name = "userId") String userId,
                              @RequestBody PassengerRequestDTO passengerRequestDTO
    ) {
        passengerRequestDTO.setUserId(userId);
        String documentNumber = passengerService.add(passengerRequestDTO);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("{documentNumber}")
                        .buildAndExpand(documentNumber).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public List<Passenger> getList(@RequestParam(name = "userId") String userId) {
        return passengerService.getList(userId);
    }

    @GetMapping("/{documentNumber}")
    public Passenger get(@PathVariable("documentNumber") String documentNumber,
                                         @RequestParam(name = "userId") String userId
    ) {
        return passengerService.get(userId, documentNumber);
    }
}

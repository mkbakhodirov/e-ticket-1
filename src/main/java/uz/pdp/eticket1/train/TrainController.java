package uz.pdp.eticket1.train;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.pdp.eticket1.car.CarRequestDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trains")
public class TrainController {

    private final TrainService trainService;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody TrainRequestDTO trainRequestDTO) {
        String newId = trainService.add(trainRequestDTO);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(newId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public Train get(@PathVariable("id") String id) {
        return trainService.get(id);
    }

    @GetMapping
    public List<Train> get() {
        return trainService.getList();
    }

}

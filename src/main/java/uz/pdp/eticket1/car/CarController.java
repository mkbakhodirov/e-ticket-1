package uz.pdp.eticket1.car;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody CarRequestDTO carRequestDTO) {
        String newId = carService.add(carRequestDTO);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(newId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public Car get(@PathVariable("id") String id) {
        return carService.get(id);
    }

    @GetMapping
    public List<Car> getList() {
        return carService.getList();
    }

    @GetMapping("/all")
    public List<Car> getActiveList() {
        return carService.getActiveList();
    }

}

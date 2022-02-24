package uz.pdp.eticket1.distance;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/distances")
public class DistanceController {

    private final DistanceService distanceService;

    @PostMapping
    public ResponseEntity<Distance> add(@RequestBody DistanceRequestDTO stationRequestDTO) {
        String newId = distanceService.add(stationRequestDTO);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(newId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public List<DistanceResponseDTO> get() {
        return distanceService.getList();
    }

    @GetMapping("/{id}")
    public DistanceResponseDTO get(@PathVariable("id") String id) {
        return distanceService.get(id);
    }

}

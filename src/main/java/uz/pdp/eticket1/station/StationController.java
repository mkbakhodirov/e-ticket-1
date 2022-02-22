package uz.pdp.eticket1.station;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.pdp.eticket1.station.StationRepository;
import uz.pdp.eticket1.station.Station;
import uz.pdp.eticket1.base.BaseResponse;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stations")
public class StationController {
    private final StationService stationService;
    private final StationRepository stationRepository;

    @PostMapping
    public ResponseEntity<Station> add(@RequestBody StationRequestDTO stationRequestDTO) {
        String newId = stationService.add(stationRequestDTO);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(newId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<Station>> get() {
        List<Station> list = stationRepository.findAll();
        if (!list.isEmpty())
            return new ResponseEntity<>(list, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Station> get(@PathVariable String id) {
        Optional<Station> optional = stationRepository.findById(id);
        Station station;
        if (optional.isPresent()) {
            station = optional.get();
            return new ResponseEntity<>(station, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

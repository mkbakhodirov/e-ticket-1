package uz.pdp.eticket1.station;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.eticket1.station.StationRepository;
import uz.pdp.eticket1.station.Station;
import uz.pdp.eticket1.base.BaseResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/station")
public class StationController implements BaseResponse {

    private final StationRepository stationRepository;

    @PostMapping("/add")
    public ResponseEntity<Station> add(@RequestBody Station station) {
        try {
            Station station1 = stationRepository.save(station);
            return new ResponseEntity<>(station1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(station, HttpStatus.CHECKPOINT);
        }
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

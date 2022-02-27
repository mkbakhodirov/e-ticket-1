package uz.pdp.eticket1.station;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stations")
public class StationController {

    private final StationService stationService;

    @PostMapping
    public ResponseEntity<Object> add(
            @RequestParam("adminId") String adminId,
            @RequestBody StationRequestDTO stationRequestDTO
    ) {
        stationRequestDTO.setAdminId(adminId);
        String newId = stationService.add(stationRequestDTO);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(newId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public List<Station> get() {
        return stationService.getList();
    }

    @GetMapping("/{id}")
    public Station get(@PathVariable("id") String id) {
        return stationService.get(id);
    }

}

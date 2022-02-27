package uz.pdp.eticket1.direction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/directions")
public class DirectionController {

    private final DirectionService directionService;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody DirectionRequestDTO directionRequestDTO) {
        String newId = directionService.add(directionRequestDTO);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(newId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public Direction get(@PathVariable("id") String id) {
        return directionService.get(id);
    }

    @GetMapping
    public List<Direction> get() {
        return directionService.getList();
    }

}

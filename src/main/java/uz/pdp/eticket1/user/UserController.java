package uz.pdp.eticket1.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.pdp.eticket1.base.BaseResponse;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements BaseResponse {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody UserReceiveDTO userReceiveDTO) {
        String newId = userService.add(userReceiveDTO);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(newId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public List<UserResponseDTO> get() {
        return userService.getList();
    }

    @GetMapping("/{id}")
    public UserResponseDTO get(@PathVariable("id") String id) {
        return userService.get(id);
    }
}

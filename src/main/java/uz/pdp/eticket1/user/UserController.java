package uz.pdp.eticket1.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody UserRequestDTO userRequestDTO) {
        String newId = userService.add(userRequestDTO);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(newId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public List<User> get() {
        return userService.getList();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") String id) {
        return userService.get(id);
    }

    @GetMapping("/admin")
    public User login(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.loginAdmin(userRequestDTO.getUsername(), userRequestDTO.getPassword());
    }
}

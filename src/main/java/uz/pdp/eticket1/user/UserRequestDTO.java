package uz.pdp.eticket1.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserRequestDTO {
    private String username;
    private String phoneNumber;
    private String password;
    private int role;
}

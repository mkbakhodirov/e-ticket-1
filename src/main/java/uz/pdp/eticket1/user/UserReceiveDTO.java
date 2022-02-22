package uz.pdp.eticket1.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserReceiveDTO {
    private String email;
    private String phoneNumber;
    private String password;
}

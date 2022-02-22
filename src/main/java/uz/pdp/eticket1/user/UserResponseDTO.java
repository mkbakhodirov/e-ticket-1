package uz.pdp.eticket1.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.eticket1.base.BaseModel;

import java.time.LocalDateTime;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDTO extends BaseModel {
    private String email;
    private String phoneNumber;
    private int numberOfTickets;
    private LocalDateTime lastBuyTicketDate;
}

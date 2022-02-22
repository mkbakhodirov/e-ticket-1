package uz.pdp.eticket1.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDTO {
    private String id;
    private String email;
    private String phoneNumber;
    private int numberOfTickets;
    private Date lastBuyTicketDate;
    private Date creationDate;
}

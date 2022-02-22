package uz.pdp.eticket1.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;

    {
        timestamp = new Date();
    }
}

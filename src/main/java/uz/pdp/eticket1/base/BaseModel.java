package uz.pdp.eticket1.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class BaseModel {
    @Id
    String id;
    LocalDateTime creationDate;
    LocalDateTime updateDate;

    {
        creationDate = LocalDateTime.now();
    }

}

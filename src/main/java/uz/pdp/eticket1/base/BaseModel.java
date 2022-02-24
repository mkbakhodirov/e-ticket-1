package uz.pdp.eticket1.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import uz.pdp.eticket1.user.AdminResponseDTO;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class BaseModel {
    @Id
    @JsonProperty(value = "_id")
    protected String id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    protected LocalDateTime creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    protected LocalDateTime updateDate;
    protected AdminResponseDTO updateBy;

    {
        creationDate = LocalDateTime.now();
    }

}

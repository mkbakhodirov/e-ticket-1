package uz.pdp.eticket1.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import uz.pdp.eticket1.user.UserBase;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class BaseModel {
    @Id
    protected String id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    protected LocalDateTime creationTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    protected LocalDateTime updateTime;
    protected boolean isActive;
    protected UserBase updateBy;

    {
        creationTime = LocalDateTime.now();
        isActive = true;
    }
}

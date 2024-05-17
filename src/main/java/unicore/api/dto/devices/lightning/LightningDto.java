package unicore.api.dto.devices.lightning;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LightningDto {
    private String name;
    private String type;
    private String kind;
    private String style;
    private Integer brightness;
    private Integer speed;
    private Integer length;
    private Integer length_t;
    private Integer length_b;
    private Integer length_l;
    private Integer length_r;
    private String changeTime;
}

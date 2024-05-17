package unicore.api.dto.devices.switch_;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SwitchDto {
    private String name;
    private Boolean active;
    private Boolean inversive;
    private String changeTime;
}

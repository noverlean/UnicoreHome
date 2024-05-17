package unicore.api.dto.devices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddDeviceDto {
    private String device_name;
    private String device_color;
    private String device_type;
    private String ip;
}

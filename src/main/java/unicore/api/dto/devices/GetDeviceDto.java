package unicore.api.dto.devices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetDeviceDto {
    private String email;
    private String device_name;
    private String access_code;
}

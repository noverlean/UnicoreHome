package unicore.api.dto.devices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccessRequest {
    private String environment_name;
    private String environment_access_code;
    private String device_name;
    private String device_access_code;
}

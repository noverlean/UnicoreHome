package unicore.api.dto.environments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddExitEnvironmentRequest {
    private String environment_name;
    private String environment_email;
}

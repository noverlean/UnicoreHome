package unicore.api.dto.environments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConfirmAccessDto {
    private String email;
    private String name;
    private String code;
}

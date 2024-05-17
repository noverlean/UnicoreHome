package unicore.api.dto.tickets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TicketContent {
    private String message;
    private Date sendTime;
    private Boolean isAdmin;
}

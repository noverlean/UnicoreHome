package unicore.api.senders;

import lombok.Getter;
import lombok.Setter;

public class EmailNotification {
    @Setter @Getter
    public String subject;
    @Setter @Getter
    public String content;

    public EmailNotification(String subject, String content){
        this.subject = subject;
        this.content = content;
    }
}

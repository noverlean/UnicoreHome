package unicore.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "changeTimestamp")
    private Date changeTimestamp;

    @ManyToOne(optional = true)
    @JoinColumn(name = "support_id")
    @JsonIgnore
    private User support;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Ticket(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.changeTimestamp = new Date();
    }

    public Ticket() {

    }

    public void print() {
        System.out.println("========================================");
        System.out.println("Id      : " + getId());
        System.out.println("Title   : " + getTitle());
        System.out.println("Content   : " + getContent());
        System.out.println("ChangeTimestamp   : " + getChangeTimestamp());
        System.out.println("========================================");
    }
}

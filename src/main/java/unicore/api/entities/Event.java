package unicore.api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "when_user_near")
    private String when_user_near;

    @ManyToOne(optional = false)
    @JoinColumn(name = "device_id")
    private Environment device;

    @ManyToOne(optional = false)
    @JoinColumn(name = "environment_id")
    private Environment environment;

    public void print()
    {
        System.out.println("========================================");
        System.out.println("Id             : " + getId());
        System.out.println("Timestamp      : " + getTimestamp());
        System.out.println("When_user_near : " + getWhen_user_near());
        System.out.println("========================================");
    }
}

package unicore.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "access_code")
    private String access_code;

    @Column(name = "color")
    private String color;

    @Column(name = "ip")
    private String ip;

    @Column(name = "changeTime")
    private Date changeTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "environment_id")
    @JsonIgnore
    private Environment environment;

    @OneToOne
    private Lightning lightning;

    @OneToOne
    private Switch switch_;

    public Device(String name, String access_code, String color, Environment environment, String ip, Date changeTime){
        this.name = name;
        this.access_code = access_code;
        this.color = color;
        this.environment = environment;
        this.ip = ip;
        this.changeTime = changeTime;
    }

    public Device() {}

    public void print()
    {
        System.out.println("========================================");
        System.out.println("Id          : " + getId());
        System.out.println("Name        : " + getName());
        System.out.println("Access_code : " + getAccess_code());
        System.out.println("Color : " + getColor());
        System.out.println("IP : " + getIp());
        System.out.println("Date : " + getChangeTime());
        System.out.println("========================================");
    }
}

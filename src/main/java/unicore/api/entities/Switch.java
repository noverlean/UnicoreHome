package unicore.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
@Table(name = "switches")
public class Switch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "inversive")
    private Boolean inversive;

    public Switch(Boolean active, Boolean inversive){
        this.active = active;
        this.inversive = inversive;
    }

    public Switch(){
        this.active = false;
        this.inversive = false;
    }

    public void print()
    {
        System.out.println("========================================");
        System.out.println("Id          : " + getId());
        System.out.println("Active         : " + getActive());
        System.out.println("Id          : " + getInversive());
        System.out.println("========================================");
    }
}

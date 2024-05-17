package unicore.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicore.api.utils.CodeGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "environments")
public class Environment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "access_code")
    private String access_code;

    @Column(name = "creator_email")
    private String creator_email;

    @OneToMany (mappedBy="environment", fetch=FetchType.EAGER)
    @JsonIgnore
    private Collection<User> users;

    @OneToMany (mappedBy="environment", fetch=FetchType.EAGER)
    private Collection<Device> devices;

    public Environment(String name, String creator_email)
    {
        setName(name);
        setCreator_email(creator_email);
        setAccess_code(CodeGenerator.generateCode());
        devices = new ArrayList<>();
    }

    public void print()
    {
        System.out.println("========================================");
        System.out.println("Id          : " + getId());
        System.out.println("Name        : " + getName());
        System.out.println("Access_code : " + getAccess_code());
        System.out.println("Creator_email : " + getCreator_email());
        System.out.print("Users         : ");
        System.out.println(users);
        System.out.println("========================================");
    }
}

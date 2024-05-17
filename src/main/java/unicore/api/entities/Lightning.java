package unicore.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "lightnings")
public class Lightning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "kind")
    private String kind;

    @Column(name = "style")
    private String style;

    @Column(name = "brightness")
    private Integer brightness;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "length")
    private Integer length;

    @Column(name = "length_t")
    private Integer length_t;

    @Column(name = "length_b")
    private Integer length_b;

    @Column(name = "length_l")
    private Integer length_l;

    @Column(name = "length_r")
    private Integer length_r;

    public Lightning(String type, String kind, String style, Integer brightness, Integer speed, Integer length, Integer length_t, Integer length_b, Integer length_l, Integer length_r){
        this.type = type;
        this.kind = kind;
        this.style = style;
        this.brightness = brightness;
        this.speed = speed;
        this.length = length;
        this.length_t = length_t;
        this.length_b = length_b;
        this.length_l = length_l;
        this.length_r = length_r;
    }

    public Lightning(String type, String kind, Integer length_t, Integer length_b, Integer length_l, Integer length_r){
        this(type, kind, "fade", 100, 1, (length_t + length_b + length_l + length_r), length_t, length_b, length_l, length_r);
    }

    public Lightning(String type, String kind, Integer length){
        this(type, kind, "fade", 100, 1, length, length, 0, 0, 0);
    }

    public Lightning(String type, String kind){
        this(type, kind, "fade", 100, 1, 10, 10, 0, 0, 0);
    }

    public Lightning(){
        this("sun", "WS2811", "fade", 100, 1, 10, 10, 0, 0, 0);
    }

    public void print()
    {
        System.out.println("========================================");
        System.out.println("Id          : " + getId());
        System.out.println("Type        : " + getType());
        System.out.println("Kind        : " + getKind());
        System.out.println("Style       : " + getStyle());
        System.out.println("Brightness  : " + getBrightness());
        System.out.println("Speed       : " + getSpeed());
        System.out.println("Length      : " + getLength());
        System.out.println("Length_t    : " + getLength_t());
        System.out.println("Length_b    : " + getLength_b());
        System.out.println("Length_l    : " + getLength_l());
        System.out.println("Length_r    : " + getLength_r());
        System.out.println("========================================");
    }
}

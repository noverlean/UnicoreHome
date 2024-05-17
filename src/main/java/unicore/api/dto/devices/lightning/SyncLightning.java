package unicore.api.dto.devices.lightning;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SyncLightning {
    private String email;
    private String device_name;
    private String access_code;
    private String type;
    private String kind;
    private String style;
    private Integer brightness;
    private Integer speed;
    private Integer length;
    private Integer length_t;
    private Integer length_b;
    private Integer length_l;
    private Integer length_r;
    private String ip;
    private String changeTime;

    public void setAsLightningDto(LightningDto lightningDto) {
        this.type = lightningDto.getType();
        this.kind = lightningDto.getKind();
        this.style = lightningDto.getStyle();
        this.brightness = lightningDto.getBrightness();
        this.speed = lightningDto.getSpeed();
        this.length = lightningDto.getLength();
        this.length_t = lightningDto.getLength_t();
        this.length_b = lightningDto.getLength_b();
        this.length_l = lightningDto.getLength_l();
        this.length_r = lightningDto.getLength_r();
    }

    public LightningDto getAsLightningDto() {
        LightningDto lightningDto = new LightningDto();

        lightningDto.setType(type);
        lightningDto.setKind(kind);
        lightningDto.setStyle(style);
        lightningDto.setBrightness(brightness);
        lightningDto.setSpeed(speed);
        lightningDto.setLength(length);
        lightningDto.setLength_t(length_t);
        lightningDto.setLength_b(length_b);
        lightningDto.setLength_l(length_l);
        lightningDto.setLength_r(length_r);

        return lightningDto;
    }
}

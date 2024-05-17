package unicore.api.dto.devices.switch_;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SyncSwitch {
    private String email;
    private String device_name;
    private String access_code;
    private Boolean switch_active;
    private Boolean switch_inversive;
    private String ip;
    private String changeTime;

    public void setAsSwitchDto(SwitchDto switchDto){
        this.switch_active = switchDto.getActive();
        this.switch_inversive = switchDto.getInversive();
    }

    public SwitchDto getAsSwitchDto(){
        SwitchDto switchDto = new SwitchDto();

        switchDto.setActive(switch_active);
        switchDto.setInversive(switch_inversive);

        return switchDto;
    }
}

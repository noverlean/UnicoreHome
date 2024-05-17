package unicore.api.mappers;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import unicore.api.dto.devices.switch_.SwitchDto;
import unicore.api.dto.devices.switch_.SyncSwitch;
import unicore.api.entities.Switch;

@Mapper(componentModel = "spring")
@Component
public interface SwitchMapper {
    Switch dtoToModel(SwitchDto switchDto);

    SwitchDto modelToDto(Switch switch_);
}
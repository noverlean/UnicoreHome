package unicore.api.mappers;

import org.mapstruct.Mapper;
import unicore.api.dto.devices.lightning.LightningDto;
import unicore.api.entities.Lightning;

@Mapper(componentModel = "spring")
public interface LightningMapper {
    Lightning dtoToModel(LightningDto lightningDto);

    LightningDto modelToDto(Lightning lightning);
}
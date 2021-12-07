package fr.univlorraine.miage.revolutmiage.operation.mapper;

import fr.univlorraine.miage.revolutmiage.compte.mapper.CompteMapper;
import fr.univlorraine.miage.revolutmiage.operation.dto.OperationDTO;
import fr.univlorraine.miage.revolutmiage.operation.entity.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(uses = {CompteMapper.class}, componentModel = "spring", imports = LocalDateTime.class)
public interface OperationMapper {

    @Mapping(target = "dateOperation", expression = "java(entity.getDateOperation().toString())")
    @Mapping(target = "compteCrediteur", source = "entity.compteCrediteur")
    OperationDTO toDto(Operation entity);

    @Mapping(target = "dateOperation", expression = "java(LocalDateTime.parse(dto.getDateOperation()))")
    @Mapping(target = "compteCrediteur", source = "dto.compteCrediteur")
    Operation toObj(OperationDTO dto);
}

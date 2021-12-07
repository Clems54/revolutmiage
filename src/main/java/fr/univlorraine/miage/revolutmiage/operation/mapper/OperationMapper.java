package fr.univlorraine.miage.revolutmiage.operation.mapper;

import fr.univlorraine.miage.revolutmiage.compte.mapper.CompteMapper;
import fr.univlorraine.miage.revolutmiage.operation.dto.OperationDTO;
import fr.univlorraine.miage.revolutmiage.operation.entity.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(uses = {CompteMapper.class}, componentModel = "spring", imports = {LocalDateTime.class, UUID.class})
public interface OperationMapper {

    @Mapping(target = "dateOperation", expression = "java(entity.getDateOperation().toString())")
    @Mapping(target = "idOperation", expression = "java(entity.getIdOperation().toString())")
    OperationDTO toDto(Operation entity);

    @Mapping(target = "dateOperation", expression = "java(LocalDateTime.parse(dto.getDateOperation()))")
    @Mapping(target = "idOperation", expression = "java(UUID.fromString(dto.getIdOperation()))")
    Operation toObj(OperationDTO dto);
}
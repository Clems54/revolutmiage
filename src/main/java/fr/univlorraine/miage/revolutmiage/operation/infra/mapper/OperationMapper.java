package fr.univlorraine.miage.revolutmiage.operation.infra.mapper;

import fr.univlorraine.miage.revolutmiage.compte.infra.mapper.CompteMapper;
import fr.univlorraine.miage.revolutmiage.operation.domain.entity.Operation;
import fr.univlorraine.miage.revolutmiage.operation.infra.dto.OperationDTO;
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

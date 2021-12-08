package fr.univlorraine.miage.revolutmiage.compte.infra.mapper;

import fr.univlorraine.miage.revolutmiage.compte.infra.dto.CompteDTO;
import fr.univlorraine.miage.revolutmiage.compte.domain.entity.Compte;
import fr.univlorraine.miage.revolutmiage.utilisateur.infra.mapper.UtilisateurMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {UtilisateurMapper.class}, componentModel = "spring")
public interface CompteMapper {

    CompteDTO toDto(Compte entity);

    Compte toObj(CompteDTO dto);
}

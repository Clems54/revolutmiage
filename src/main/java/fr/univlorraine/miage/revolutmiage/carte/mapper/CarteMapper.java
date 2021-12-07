package fr.univlorraine.miage.revolutmiage.carte.mapper;

import fr.univlorraine.miage.revolutmiage.carte.dto.CarteDTO;
import fr.univlorraine.miage.revolutmiage.carte.entity.Carte;
import fr.univlorraine.miage.revolutmiage.compte.mapper.CompteMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {CompteMapper.class}, componentModel = "spring")
public interface CarteMapper {

    CarteDTO toDto(Carte entity);

    Carte toObj(CarteDTO dto);
}

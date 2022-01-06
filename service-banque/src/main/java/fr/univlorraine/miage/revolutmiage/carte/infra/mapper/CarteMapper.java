package fr.univlorraine.miage.revolutmiage.carte.infra.mapper;

import fr.univlorraine.miage.revolutmiage.carte.domain.entity.Carte;
import fr.univlorraine.miage.revolutmiage.carte.infra.dto.CarteDTO;
import fr.univlorraine.miage.revolutmiage.compte.infra.mapper.CompteMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {CompteMapper.class}, componentModel = "spring")
public interface CarteMapper {

    CarteDTO toDto(Carte entity);

    Carte toObj(CarteDTO dto);

    List<CarteDTO> toDtos(List<Carte> cartes);
}

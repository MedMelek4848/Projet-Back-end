package com.nourelmaaref.master.service.mapper;

import com.nourelmaaref.master.domain.Classe;
import com.nourelmaaref.master.domain.Examen;
import com.nourelmaaref.master.domain.Matiere;
import com.nourelmaaref.master.service.dto.ClasseDTO;
import com.nourelmaaref.master.service.dto.ExamenDTO;
import com.nourelmaaref.master.service.dto.MatiereDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Examen} and its DTO {@link ExamenDTO}.
 */
@Mapper(componentModel = "spring")
public interface ExamenMapper extends EntityMapper<ExamenDTO, Examen> {
    @Mapping(target = "nomMatiere", source = "nomMatiere", qualifiedByName = "matiereNomMatiere")
    @Mapping(target = "nomClasse", source = "nomClasse", qualifiedByName = "classeNomClasse")
    ExamenDTO toDto(Examen s);

    @Named("matiereNomMatiere")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nomMatiere", source = "nomMatiere")
    MatiereDTO toDtoMatiereNomMatiere(Matiere matiere);

    @Named("classeNomClasse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nomClasse", source = "nomClasse")
    ClasseDTO toDtoClasseNomClasse(Classe classe);
}

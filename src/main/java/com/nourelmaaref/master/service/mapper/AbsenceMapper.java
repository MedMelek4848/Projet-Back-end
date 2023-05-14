package com.nourelmaaref.master.service.mapper;

import com.nourelmaaref.master.domain.Absence;
import com.nourelmaaref.master.domain.Eleve;
import com.nourelmaaref.master.domain.Matiere;
import com.nourelmaaref.master.service.dto.AbsenceDTO;
import com.nourelmaaref.master.service.dto.EleveDTO;
import com.nourelmaaref.master.service.dto.MatiereDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Absence} and its DTO {@link AbsenceDTO}.
 */
@Mapper(componentModel = "spring")
public interface AbsenceMapper extends EntityMapper<AbsenceDTO, Absence> {
    @Mapping(target = "matriculeEleve", source = "matriculeEleve", qualifiedByName = "eleveMatricule")
    @Mapping(target = "nomMatiere", source = "nomMatiere", qualifiedByName = "matiereNomMatiere")
    AbsenceDTO toDto(Absence s);

    @Named("eleveMatricule")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "matricule", source = "matricule")
    EleveDTO toDtoEleveMatricule(Eleve eleve);

    @Named("matiereNomMatiere")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nomMatiere", source = "nomMatiere")
    MatiereDTO toDtoMatiereNomMatiere(Matiere matiere);
}

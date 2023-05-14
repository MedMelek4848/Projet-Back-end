package com.nourelmaaref.master.service.mapper;

import com.nourelmaaref.master.domain.Classe;
import com.nourelmaaref.master.domain.Cours;
import com.nourelmaaref.master.domain.Matiere;
import com.nourelmaaref.master.domain.Professeur;
import com.nourelmaaref.master.service.dto.ClasseDTO;
import com.nourelmaaref.master.service.dto.CoursDTO;
import com.nourelmaaref.master.service.dto.MatiereDTO;
import com.nourelmaaref.master.service.dto.ProfesseurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cours} and its DTO {@link CoursDTO}.
 */
@Mapper(componentModel = "spring")
public interface CoursMapper extends EntityMapper<CoursDTO, Cours> {
    @Mapping(target = "enseignerPar", source = "enseignerPar", qualifiedByName = "professeurCin")
    @Mapping(target = "pourLaClasse", source = "pourLaClasse", qualifiedByName = "classeNomClasse")
    @Mapping(target = "nomMatiere", source = "nomMatiere", qualifiedByName = "matiereNomMatiere")
    CoursDTO toDto(Cours s);

    @Named("professeurCin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "cin", source = "cin")
    ProfesseurDTO toDtoProfesseurCin(Professeur professeur);

    @Named("classeNomClasse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nomClasse", source = "nomClasse")
    ClasseDTO toDtoClasseNomClasse(Classe classe);

    @Named("matiereNomMatiere")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nomMatiere", source = "nomMatiere")
    MatiereDTO toDtoMatiereNomMatiere(Matiere matiere);
}

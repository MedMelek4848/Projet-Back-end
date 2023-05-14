package com.nourelmaaref.master.service.mapper;

import com.nourelmaaref.master.domain.Eleve;
import com.nourelmaaref.master.domain.Examen;
import com.nourelmaaref.master.domain.Note;
import com.nourelmaaref.master.service.dto.EleveDTO;
import com.nourelmaaref.master.service.dto.ExamenDTO;
import com.nourelmaaref.master.service.dto.NoteDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Note} and its DTO {@link NoteDTO}.
 */
@Mapper(componentModel = "spring")
public interface NoteMapper extends EntityMapper<NoteDTO, Note> {
    @Mapping(target = "matriculeEleve", source = "matriculeEleve", qualifiedByName = "eleveMatricule")
    @Mapping(target = "nomExamen", source = "nomExamen", qualifiedByName = "examenNomExamen")
    NoteDTO toDto(Note s);

    @Named("eleveMatricule")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "matricule", source = "matricule")
    EleveDTO toDtoEleveMatricule(Eleve eleve);

    @Named("examenNomExamen")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nomExamen", source = "nomExamen")
    ExamenDTO toDtoExamenNomExamen(Examen examen);
}

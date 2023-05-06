package com.nourelmaaref.master.service.mapper;

import com.nourelmaaref.master.domain.Classe;
import com.nourelmaaref.master.domain.Eleve;
import com.nourelmaaref.master.service.dto.ClasseDTO;
import com.nourelmaaref.master.service.dto.EleveDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Eleve} and its DTO {@link EleveDTO}.
 */
@Mapper(componentModel = "spring")
public interface EleveMapper extends EntityMapper<EleveDTO, Eleve> {
    @Mapping(target = "appartientA", source = "appartientA", qualifiedByName = "classeNomClasse")
    EleveDTO toDto(Eleve s);

    @Named("classeNomClasse")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nomClasse", source = "nomClasse")
    ClasseDTO toDtoClasseNomClasse(Classe classe);
}

package com.nourelmaaref.master.service.mapper;

import com.nourelmaaref.master.domain.Eleve;
import com.nourelmaaref.master.domain.Paiement;
import com.nourelmaaref.master.service.dto.EleveDTO;
import com.nourelmaaref.master.service.dto.PaiementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Paiement} and its DTO {@link PaiementDTO}.
 */
@Mapper(componentModel = "spring")
public interface PaiementMapper extends EntityMapper<PaiementDTO, Paiement> {
    @Mapping(target = "matricule", source = "matricule", qualifiedByName = "eleveMatricule")
    PaiementDTO toDto(Paiement s);

    @Named("eleveMatricule")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "matricule", source = "matricule")
    EleveDTO toDtoEleveMatricule(Eleve eleve);
}

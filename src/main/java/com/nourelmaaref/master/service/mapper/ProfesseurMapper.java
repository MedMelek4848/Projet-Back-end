package com.nourelmaaref.master.service.mapper;

import com.nourelmaaref.master.domain.Professeur;
import com.nourelmaaref.master.service.dto.ProfesseurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Professeur} and its DTO {@link ProfesseurDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProfesseurMapper extends EntityMapper<ProfesseurDTO, Professeur> {}

package com.nourelmaaref.master.service.mapper;

import com.nourelmaaref.master.domain.Matiere;
import com.nourelmaaref.master.service.dto.MatiereDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Matiere} and its DTO {@link MatiereDTO}.
 */
@Mapper(componentModel = "spring")
public interface MatiereMapper extends EntityMapper<MatiereDTO, Matiere> {}

package com.nourelmaaref.master.service.mapper;

import com.nourelmaaref.master.domain.Classe;
import com.nourelmaaref.master.service.dto.ClasseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Classe} and its DTO {@link ClasseDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClasseMapper extends EntityMapper<ClasseDTO, Classe> {}

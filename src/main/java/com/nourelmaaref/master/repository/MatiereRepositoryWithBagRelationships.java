package com.nourelmaaref.master.repository;

import com.nourelmaaref.master.domain.Matiere;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface MatiereRepositoryWithBagRelationships {
    Optional<Matiere> fetchBagRelationships(Optional<Matiere> matiere);

    List<Matiere> fetchBagRelationships(List<Matiere> matieres);

    Page<Matiere> fetchBagRelationships(Page<Matiere> matieres);
}

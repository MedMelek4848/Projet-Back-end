package tn.Louati.GestionEcole.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Louati.GestionEcole.model.Eleve1;
@Repository
public interface EleveRepository extends JpaRepository<Eleve1, Long> {

}

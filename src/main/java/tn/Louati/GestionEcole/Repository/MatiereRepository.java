package tn.Louati.GestionEcole.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Louati.GestionEcole.model.Matiere;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long>{

}

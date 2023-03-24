package tn.Louati.GestionEcole.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Louati.GestionEcole.model.Professeur;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur,Long> {

}

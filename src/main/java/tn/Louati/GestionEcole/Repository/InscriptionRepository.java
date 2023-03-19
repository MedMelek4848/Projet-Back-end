package tn.Louati.GestionEcole.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Louati.GestionEcole.model.Inscription;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long>{

}

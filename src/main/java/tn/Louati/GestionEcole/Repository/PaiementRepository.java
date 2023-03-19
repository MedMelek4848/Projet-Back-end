package tn.Louati.GestionEcole.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Louati.GestionEcole.model.Paiement;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long>{

}

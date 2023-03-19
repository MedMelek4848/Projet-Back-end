package tn.Louati.GestionEcole.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Louati.GestionEcole.model.Groupe;

@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Long> {

}

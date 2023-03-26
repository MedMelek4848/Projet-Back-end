package tn.Louati.GestionEcole.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Louati.GestionEcole.model.Bultain;

@Repository
public interface BultainRepository extends JpaRepository<Bultain, Long>{

}

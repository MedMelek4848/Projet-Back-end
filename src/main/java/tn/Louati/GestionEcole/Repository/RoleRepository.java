package tn.Louati.GestionEcole.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Louati.GestionEcole.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}

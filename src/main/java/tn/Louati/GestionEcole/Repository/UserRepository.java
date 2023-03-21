package tn.Louati.GestionEcole.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Louati.GestionEcole.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	  
	 
}

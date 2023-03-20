package tn.Louati.GestionEcole.Repository;

import org.apache.catalina.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	  
	  @Query("Select d from User d where d.user_id=:x")
	   User findEnabled (@Param("x")  int user_id );
	  
	//  @Query("Select d from User d where d.confirmationToken=:x")
	   User findByConfirmationToken (String confirmationToken );
	   
	  /* @Query("Select d from User d where d.role=:x")
	   List <User> findEmployee (@Param("x") int RH );*/
	   
	 //  User findByLogin(String login);
	   User findByLogin(String login);
}

package tn.Louati.GestionEcole.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Role")
public class Role {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idrole;

	    private String roleName;

	    @JsonBackReference
	    @ManyToMany(mappedBy = "roles")
	    private Set<User> users = new HashSet<User>();

	    public Role() {
	    }

	    public Role(String roleName) {
	        this.roleName = roleName;
	    }

	    // Les getters et setters

	 

	    public String getRoleName() {
	        return roleName;
	    }

	    public Long getIdrole() {
			return idrole;
		}

		public void setIdrole(Long idrole) {
			this.idrole = idrole;
		}

		public void setRoleName(String roleName) {
	        this.roleName = roleName;
	    }

	    public Set<User> getUsers() {
	        return users;
	    }

	    public void setUsers(Set<User> users) {
	        this.users = users;
	    }
	

	
}

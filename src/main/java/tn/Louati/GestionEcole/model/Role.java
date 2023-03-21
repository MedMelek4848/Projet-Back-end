package tn.Louati.GestionEcole.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Role")
public class Role {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String roleName;

	    // Un rôle peut avoir plusieurs utilisateurs
	    @ManyToMany(mappedBy = "roles")
	    private Set<User> users = new HashSet<User>();

	    public Role() {
	    }

	    public Role(String roleName) {
	        this.roleName = roleName;
	    }

	    // Les getters et setters

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getRoleName() {
	        return roleName;
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

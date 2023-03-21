package tn.Louati.GestionEcole.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;

@Table(name="user")
@Entity
@MappedSuperclass
public class User implements Serializable{
	
		
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iduser;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
/*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
*/
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

     public Long getIduser() {
		return iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

   
    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }
    
    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }
}
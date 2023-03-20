package tn.Louati.GestionEcole.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="User")

public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column
		private Long idUser;
		
		public Long getIdUser() {
			return idUser;
		}

		public void setIdUser(Long idUser) {
			this.idUser = idUser;
		}

		

		@Column(name = "login")
	    private String Login;
		
		
		
		
		public String getLogin() {
			return Login;
		}

		public void setLogin(String login) {
			this.Login = login;
		}
		
		
		private String password;
		
		 
		@Column(name = "enabled")
	    private boolean enabled;
	     
	    public User() {
	        super();
	        this.enabled=false;
	    }
	    
	    
		@Column(name = "active")
		private int active;
		
		


		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		 

		public int getActive() {
			return active;
		}

		public void setActive(int active) {
			this.active = active;
		}

		
		public boolean isEnabled() {
		        return enabled;
		    }

		public void setEnabled(final boolean enabled) {
		        this.enabled = enabled;
		    }

			
		
			
			
			
		@Column(name = "confirmation_token")
		private String confirmationToken;

		public String getConfirmationToken() {
				return confirmationToken;
			}

		public void setConfirmationToken(String confirmationToken) {
				this.confirmationToken = confirmationToken;
			}
		@ManyToMany(cascade = CascadeType.ALL)
		@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "idUser"), inverseJoinColumns = @JoinColumn(name = "idRole"))
		private List<Role> roles=new ArrayList<>();

		public List<Role> getRoles() {
			return roles;
		}

		public void setRoles(List<Role> roles) {
			this.roles = roles;
		}
	

}
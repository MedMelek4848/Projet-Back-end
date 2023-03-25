package tn.Louati.GestionEcole.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "matiere")
public class Matiere implements Serializable {

	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idmatiere;

	@Column(name = "nom")
	private String nom;
	
	@JsonBackReference
	@ManyToMany(mappedBy = "matieres")
	private Set<Professeur> professeurs = new HashSet<Professeur>();

	
	public Long getIdmatiere() {
		return idmatiere;
	}

	public void setIdmatiere(Long idmatiere) {
		this.idmatiere = idmatiere;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Professeur> getProfesseurs() {
		return professeurs;
	}
	

	public void setProfesseurs(Set<Professeur> professeurs) {
		this.professeurs = professeurs;
	}


	@Override
	public String toString() {
		return "Matiere [idmatiere=" + idmatiere + ", nom=" + nom + "]";
	}

}

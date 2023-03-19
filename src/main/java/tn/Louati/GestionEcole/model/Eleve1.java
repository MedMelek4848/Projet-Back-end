package tn.Louati.GestionEcole.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(name = "eleve1")

public class Eleve1 implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ideleve;
	@Column(name = "sexe")
	private String sexe;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "dateDeNaissance")
	private Date dateDeNaissance;
	@Column(name = "email")
	private String email;
	@Column(name = "adresse")
	private String adresse;
	@Column(name = "telephone")
	private String telephone;
	@Column(name = "ville")
	private String ville;
	@Column(name = "pays")
	private String pays;
	@Column(name = "typePaiement")
	private int typePaiement;
	@Column(name = "matricule")
	private String matricule;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "groupe_id")
	private Groupe groupe;
	
	  @OneToMany(mappedBy = "eleve", cascade = CascadeType.ALL)
	    private List<Absence> absences;
	
	public Eleve1() {
	}

	public Long getIdeleve() {
		return ideleve;
	}

	public void setIdeleve(Long ideleve) {
		this.ideleve = ideleve;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public int getTypePaiement() {
		return typePaiement;
	}

	public void setTypePaiement(int typePaiement) {
		this.typePaiement = typePaiement;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
 

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Eleve1 [ideleve=" + ideleve + ", sexe=" + sexe + ", nom=" + nom + ", prenom=" + prenom
				+ ", dateDeNaissance=" + dateDeNaissance + ", email=" + email + ", adresse=" + adresse + ", telephone="
				+ telephone + ", ville=" + ville + ", pays=" + pays + ", typePaiement=" + typePaiement + ", matricule="
				+ matricule + ", groupe=" + groupe + ", absences=" + absences + ", getIdeleve()=" + getIdeleve()
				+ ", getGroupe()=" + getGroupe() + ", getSexe()=" + getSexe() + ", getNom()=" + getNom()
				+ ", getPrenom()=" + getPrenom() + ", getDateDeNaissance()=" + getDateDeNaissance() + ", getEmail()="
				+ getEmail() + ", getAdresse()=" + getAdresse() + ", getTelephone()=" + getTelephone() + ", getVille()="
				+ getVille() + ", getPays()=" + getPays() + ", getTypePaiement()=" + getTypePaiement()
				+ ", getMatricule()=" + getMatricule() + ", getAbsences()=" + getAbsences() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

 
	





}

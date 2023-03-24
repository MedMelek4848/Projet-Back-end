package tn.Louati.GestionEcole.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "professeur")
public class Professeur implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idprofesseur;
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
	@Column(name = "cin")
	private String cin;
	@Column(name = "diplome")
	private String diplome;
	@Column(name = "dateDebutTravail")
	private Date dateDebutTravail;
	
	@ManyToMany
    @JoinTable(name = "professeur_matiere",
            joinColumns = @JoinColumn(name = "professeur_id"),
            inverseJoinColumns = @JoinColumn(name = "matiere_id"))
    private Set<Matiere> matieres = new HashSet<Matiere>();
	
	@ManyToMany
	 @JoinTable(name = "professeur_groupe",
     joinColumns = @JoinColumn(name = "professeur_id"),
     inverseJoinColumns = @JoinColumn(name = "groupe_id"))
    private List<Groupe> groupes=new ArrayList<Groupe>();
    
	public Professeur() {
	}

	public Long getIdprofesseur() {
		return idprofesseur;
	}

	public void setIdprofesseur(Long idprofesseur) {
		this.idprofesseur = idprofesseur;
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

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	public Date getDateDebutTravail() {
		return dateDebutTravail;
	}

	public void setDateDebutTravail(Date dateDebutTravail) {
		this.dateDebutTravail = dateDebutTravail;
	}

	
	public Set<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(Set<Matiere> matieres) {
		this.matieres = matieres;
	}

	public List<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}
	public void addMatiere(Matiere matiere) {
        this.matieres.add(matiere);
    }

    public void addGroupe(Groupe groupe) {
        this.groupes.add(groupe);
    }

	@Override
	public String toString() {
		return "Professeur [idprofesseur=" + idprofesseur + ", sexe=" + sexe + ", nom=" + nom + ", prenom=" + prenom
				+ ", dateDeNaissance=" + dateDeNaissance + ", email=" + email + ", adresse=" + adresse + ", telephone="
				+ telephone + ", ville=" + ville + ", pays=" + pays + ", cin=" + cin + ", diplome=" + diplome
				+ ", dateDebutTravail=" + dateDebutTravail + "]";
	}

}

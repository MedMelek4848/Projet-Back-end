package tn.Louati.GestionEcole.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "matiere")
public class Matiere implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idmatiere;

	@Column(name = "nom", nullable = false)
	private String nom;
	
	 @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL)
	    private List<Absence> absences;

	public Matiere() {

	}

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

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	@Override
	public String toString() {
		return "Matiere [idmatiere=" + idmatiere + ", nom=" + nom + ", absences=" + absences + ", getIdmatiere()="
				+ getIdmatiere() + ", getNom()=" + getNom() + ", getAbsences()=" + getAbsences() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	}

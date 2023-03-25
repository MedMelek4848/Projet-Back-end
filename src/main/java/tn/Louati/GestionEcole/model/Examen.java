package tn.Louati.GestionEcole.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "examen")
public class Examen implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idexamen;
	
	/*@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "idmatiere")
	private Matiere matiere;
	
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name = "ideleve")
	Eleve1 eleve1; */
	
	
	@Column(name="pourcentage")
	private double pourcentage;
	@Column(name="date")
	private Date date;
	@Column(name="duree")
	private int duree; // en minutes
	@Column(name="coefficient")
	private double coefficient;
	@Column(name="note")
	private double note;

	// Getters and Setters

	public Long getIdexamen() {
		return idexamen;
	}

	public void setIdexamen(Long idExamen) {
		this.idexamen = idExamen;
	}
	
	/*@JsonManagedReference(value="examen-matiere")
	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	@JsonManagedReference(value="examen-eleve1")
	public Eleve1 getEleve1() {
		return eleve1;
	}

	public void setEleve1(Eleve1 eleve1) {
		this.eleve1 = eleve1;
	}*/

	public double getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}
}

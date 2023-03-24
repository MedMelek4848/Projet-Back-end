package tn.Louati.GestionEcole.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="note")
public class Note implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idnote;
	@Column(name="noteexam")
	private String noteexam;
	@Column(name="remarque")
	private String remarque;
	 @ManyToOne
	 @JoinColumn(name = "examen_id")
	 private Examen examen;
	@ManyToOne
	@JoinColumn(name = "eleve_id")
	private Eleve1 eleve;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "matiere_id")
	private Matiere matiere;

	public Note() {
	}

	public Long getIdnote() {
		return idnote;
	}

	public void setIdnote(Long idnote) {
		this.idnote = idnote;
	}

	public String getNoteexam() {
		return noteexam;
	}

	public void setNoteexam(String noteexam) {
		this.noteexam = noteexam;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public Eleve1 getEleve() {
		return eleve;
	}

	public void setEleve(Eleve1 eleve) {
		this.eleve = eleve;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}
	
}

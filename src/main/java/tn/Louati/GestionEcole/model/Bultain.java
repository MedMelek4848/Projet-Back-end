package tn.Louati.GestionEcole.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
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
@Table(name = "bultain")

public class Bultain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idbultain;

	@Column(name = "nbabsence")
	private int nbabsence;

	@Column(name = "noteControle1")
	private int noteControle1;

	@Column(name = "noteControle2")
	private int noteControle2;

	@Column(name = "noteSynthese")
	private int noteSynthese;

	@Column(name = "noteTp")
	private int noteTp;
	
	@Column(name="dateExamenC1")
	private Date dateExamenC1;
	
	@Column(name="dateExamenC2")
	private Date dateExamenC2;
	
	@Column(name="dateExamenTp")
	private Date dateExamenTp;
	
	@Column(name="dateExamen")
	private Date dateExamen;
	
	
	

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ideleve")
	Eleve1 eleve1;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "idmatiere")
	Matiere matiere;

	public Long getIdbultain() {
		return idbultain;
	}

	public void setIdbultain(Long idbultain) {
		this.idbultain = idbultain;
	}

	public int getNbabsence() {
		return nbabsence;
	}

	public void setNbabsence(int nbabsence) {
		this.nbabsence = nbabsence;
	}

	public int getNoteControle1() {
		return noteControle1;
	}

	public void setNoteControle1(int noteControle1) {
		this.noteControle1 = noteControle1;
	}

	public int getNoteControle2() {
		return noteControle2;
	}

	public void setNoteControle2(int noteControle2) {
		this.noteControle2 = noteControle2;
	}

	public int getNoteSynthese() {
		return noteSynthese;
	}

	public void setNoteSynthese(int noteSynthese) {
		this.noteSynthese = noteSynthese;
	}

	public int getNoteTp() {
		return noteTp;
	}

	public void setNoteTp(int noteTp) {
		this.noteTp = noteTp;
	}

	public Eleve1 getEleve1() {
		return eleve1;
	}

	public void setEleve1(Eleve1 eleve1) {
		this.eleve1 = eleve1;
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

	public Date getDateExamenC1() {
		return dateExamenC1;
	}

	public void setDateExamenC1(Date dateExamenC1) {
		this.dateExamenC1 = dateExamenC1;
	}

	public Date getDateExamenC2() {
		return dateExamenC2;
	}

	public void setDateExamenC2(Date dateExamenC2) {
		this.dateExamenC2 = dateExamenC2;
	}

	public Date getDateExamenTp() {
		return dateExamenTp;
	}

	public void setDateExamenTp(Date dateExamenTp) {
		this.dateExamenTp = dateExamenTp;
	}

	public Date getDateExamen() {
		return dateExamen;
	}

	@Override
	public String toString() {
		return "Bultain [idbultain=" + idbultain + ", nbabsence=" + nbabsence + ", noteControle1=" + noteControle1
				+ ", noteControle2=" + noteControle2 + ", noteSynthese=" + noteSynthese + ", noteTp=" + noteTp
				+ ", dateExamenC1=" + dateExamenC1 + ", dateExamenC2=" + dateExamenC2 + ", dateExamenTp=" + dateExamenTp
				+ ", dateExamen=" + dateExamen + ", eleve1=" + eleve1 + ", matiere=" + matiere + "]";
	}

	public void setDateExamen(Date dateExamen) {
		this.dateExamen = dateExamen;
	}

}

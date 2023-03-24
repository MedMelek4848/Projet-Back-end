package tn.Louati.GestionEcole.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "examen")
public class Examen implements Serializable{

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

	@ManyToOne
	@JoinColumn(name = "matiere_id")
	private Matiere matiere;

	@ManyToOne
	@JoinColumn(name = "groupe_id")
	private Groupe groupe;
	@Column(name = "numero")
	private String numero;
	@Column(name = "pourcentage")
	private Integer pourcentage;   
	@OneToMany(mappedBy = "examen", cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>();
	@Column(name = "valide")
	private boolean valide;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Column(name = "duree")
	private String duree;

	public Examen() {

	}

	

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(Integer pourcentage) {
		this.pourcentage = pourcentage;
	}

 

	public Long getIdexamen() {
		return idexamen;
	}

	public void setIdexamen(Long idexamen) {
		this.idexamen = idexamen;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}
}

package tn.Louati.GestionEcole.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
@Table(name = "absence")
public class Absence implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAbsence;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eleve_id")
    private Eleve1 eleve;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;

    @Column(name = "date_absence")
    private Date dateAbsence;

    
 
    
    public Absence() {}

    public Absence(Eleve1 eleve, Matiere matiere, Date dateAbsence) {
        this.eleve = eleve;
        this.matiere = matiere;
        this.dateAbsence = dateAbsence;
    }

	public Long getIdAbsence() {
		return idAbsence;
	}

	public void setIdAbsence(Long idAbsence) {
		this.idAbsence = idAbsence;
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

	public Date getDateAbsence() {
		return dateAbsence;
	}

	public void setDateAbsence(Date dateAbsence) {
		this.dateAbsence = dateAbsence;
	}

	@Override
	public String toString() {
		return "Absence [idAbsence=" + idAbsence + ", eleve=" + eleve + ", matiere=" + matiere + ", dateAbsence="
				+ dateAbsence + ", getIdAbsence()=" + getIdAbsence() + ", getEleve()=" + getEleve() + ", getMatiere()="
				+ getMatiere() + ", getDateAbsence()=" + getDateAbsence() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}

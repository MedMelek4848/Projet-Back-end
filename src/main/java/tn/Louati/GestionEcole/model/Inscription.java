package tn.Louati.GestionEcole.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(name="inscription")
public class Inscription implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInscription;
	@Column(name="dateInscription")
	private Date dateInscription;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "eleve_id")
	private Eleve1 eleve;
	@Column(name="valide")
	private boolean valide;
	@Column(name="current")
	private boolean current;
	public Inscription() {
	}
	public Long getIdInscription() {
		return idInscription;
	}
	public void setIdInscription(Long idInscription) {
		this.idInscription = idInscription;
	}
	public Date getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	public Eleve1 getEleve() {
		return eleve;
	}
	public void setEleve(Eleve1 eleve) {
		this.eleve = eleve;
	}
	public boolean isValide() {
		return valide;
	}
	public void setValide(boolean valide) {
		this.valide = valide;
	}
	public boolean isCurrent() {
		return current;
	}
	public void setCurrent(boolean current) {
		this.current = current;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Inscription [idInscription=" + idInscription + ", dateInscription=" + dateInscription + ", eleve="
				+ eleve + ", valide=" + valide + ", current=" + current + ", getIdInscription()=" + getIdInscription()
				+ ", getDateInscription()=" + getDateInscription() + ", getEleve()=" + getEleve() + ", isValide()="
				+ isValide() + ", isCurrent()=" + isCurrent() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}

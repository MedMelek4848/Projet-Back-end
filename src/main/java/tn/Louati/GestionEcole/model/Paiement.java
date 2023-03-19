package tn.Louati.GestionEcole.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name="paiement")
public class Paiement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPaiement;
	
	@Column(name="typeService")
	private String typeService;
	
	@Column(name="montant")
	private float montant;
	@Column(name="modePaiement")
	private String modePaiement;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "inscription_id")
	private Inscription inscription;
	@Column(name="numCheque")
	private String numCheque;
	@Column(name="datePaiment")
	private Date datePaiment;
	@Column(name="numRecu")
	private String numRecu;
	@Column(name="mois")
	private String mois;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "eleve_id")
	private Eleve1 eleve;
	
	public Paiement() {
	}

	public int getIdPaiement() {
		return idPaiement;
	}

	public void setIdPaiement(int idPaiement) {
		this.idPaiement = idPaiement;
	}

	public String getTypeService() {
		return typeService;
	}

	public void setTypeService(String typeService) {
		this.typeService = typeService;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public String getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}

	public Inscription getInscription() {
		return inscription;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public String getNumCheque() {
		return numCheque;
	}

	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}

	public Date getDatePaiment() {
		return datePaiment;
	}

	public void setDatePaiment(Date datePaiment) {
		this.datePaiment = datePaiment;
	}

	public String getNumRecu() {
		return numRecu;
	}

	public void setNumRecu(String numRecu) {
		this.numRecu = numRecu;
	}

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public Eleve1 getEleve() {
		return eleve;
	}

	public void setEleve(Eleve1 eleve) {
		this.eleve = eleve;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Paiement [idPaiement=" + idPaiement + ", typeService=" + typeService + ", montant=" + montant
				+ ", modePaiement=" + modePaiement + ", inscription=" + inscription + ", numCheque=" + numCheque
				+ ", datePaiment=" + datePaiment + ", numRecu=" + numRecu + ", mois=" + mois + ", eleve=" + eleve
				+ ", getIdPaiement()=" + getIdPaiement() + ", getTypeService()=" + getTypeService() + ", getMontant()="
				+ getMontant() + ", getModePaiement()=" + getModePaiement() + ", getInscription()=" + getInscription()
				+ ", getNumCheque()=" + getNumCheque() + ", getDatePaiment()=" + getDatePaiment() + ", getNumRecu()="
				+ getNumRecu() + ", getMois()=" + getMois() + ", getEleve()=" + getEleve() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}

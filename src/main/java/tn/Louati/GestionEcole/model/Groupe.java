package tn.Louati.GestionEcole.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;


@Entity
@Table(name="Groupe")
public class Groupe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGroupe;
	@Column(name="nom")
	private String nom;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER,mappedBy = "groupe")
	private List<Eleve1> eleves= new ArrayList<Eleve1>();
	public Groupe() {
		
	}
	public Long getIdGroupe() {
		return idGroupe;
	}
	public void setIdGroupe(Long idGroupe) {
		this.idGroupe = idGroupe;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Eleve1> getEleves() {
		return eleves;
	}
	public void setEleves(List<Eleve1> eleves) {
		this.eleves = eleves;
	}
	@Override
	public String toString() {
		return "Groupe [idGroupe=" + idGroupe + ", nom=" + nom + ", eleves=" + eleves + ", getIdGroupe()="
				+ getIdGroupe() + ", getNom()=" + getNom() + ", getEleves()=" + getEleves() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	}

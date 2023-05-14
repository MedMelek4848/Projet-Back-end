package com.nourelmaaref.master.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Examen.
 */
@Entity
@Table(name = "examen")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Examen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "num_examen")
    private Integer numExamen;

    @Column(name = "pourcentage")
    private Double pourcentage;

    @Column(name = "valide")
    private Boolean valide;

    @Column(name = "date_examen")
    private LocalDate dateExamen;

    @Column(name = "duree")
    private Integer duree;

    @Column(name = "nom_examen")
    private String nomExamen;

    @ManyToOne
    private Matiere nomMatiere;

    @ManyToOne
    private Classe nomClasse;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Examen id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumExamen() {
        return this.numExamen;
    }

    public Examen numExamen(Integer numExamen) {
        this.setNumExamen(numExamen);
        return this;
    }

    public void setNumExamen(Integer numExamen) {
        this.numExamen = numExamen;
    }

    public Double getPourcentage() {
        return this.pourcentage;
    }

    public Examen pourcentage(Double pourcentage) {
        this.setPourcentage(pourcentage);
        return this;
    }

    public void setPourcentage(Double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Boolean getValide() {
        return this.valide;
    }

    public Examen valide(Boolean valide) {
        this.setValide(valide);
        return this;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public LocalDate getDateExamen() {
        return this.dateExamen;
    }

    public Examen dateExamen(LocalDate dateExamen) {
        this.setDateExamen(dateExamen);
        return this;
    }

    public void setDateExamen(LocalDate dateExamen) {
        this.dateExamen = dateExamen;
    }

    public Integer getDuree() {
        return this.duree;
    }

    public Examen duree(Integer duree) {
        this.setDuree(duree);
        return this;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public String getNomExamen() {
        return this.nomExamen;
    }

    public Examen nomExamen(String nomExamen) {
        this.setNomExamen(nomExamen);
        return this;
    }

    public void setNomExamen(String nomExamen) {
        this.nomExamen = nomExamen;
    }

    public Matiere getNomMatiere() {
        return this.nomMatiere;
    }

    public void setNomMatiere(Matiere matiere) {
        this.nomMatiere = matiere;
    }

    public Examen nomMatiere(Matiere matiere) {
        this.setNomMatiere(matiere);
        return this;
    }

    public Classe getNomClasse() {
        return this.nomClasse;
    }

    public void setNomClasse(Classe classe) {
        this.nomClasse = classe;
    }

    public Examen nomClasse(Classe classe) {
        this.setNomClasse(classe);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Examen)) {
            return false;
        }
        return id != null && id.equals(((Examen) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Examen{" +
            "id=" + getId() +
            ", numExamen=" + getNumExamen() +
            ", pourcentage=" + getPourcentage() +
            ", valide='" + getValide() + "'" +
            ", dateExamen='" + getDateExamen() + "'" +
            ", duree=" + getDuree() +
            ", nomExamen='" + getNomExamen() + "'" +
            "}";
    }
}

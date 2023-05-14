package com.nourelmaaref.master.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Cours.
 */
@Entity
@Table(name = "cours")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Cours implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_cours")
    private Integer numeroCours;

    @Column(name = "date_cour")
    private LocalDate dateCour;

    @ManyToOne
    private Professeur enseignerPar;

    @ManyToOne
    private Classe pourLaClasse;

    @ManyToOne
    private Matiere nomMatiere;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Cours id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroCours() {
        return this.numeroCours;
    }

    public Cours numeroCours(Integer numeroCours) {
        this.setNumeroCours(numeroCours);
        return this;
    }

    public void setNumeroCours(Integer numeroCours) {
        this.numeroCours = numeroCours;
    }

    public LocalDate getDateCour() {
        return this.dateCour;
    }

    public Cours dateCour(LocalDate dateCour) {
        this.setDateCour(dateCour);
        return this;
    }

    public void setDateCour(LocalDate dateCour) {
        this.dateCour = dateCour;
    }

    public Professeur getEnseignerPar() {
        return this.enseignerPar;
    }

    public void setEnseignerPar(Professeur professeur) {
        this.enseignerPar = professeur;
    }

    public Cours enseignerPar(Professeur professeur) {
        this.setEnseignerPar(professeur);
        return this;
    }

    public Classe getPourLaClasse() {
        return this.pourLaClasse;
    }

    public void setPourLaClasse(Classe classe) {
        this.pourLaClasse = classe;
    }

    public Cours pourLaClasse(Classe classe) {
        this.setPourLaClasse(classe);
        return this;
    }

    public Matiere getNomMatiere() {
        return this.nomMatiere;
    }

    public void setNomMatiere(Matiere matiere) {
        this.nomMatiere = matiere;
    }

    public Cours nomMatiere(Matiere matiere) {
        this.setNomMatiere(matiere);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cours)) {
            return false;
        }
        return id != null && id.equals(((Cours) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cours{" +
            "id=" + getId() +
            ", numeroCours=" + getNumeroCours() +
            ", dateCour='" + getDateCour() + "'" +
            "}";
    }
}

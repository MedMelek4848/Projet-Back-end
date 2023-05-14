package com.nourelmaaref.master.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A Matiere.
 */
@Entity
@Table(name = "matiere")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Matiere implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom_matiere")
    private String nomMatiere;

    @Column(name = "coefficient")
    private Integer coefficient;

    @Column(name = "nombre_heures")
    private Integer nombreHeures;

    @Column(name = "nombre_examen")
    private Integer nombreExamen;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Matiere id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomMatiere() {
        return this.nomMatiere;
    }

    public Matiere nomMatiere(String nomMatiere) {
        this.setNomMatiere(nomMatiere);
        return this;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public Integer getCoefficient() {
        return this.coefficient;
    }

    public Matiere coefficient(Integer coefficient) {
        this.setCoefficient(coefficient);
        return this;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }

    public Integer getNombreHeures() {
        return this.nombreHeures;
    }

    public Matiere nombreHeures(Integer nombreHeures) {
        this.setNombreHeures(nombreHeures);
        return this;
    }

    public void setNombreHeures(Integer nombreHeures) {
        this.nombreHeures = nombreHeures;
    }

    public Integer getNombreExamen() {
        return this.nombreExamen;
    }

    public Matiere nombreExamen(Integer nombreExamen) {
        this.setNombreExamen(nombreExamen);
        return this;
    }

    public void setNombreExamen(Integer nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Matiere)) {
            return false;
        }
        return id != null && id.equals(((Matiere) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Matiere{" +
            "id=" + getId() +
            ", nomMatiere='" + getNomMatiere() + "'" +
            ", coefficient=" + getCoefficient() +
            ", nombreHeures=" + getNombreHeures() +
            ", nombreExamen=" + getNombreExamen() +
            "}";
    }
}

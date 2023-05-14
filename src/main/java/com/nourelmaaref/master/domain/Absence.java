package com.nourelmaaref.master.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Absence.
 */
@Entity
@Table(name = "absence")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Absence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_absence")
    private LocalDate dateAbsence;

    @Column(name = "justifie")
    private Boolean justifie;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "nombre_absence")
    private Integer nombreAbsence;

    @ManyToOne
    @JsonIgnoreProperties(value = { "appartientA" }, allowSetters = true)
    private Eleve matriculeEleve;

    @ManyToOne
    private Matiere nomMatiere;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Absence id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateAbsence() {
        return this.dateAbsence;
    }

    public Absence dateAbsence(LocalDate dateAbsence) {
        this.setDateAbsence(dateAbsence);
        return this;
    }

    public void setDateAbsence(LocalDate dateAbsence) {
        this.dateAbsence = dateAbsence;
    }

    public Boolean getJustifie() {
        return this.justifie;
    }

    public Absence justifie(Boolean justifie) {
        this.setJustifie(justifie);
        return this;
    }

    public void setJustifie(Boolean justifie) {
        this.justifie = justifie;
    }

    public String getCommentaire() {
        return this.commentaire;
    }

    public Absence commentaire(String commentaire) {
        this.setCommentaire(commentaire);
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Integer getNombreAbsence() {
        return this.nombreAbsence;
    }

    public Absence nombreAbsence(Integer nombreAbsence) {
        this.setNombreAbsence(nombreAbsence);
        return this;
    }

    public void setNombreAbsence(Integer nombreAbsence) {
        this.nombreAbsence = nombreAbsence;
    }

    public Eleve getMatriculeEleve() {
        return this.matriculeEleve;
    }

    public void setMatriculeEleve(Eleve eleve) {
        this.matriculeEleve = eleve;
    }

    public Absence matriculeEleve(Eleve eleve) {
        this.setMatriculeEleve(eleve);
        return this;
    }

    public Matiere getNomMatiere() {
        return this.nomMatiere;
    }

    public void setNomMatiere(Matiere matiere) {
        this.nomMatiere = matiere;
    }

    public Absence nomMatiere(Matiere matiere) {
        this.setNomMatiere(matiere);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Absence)) {
            return false;
        }
        return id != null && id.equals(((Absence) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Absence{" +
            "id=" + getId() +
            ", dateAbsence='" + getDateAbsence() + "'" +
            ", justifie='" + getJustifie() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            ", nombreAbsence=" + getNombreAbsence() +
            "}";
    }
}

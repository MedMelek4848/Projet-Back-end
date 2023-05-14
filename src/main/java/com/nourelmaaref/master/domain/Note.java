package com.nourelmaaref.master.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A Note.
 */
@Entity
@Table(name = "note")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "note_exam")
    private Double noteExam;

    @Column(name = "remarque")
    private String remarque;

    @ManyToOne
    @JsonIgnoreProperties(value = { "appartientA" }, allowSetters = true)
    private Eleve matriculeEleve;

    @ManyToOne
    @JsonIgnoreProperties(value = { "nomMatiere", "nomClasse" }, allowSetters = true)
    private Examen nomExamen;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Note id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNoteExam() {
        return this.noteExam;
    }

    public Note noteExam(Double noteExam) {
        this.setNoteExam(noteExam);
        return this;
    }

    public void setNoteExam(Double noteExam) {
        this.noteExam = noteExam;
    }

    public String getRemarque() {
        return this.remarque;
    }

    public Note remarque(String remarque) {
        this.setRemarque(remarque);
        return this;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public Eleve getMatriculeEleve() {
        return this.matriculeEleve;
    }

    public void setMatriculeEleve(Eleve eleve) {
        this.matriculeEleve = eleve;
    }

    public Note matriculeEleve(Eleve eleve) {
        this.setMatriculeEleve(eleve);
        return this;
    }

    public Examen getNomExamen() {
        return this.nomExamen;
    }

    public void setNomExamen(Examen examen) {
        this.nomExamen = examen;
    }

    public Note nomExamen(Examen examen) {
        this.setNomExamen(examen);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Note)) {
            return false;
        }
        return id != null && id.equals(((Note) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Note{" +
            "id=" + getId() +
            ", noteExam=" + getNoteExam() +
            ", remarque='" + getRemarque() + "'" +
            "}";
    }
}

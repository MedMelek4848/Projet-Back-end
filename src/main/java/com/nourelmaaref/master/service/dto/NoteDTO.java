package com.nourelmaaref.master.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.nourelmaaref.master.domain.Note} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NoteDTO implements Serializable {

    private Long id;

    private Double noteExam;

    private String remarque;

    private EleveDTO matriculeEleve;

    private ExamenDTO nomExamen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNoteExam() {
        return noteExam;
    }

    public void setNoteExam(Double noteExam) {
        this.noteExam = noteExam;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public EleveDTO getMatriculeEleve() {
        return matriculeEleve;
    }

    public void setMatriculeEleve(EleveDTO matriculeEleve) {
        this.matriculeEleve = matriculeEleve;
    }

    public ExamenDTO getNomExamen() {
        return nomExamen;
    }

    public void setNomExamen(ExamenDTO nomExamen) {
        this.nomExamen = nomExamen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NoteDTO)) {
            return false;
        }

        NoteDTO noteDTO = (NoteDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, noteDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoteDTO{" +
            "id=" + getId() +
            ", noteExam=" + getNoteExam() +
            ", remarque='" + getRemarque() + "'" +
            ", matriculeEleve=" + getMatriculeEleve() +
            ", nomExamen=" + getNomExamen() +
            "}";
    }
}

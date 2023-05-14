package com.nourelmaaref.master.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.nourelmaaref.master.domain.Absence} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AbsenceDTO implements Serializable {

    private Long id;

    private LocalDate dateAbsence;

    private Boolean justifie;

    private String commentaire;

    private Integer nombreAbsence;

    private EleveDTO matriculeEleve;

    private MatiereDTO nomMatiere;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateAbsence() {
        return dateAbsence;
    }

    public void setDateAbsence(LocalDate dateAbsence) {
        this.dateAbsence = dateAbsence;
    }

    public Boolean getJustifie() {
        return justifie;
    }

    public void setJustifie(Boolean justifie) {
        this.justifie = justifie;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Integer getNombreAbsence() {
        return nombreAbsence;
    }

    public void setNombreAbsence(Integer nombreAbsence) {
        this.nombreAbsence = nombreAbsence;
    }

    public EleveDTO getMatriculeEleve() {
        return matriculeEleve;
    }

    public void setMatriculeEleve(EleveDTO matriculeEleve) {
        this.matriculeEleve = matriculeEleve;
    }

    public MatiereDTO getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(MatiereDTO nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbsenceDTO)) {
            return false;
        }

        AbsenceDTO absenceDTO = (AbsenceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, absenceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AbsenceDTO{" +
            "id=" + getId() +
            ", dateAbsence='" + getDateAbsence() + "'" +
            ", justifie='" + getJustifie() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            ", nombreAbsence=" + getNombreAbsence() +
            ", matriculeEleve=" + getMatriculeEleve() +
            ", nomMatiere=" + getNomMatiere() +
            "}";
    }
}

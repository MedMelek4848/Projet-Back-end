package com.nourelmaaref.master.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.nourelmaaref.master.domain.Matiere} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MatiereDTO implements Serializable {

    private Long id;

    private String nomMatiere;

    private Integer coefficient;

    private Integer nombreHeures;

    private Integer nombreExamen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public Integer getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }

    public Integer getNombreHeures() {
        return nombreHeures;
    }

    public void setNombreHeures(Integer nombreHeures) {
        this.nombreHeures = nombreHeures;
    }

    public Integer getNombreExamen() {
        return nombreExamen;
    }

    public void setNombreExamen(Integer nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MatiereDTO)) {
            return false;
        }

        MatiereDTO matiereDTO = (MatiereDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, matiereDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MatiereDTO{" +
            "id=" + getId() +
            ", nomMatiere='" + getNomMatiere() + "'" +
            ", coefficient=" + getCoefficient() +
            ", nombreHeures=" + getNombreHeures() +
            ", nombreExamen=" + getNombreExamen() +
            "}";
    }
}

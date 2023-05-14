package com.nourelmaaref.master.service.dto;

import com.nourelmaaref.master.domain.enumeration.modes;
import com.nourelmaaref.master.domain.enumeration.payType;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.nourelmaaref.master.domain.Paiement} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaiementDTO implements Serializable {

    private Long id;

    private modes modeDePaiement;

    private Integer numeroTranche;

    private payType typeDePaiement;

    private Double montantPaye;

    private EleveDTO matricule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public modes getModeDePaiement() {
        return modeDePaiement;
    }

    public void setModeDePaiement(modes modeDePaiement) {
        this.modeDePaiement = modeDePaiement;
    }

    public Integer getNumeroTranche() {
        return numeroTranche;
    }

    public void setNumeroTranche(Integer numeroTranche) {
        this.numeroTranche = numeroTranche;
    }

    public payType getTypeDePaiement() {
        return typeDePaiement;
    }

    public void setTypeDePaiement(payType typeDePaiement) {
        this.typeDePaiement = typeDePaiement;
    }

    public Double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(Double montantPaye) {
        this.montantPaye = montantPaye;
    }

    public EleveDTO getMatricule() {
        return matricule;
    }

    public void setMatricule(EleveDTO matricule) {
        this.matricule = matricule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaiementDTO)) {
            return false;
        }

        PaiementDTO paiementDTO = (PaiementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paiementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaiementDTO{" +
            "id=" + getId() +
            ", modeDePaiement='" + getModeDePaiement() + "'" +
            ", numeroTranche=" + getNumeroTranche() +
            ", typeDePaiement='" + getTypeDePaiement() + "'" +
            ", montantPaye=" + getMontantPaye() +
            ", matricule=" + getMatricule() +
            "}";
    }
}

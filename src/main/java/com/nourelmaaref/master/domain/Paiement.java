package com.nourelmaaref.master.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nourelmaaref.master.domain.enumeration.modes;
import com.nourelmaaref.master.domain.enumeration.payType;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A Paiement.
 */
@Entity
@Table(name = "paiement")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Paiement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_de_paiement")
    private modes modeDePaiement;

    @Column(name = "numero_tranche")
    private Integer numeroTranche;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_de_paiement")
    private payType typeDePaiement;

    @Column(name = "montant_paye")
    private Double montantPaye;

    @ManyToOne
    @JsonIgnoreProperties(value = { "appartientA" }, allowSetters = true)
    private Eleve matricule;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Paiement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public modes getModeDePaiement() {
        return this.modeDePaiement;
    }

    public Paiement modeDePaiement(modes modeDePaiement) {
        this.setModeDePaiement(modeDePaiement);
        return this;
    }

    public void setModeDePaiement(modes modeDePaiement) {
        this.modeDePaiement = modeDePaiement;
    }

    public Integer getNumeroTranche() {
        return this.numeroTranche;
    }

    public Paiement numeroTranche(Integer numeroTranche) {
        this.setNumeroTranche(numeroTranche);
        return this;
    }

    public void setNumeroTranche(Integer numeroTranche) {
        this.numeroTranche = numeroTranche;
    }

    public payType getTypeDePaiement() {
        return this.typeDePaiement;
    }

    public Paiement typeDePaiement(payType typeDePaiement) {
        this.setTypeDePaiement(typeDePaiement);
        return this;
    }

    public void setTypeDePaiement(payType typeDePaiement) {
        this.typeDePaiement = typeDePaiement;
    }

    public Double getMontantPaye() {
        return this.montantPaye;
    }

    public Paiement montantPaye(Double montantPaye) {
        this.setMontantPaye(montantPaye);
        return this;
    }

    public void setMontantPaye(Double montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Eleve getMatricule() {
        return this.matricule;
    }

    public void setMatricule(Eleve eleve) {
        this.matricule = eleve;
    }

    public Paiement matricule(Eleve eleve) {
        this.setMatricule(eleve);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Paiement)) {
            return false;
        }
        return id != null && id.equals(((Paiement) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Paiement{" +
            "id=" + getId() +
            ", modeDePaiement='" + getModeDePaiement() + "'" +
            ", numeroTranche=" + getNumeroTranche() +
            ", typeDePaiement='" + getTypeDePaiement() + "'" +
            ", montantPaye=" + getMontantPaye() +
            "}";
    }
}

package com.nourelmaaref.master.domain;

import com.nourelmaaref.master.domain.enumeration.Egenre;
import com.nourelmaaref.master.domain.enumeration.typeDePaiement;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Eleve.
 */
@Entity
@Table(name = "eleve")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Eleve implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "adress")
    private String adress;

    @Column(name = "ville")
    private String ville;

    @Column(name = "pays")
    private String pays;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "date_de_naissance")
    private LocalDate dateDeNaissance;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Egenre genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_de_paiement")
    private typeDePaiement typeDePaiement;

    @ManyToOne
    private Classe appartientA;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Eleve id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return this.matricule;
    }

    public Eleve matricule(String matricule) {
        this.setMatricule(matricule);
        return this;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return this.nom;
    }

    public Eleve nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public Eleve prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdress() {
        return this.adress;
    }

    public Eleve adress(String adress) {
        this.setAdress(adress);
        return this;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getVille() {
        return this.ville;
    }

    public Eleve ville(String ville) {
        this.setVille(ville);
        return this;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return this.pays;
    }

    public Eleve pays(String pays) {
        this.setPays(pays);
        return this;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public Eleve telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return this.email;
    }

    public Eleve email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateDeNaissance() {
        return this.dateDeNaissance;
    }

    public Eleve dateDeNaissance(LocalDate dateDeNaissance) {
        this.setDateDeNaissance(dateDeNaissance);
        return this;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public Egenre getGenre() {
        return this.genre;
    }

    public Eleve genre(Egenre genre) {
        this.setGenre(genre);
        return this;
    }

    public void setGenre(Egenre genre) {
        this.genre = genre;
    }

    public typeDePaiement getTypeDePaiement() {
        return this.typeDePaiement;
    }

    public Eleve typeDePaiement(typeDePaiement typeDePaiement) {
        this.setTypeDePaiement(typeDePaiement);
        return this;
    }

    public void setTypeDePaiement(typeDePaiement typeDePaiement) {
        this.typeDePaiement = typeDePaiement;
    }

    public Classe getAppartientA() {
        return this.appartientA;
    }

    public void setAppartientA(Classe classe) {
        this.appartientA = classe;
    }

    public Eleve appartientA(Classe classe) {
        this.setAppartientA(classe);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Eleve)) {
            return false;
        }
        return id != null && id.equals(((Eleve) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Eleve{" +
            "id=" + getId() +
            ", matricule='" + getMatricule() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", adress='" + getAdress() + "'" +
            ", ville='" + getVille() + "'" +
            ", pays='" + getPays() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", dateDeNaissance='" + getDateDeNaissance() + "'" +
            ", genre='" + getGenre() + "'" +
            ", typeDePaiement='" + getTypeDePaiement() + "'" +
            "}";
    }
}

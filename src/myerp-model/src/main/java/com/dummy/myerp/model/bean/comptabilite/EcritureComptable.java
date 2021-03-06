package com.dummy.myerp.model.bean.comptabilite;


import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * Bean représentant une Écriture Comptable
 */
public class EcritureComptable {

    // ==================== Attributs ====================
    /**
     * La liste des lignes d'écriture comptable.
     */
    @Valid
    @Size(min = 2)
    private final List<LigneEcritureComptable> listLigneEcriture = new ArrayList<>();
    /**
     * The Id.
     */
    private Integer id;
    /**
     * Journal comptable
     */
    @NotNull
    private JournalComptable journal;
    /**
     * The Reference.
     */
    @Pattern(regexp = "^[A-Za-z]{2}-\\d{4}/\\d{5}$")
    private String reference;
    /**
     * The Date.
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    /**
     * The Libelle.
     */
    @NotNull
    @Size(min = 1, max = 200)
    private String libelle;

    // ==================== Getters/Setters ====================
    public Integer getId() {
        return id;
    }

    public void setId(Integer pId) {
        id = pId;
    }

    public JournalComptable getJournal() {
        return journal;
    }

    public void setJournal(JournalComptable pJournal) {
        journal = pJournal;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String pReference) {
        reference = pReference;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date pDate) {
        date = pDate;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String pLibelle) {
        libelle = pLibelle;
    }

    public List<LigneEcritureComptable> getListLigneEcriture() {
        return listLigneEcriture;
    }

    /**
     * Calcul et renvoie le total des montants au débit des lignes d'écriture
     *
     * @return {@link BigDecimal}, {@link BigDecimal#ZERO} si aucun montant au débit
     */
    public BigDecimal getTotalDebit() {
        return listLigneEcriture.stream().filter(e -> e.getDebit() != null)
                .map(LigneEcritureComptable::getDebit)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    /**
     * Calcul et renvoie le total des montants au crédit des lignes d'écriture
     *
     * @return {@link BigDecimal}, {@link BigDecimal#ZERO} si aucun montant au crédit
     */
    public BigDecimal getTotalCredit() {
        return listLigneEcriture.stream().filter(e -> e.getCredit() != null)
                .map(LigneEcritureComptable::getCredit)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    /**
     * Renvoie si l'écriture est équilibrée (TotalDebit = TotalCrédit)
     *
     * @return boolean
     */
    public boolean isEquilibree() {
        return getTotalDebit().equals(getTotalCredit());
    }

    // ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        final String vSEP = ", ";
        vStB.append("{")
                .append("id=").append(id)
                .append(vSEP).append("journal=").append(journal)
                .append(vSEP).append("reference='").append(reference).append('\'')
                .append(vSEP).append("date=").append(date)
                .append(vSEP).append("libelle='").append(libelle).append('\'')
                .append(vSEP).append("totalDebit=").append(this.getTotalDebit().toPlainString())
                .append(vSEP).append("totalCredit=").append(this.getTotalCredit().toPlainString())
                .append(vSEP).append("listLigneEcriture=[\n")
                .append(StringUtils.join(listLigneEcriture, "\n")).append("\n]")
                .append("}");
        return vStB.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EcritureComptable that = (EcritureComptable) o;
        return Objects.equals(listLigneEcriture, that.listLigneEcriture) &&
                Objects.equals(id, that.id) &&
                Objects.equals(journal, that.journal) &&
                Objects.equals(reference, that.reference) &&
                Objects.equals(date, that.date) &&
                Objects.equals(libelle, that.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listLigneEcriture, id, journal, reference, date, libelle);
    }
}

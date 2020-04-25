package com.dummy.myerp.model.bean.comptabilite;

import com.dummy.myerp.model.validation.constraint.MontantComptable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;


/**
 * Bean représentant une Ligne d'écriture comptable.
 */
public class LigneEcritureComptable {

    // ==================== Attributs ====================
    /**
     * Compte Comptable
     */
    @NotNull
    private CompteComptable compteComptable;

    /**
     * The Libelle.
     */
    @Size(max = 200)
    private String libelle;

    /**
     * The Debit.
     */
    @MontantComptable
    private BigDecimal debit;

    /**
     * The Credit.
     */
    @MontantComptable
    private BigDecimal credit;


    // ==================== Constructeurs ====================

    /**
     * Instantiates a new Ligne ecriture comptable.
     *
     * @param pCompteComptable the Compte Comptable
     * @param pLibelle         the libelle
     * @param pDebit           the debit
     * @param pCredit          the credit
     */
    public LigneEcritureComptable(CompteComptable pCompteComptable, String pLibelle,
                                  BigDecimal pDebit, BigDecimal pCredit) {
        compteComptable = pCompteComptable;
        libelle = pLibelle;
        debit = pDebit;
        credit = pCredit;
    }


    // ==================== Getters/Setters ====================
    public CompteComptable getCompteComptable() {
        return compteComptable;
    }

    public void setCompteComptable(CompteComptable pCompteComptable) {
        compteComptable = pCompteComptable;
    }

    public String getLibelle() {
        return libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LigneEcritureComptable that = (LigneEcritureComptable) o;
        return Objects.equals(compteComptable, that.compteComptable) &&
                Objects.equals(libelle, that.libelle) &&
                Objects.equals(debit, that.debit) &&
                Objects.equals(credit, that.credit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compteComptable, libelle, debit, credit);
    }

    public void setLibelle(String pLibelle) {
        libelle = pLibelle;
    }

    public BigDecimal getDebit() {
        if (debit == null) {
            return null;
        }
        return debit.setScale(2, RoundingMode.DOWN);
    }

    public void setDebit(BigDecimal pDebit) {
        debit = pDebit;
    }

    public BigDecimal getCredit() {
        if (credit == null) {
            return null;
        }
        return credit.setScale(2, RoundingMode.DOWN);
    }

    public void setCredit(BigDecimal pCredit) {
        credit = pCredit;
    }


    // ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        final String vSEP = ", ";
        vStB.append("{")
                .append("compteComptable=").append(compteComptable)
                .append(vSEP).append("libelle='").append(libelle).append('\'')
                .append(vSEP).append("debit=").append(debit)
                .append(vSEP).append("credit=").append(credit)
                .append("}");
        return vStB.toString();
    }
}

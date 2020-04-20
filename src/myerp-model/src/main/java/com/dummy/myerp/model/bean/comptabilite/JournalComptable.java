package com.dummy.myerp.model.bean.comptabilite;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;


/**
 * Bean représentant un Journal Comptable
 */
public class JournalComptable {

    // ==================== Attributs ====================
    /**
     * code
     */
    @NotNull
    @Size(min = 1, max = 5)
    private String code;

    /**
     * libelle
     */
    @NotNull
    @Size(min = 1, max = 150)
    private String libelle;


    // ==================== Constructeurs ====================

    /**
     * Instantiates a new Journal comptable.
     */
    public JournalComptable() {
    }

    /**
     * Instantiates a new Journal comptable.
     *
     * @param pCode    the p code
     * @param pLibelle the p libelle
     */
    public JournalComptable(String pCode, String pLibelle) {
        code = pCode;
        libelle = pLibelle;
    }

    /**
     * Renvoie le {@link JournalComptable} de code {@code pCode} s'il est présent dans la liste
     *
     * @param pJournalComptableList la liste où chercher le {@link JournalComptable}
     * @param pCode le code du {@link JournalComptable} à chercher
     * @return {@link JournalComptable} ou {@code null}
     */
    public static JournalComptable getByCode(List<? extends JournalComptable> pJournalComptableList, String pCode) {
        return pJournalComptableList.stream()
                .filter(e -> e != null && Objects.equals(((JournalComptable) e).getCode(), pCode))
                .findFirst().orElse(null);
    }

    // ==================== Getters/Setters ====================
    public String getCode() {
        return code;
    }

    public void setCode(String pCode) {
        code = pCode;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String pLibelle) {
        libelle = pLibelle;
    }


    // ==================== Méthodes STATIC ====================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalComptable that = (JournalComptable) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(libelle, that.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, libelle);
    }

    // ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        final String vSEP = ", ";
        vStB.append("{")
                .append("code='").append(code).append('\'')
                .append(vSEP).append("libelle='").append(libelle).append('\'')
                .append("}");
        return vStB.toString();
    }
}

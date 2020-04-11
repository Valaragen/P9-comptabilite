package com.dummy.myerp.model.bean.comptabilite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@Tag("LigneEcritureComptableTest")
public class LigneEcritureComptableTest {

    @Test
    void getDebit_notNullDebit_returnDebitWith2Decimals() {
        BigDecimal debitValue = new BigDecimal("100");
        BigDecimal expectedResult = new BigDecimal("100.00");

        LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable(new CompteComptable(1, "test"), "test", debitValue, new BigDecimal("200"));

        Assertions.assertThat(ligneEcritureComptable.getDebit()).isEqualTo(expectedResult);
    }

    @Test
    void getDebit_nullDebit_returnNull() {
        BigDecimal debitValue = null;

        LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable(new CompteComptable(1, "test"), "test", debitValue, new BigDecimal("200"));

        Assertions.assertThat(ligneEcritureComptable.getDebit()).isNull();
    }

    @Test
    void getCredit_NotNullCredit_returnCreditWith2Decimals() {
        BigDecimal creditValue = new BigDecimal("100");
        BigDecimal expectedResult = new BigDecimal("100.00");

        LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable(new CompteComptable(1, "test"), "test", new BigDecimal("100"), creditValue);

        Assertions.assertThat(ligneEcritureComptable.getCredit()).isEqualTo(expectedResult);
    }

    @Test
    void getCredit_nullCredit_returnNull() {
        BigDecimal creditValue = null;

        LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable(new CompteComptable(1, "test"), "test", new BigDecimal("100"), creditValue);

        Assertions.assertThat(ligneEcritureComptable.getCredit()).isNull();
    }


}

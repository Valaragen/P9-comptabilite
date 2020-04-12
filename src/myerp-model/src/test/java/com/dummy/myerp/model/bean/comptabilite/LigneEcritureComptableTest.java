package com.dummy.myerp.model.bean.comptabilite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@Tag("LigneEcritureComptableTest")
public class LigneEcritureComptableTest {

    LigneEcritureComptable objectToTest;

    @BeforeEach
    void init() {
        objectToTest = new LigneEcritureComptable(new CompteComptable(1, "test"), "test", new BigDecimal("200"), new BigDecimal("200"));
    }

    @Test
    @Tag("RG")
    @Tag("RG_Compta_7")
    void getDebit_notNullDebit_returnDebitWith2Decimals() {
        BigDecimal debitValue = new BigDecimal("100.00864");
        BigDecimal expectedResult = new BigDecimal("100.00");

        objectToTest.setDebit(debitValue);

        Assertions.assertThat(objectToTest.getDebit()).isEqualTo(expectedResult);
    }

    @Test
    void getDebit_nullDebit_returnNull() {
        objectToTest.setDebit(null);

        Assertions.assertThat(objectToTest.getDebit()).isNull();
    }

    @Test
    @Tag("RG")
    @Tag("RG_Compta_7")
    void getCredit_NotNullCredit_returnCreditWith2Decimals() {
        BigDecimal creditValue = new BigDecimal("100.00864");
        BigDecimal expectedResult = new BigDecimal("100.00");

        objectToTest.setCredit(creditValue);

        Assertions.assertThat(objectToTest.getCredit()).isEqualTo(expectedResult);
    }

    @Test
    void getCredit_nullCredit_returnNull() {
        objectToTest.setCredit(null);

        Assertions.assertThat(objectToTest.getCredit()).isNull();
    }


}

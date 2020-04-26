package com.dummy.myerp.model.bean.comptabilite;

import org.apache.commons.lang3.ObjectUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

@Tag("EcritureComptableTest")
class EcritureComptableTest {

    private EcritureComptable objectToTest;

    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        return new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                vLibelle,
                vDebit, vCredit);
    }

    @BeforeEach
    void initiateEcritureComptable() {
        objectToTest = new EcritureComptable();
    }

    @ParameterizedTest(name = "{0} + {1} should return {2}")
    @CsvSource({
            "10.2, 20, 30.20",
            "-10.2, 20 , 9.80",
            "10, 5, 15.00",
            "10.1, -0.1, 10.00"
    })
    @Tag("RG")
    @Tag("RG_Compta_2")
    @DisplayName("GetTotalDebit() should return the correct result with two decimal places")
    void getTotalDebit_addTwoDebit_returnTheirSumFormatted(String value1, String value2, BigDecimal expectedResult) {
        objectToTest.getListLigneEcriture().add(this.createLigne(1, value1, null));
        objectToTest.getListLigneEcriture().add(this.createLigne(1, value2, "33"));

        BigDecimal result = objectToTest.getTotalDebit();

        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "{0} + {1} should return {2}")
    @CsvSource({
            "10.225, 20.55884, 30.77",
            "-10.225, 20.55884 , 10.33",
    })
    @DisplayName("getTotalDebit() should ignore decimals after 2nd decimal")
    void getTotalDebit_addTwoDebitWithMoreThanTwoDecimals_returnTheirSumIgnoringTheDecimalsFollowingTheSecond(String value1, String value2, BigDecimal expectedResult) {
        objectToTest.getListLigneEcriture().add(this.createLigne(1, value1, null));
        objectToTest.getListLigneEcriture().add(this.createLigne(1, value2, "33"));

        BigDecimal result = objectToTest.getTotalDebit();

        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "{0} + {1} should return {2}")
    @CsvSource({
            "10.2, 20, 30.20",
            "-10.2, 20 , 9.80",
            "10, 5, 15.00",
            "10.1, -0.1, 10.00"
    })
    @DisplayName("getTotalCredit() should return the correct result with two decimal places")
    void getTotalCredit_addTwoCredit_returnTheirSumFormatted(String value1, String value2, BigDecimal expectedResult) {
        objectToTest.getListLigneEcriture().add(this.createLigne(1, null, value1));
        objectToTest.getListLigneEcriture().add(this.createLigne(1, "33", value2));

        BigDecimal result = objectToTest.getTotalCredit();

        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "{0} + {1} should return {2}")
    @CsvSource({
            "10.225, 20.55884, 30.77",
            "-10.225, 20.55884 , 10.33",
    })
    @DisplayName("getTotalCredit() should ignore decimals after 2nd decimal")
    void getTotalCredit_addTwoCreditWithMoreThanTwoDecimals_returnTheirSumIgnoringTheDecimalsFollowingTheSecond(String value1, String value2, BigDecimal expectedResult) {
        objectToTest.getListLigneEcriture().add(this.createLigne(1, null, value1));
        objectToTest.getListLigneEcriture().add(this.createLigne(1, "33", value2));

        BigDecimal result = objectToTest.getTotalCredit();

        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "{0} + {1} + {2} = {3} + {4} + {5} should return true")
    @CsvSource({
            "200.50, 100.5, , , 21, 280",
            ", -20.00, 30, -40, 50, "
    })
    @DisplayName("isEquilibree() should return true when debit and credit total values are balanced")
    void isEquilibree_balancedDebitAndCredit_ReturnTrue(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) {
        objectToTest.getListLigneEcriture().add(this.createLigne(1, arg1, arg4));
        objectToTest.getListLigneEcriture().add(this.createLigne(1, arg2, arg5));
        objectToTest.getListLigneEcriture().add(this.createLigne(2, arg3, arg6));

        boolean result = objectToTest.isEquilibree();

        Assertions.assertThat(result).isTrue();
    }

    @ParameterizedTest(name = "{0} + {1} + {2} = {3} + {4} + {5} should return false")
    @CsvSource({
            "10, 20, 1, 1, 10, 21",
            ", -20.00, 31, -42, 50.00, "
    })
    @DisplayName("isEquilibree() should return false when debit and credit total values are unbalanced")
    void isEqulibree_unbalancedDebitAndCredit_ReturnFalse(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) {
        objectToTest.getListLigneEcriture().add(this.createLigne(1, arg1, arg4));
        objectToTest.getListLigneEcriture().add(this.createLigne(1, arg2, arg5));
        objectToTest.getListLigneEcriture().add(this.createLigne(2, arg3, arg6));

        boolean result = objectToTest.isEquilibree();

        Assertions.assertThat(result).isFalse();
    }

    @Test
    void toString_shouldNotThrowException_whenCalled() {
        objectToTest.getListLigneEcriture().add(this.createLigne(1, "10", "1"));
        objectToTest.getListLigneEcriture().add(this.createLigne(1, "20", "10"));
        objectToTest.getListLigneEcriture().add(this.createLigne(2, "1", "21"));

        Assertions.assertThatCode(() -> objectToTest.toString()).doesNotThrowAnyException();
    }



}

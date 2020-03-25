package com.dummy.myerp.model.bean.comptabilite;

import org.apache.commons.lang3.ObjectUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

class EcritureComptableTest {

    private static final Logger log = LoggerFactory.getLogger(EcritureComptableTest.class);

    private EcritureComptable vEcriture;

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
        vEcriture = new EcritureComptable();
    }

    @ParameterizedTest(name = "{0} + {1} should return {2}")
    @CsvSource({
            "10.20, 20, 30.20",
            "10, 20, 30.00",
            "10.20, 20.30, 30.50"
    })
    void getTotalDebit_shouldReturnCorrectBigInt_ofTwoPositiveDebit(String arg1, String arg2, BigDecimal expectedResult) {
        vEcriture.setLibelle("Equilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, arg1, null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, arg2, "33"));
        Assertions.assertThat(vEcriture.getTotalDebit()).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "{0} + {1} should return {2}")
    @CsvSource({
            "10.20, 20, 30.20",
            "10, 20, 30.00",
            "10.20, 20.30, 30.50"
    })
    void getTotalCredit_shouldReturnCorrectBigInt_ofTwoPositiveCredit(String arg1, String arg2, BigDecimal expectedResult) {
        vEcriture.setLibelle("Equilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, null, arg1));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "33", arg2));
        Assertions.assertThat(vEcriture.getTotalCredit()).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "{0} + {1} + {2} = {3} + {4} + {5} should return true")
    @CsvSource({
            "200.50, 100.5, , , 21, 280",
            ", -20.00, 30, -40, 50, "
    })
    void isEquilibree_shouldReturnTrue_ofBalancedDebitAndCredit(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) {
        vEcriture.setLibelle("Equilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, arg1, arg4));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, arg2, arg5));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, arg3, arg6));
        Assertions.assertThat(vEcriture.isEquilibree()).isTrue();
    }

    @ParameterizedTest(name = "{0} + {1} + {2} = {3} + {4} + {5} should return false")
    @CsvSource({
            "10, 20, , , 10, 21",
            ", -20.00, 31, -42, 50.00, "
    })
    void isEqulibree_shouldReturnFalse_ofUnbalancedDebitAndCredit(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) {
        vEcriture.setLibelle("Non équilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, arg1, arg4));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, arg2, arg5));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, arg3, arg6));
        Assertions.assertThat(vEcriture.isEquilibree()).isFalse();
    }

}

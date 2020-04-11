package com.dummy.myerp.business.impl;

import com.dummy.myerp.business.util.Constant;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;


public class ComptabiliteManagerImplTest {

    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();

    @Test
    void checkEcritureComptableUnit_giveOnlyOneLigneEcriture_returnFunctionalExceptionWithCorrectMessage() {
            EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
            vEcritureComptable.setDate(new Date());
            vEcritureComptable.setLibelle("Libelle");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                    null, new BigDecimal(123),
                    null));

        Assertions.assertThatThrownBy(() -> {
            manager.checkEcritureComptableUnit(vEcritureComptable);
        }).isInstanceOf(FunctionalException.class)
        .hasMessageContaining(Constant.ECRITURE_COMPTABLE_MANAGEMENT_RULE_ERRORMSG);
    }

    @Test
    @Tag("RGCompta2")
    @DisplayName("RG compta 2")
    void checkEcritureComptableUnit_giveUnbalancedEcritureComptable_returnFunctionalExceptionForRGCompta2Violation(){
            EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
            vEcritureComptable.setDate(new Date());
            vEcritureComptable.setLibelle("Libelle");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                    null, new BigDecimal(123),
                    null));
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                    null, null,
                    new BigDecimal(1234)));

        Assertions.assertThatThrownBy(() -> {
            manager.checkEcritureComptableUnit(vEcritureComptable);
        }).isInstanceOf(FunctionalException.class)
                .hasMessageContaining(Constant.RG_COMPTA_2_VIOLATION_ERRORMSG);
    }

    @Test
    @Tag("RGCompta3")
    @DisplayName("RG compta 3")
    void checkEcritureComptableUnit_giveNoCreditValue_returnFunctionalExceptionForRGCompta3Violation() {
            EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
            vEcritureComptable.setDate(new Date());
            vEcritureComptable.setLibelle("Libelle");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                    null, new BigDecimal(0),
                    null));
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                    null, new BigDecimal(0),
                    null));

        Assertions.assertThatThrownBy(() -> {
            manager.checkEcritureComptableUnit(vEcritureComptable);
        }).isInstanceOf(FunctionalException.class)
        .hasMessageContaining(Constant.RG_COMPTA_3_VIOLATION_ERRORMSG);
    }

}

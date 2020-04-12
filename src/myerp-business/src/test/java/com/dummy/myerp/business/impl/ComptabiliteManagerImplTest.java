package com.dummy.myerp.business.impl;

import com.dummy.myerp.business.config.BusinessContextBeansTest;
import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.business.impl.manager.ComptabiliteManagerImpl;
import com.dummy.myerp.business.util.Constant;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BusinessContextBeansTest.class})
public class ComptabiliteManagerImplTest {


    @Autowired
    private BusinessProxy businessProxy;

    @Autowired
    private DaoProxy daoProxy;

    @Autowired
    private TransactionManager transactionManager;

    @Autowired
    private ComptabiliteDao comptabiliteDao;

    private ComptabiliteManagerImpl objectToTest;

    private EcritureComptable sampleEcritureComptable;

    @BeforeEach
    void init() {
        objectToTest = new ComptabiliteManagerImpl();
        ComptabiliteManagerImpl.configure(businessProxy, daoProxy, transactionManager);

        sampleEcritureComptable = new EcritureComptable();
        sampleEcritureComptable.setId(1);
        sampleEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        sampleEcritureComptable.setDate(new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime());
        sampleEcritureComptable.setLibelle("Libelle");
        sampleEcritureComptable.setReference("AC-2020/00001");
        sampleEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        sampleEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(123)));

        Mockito.when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
    }

    @AfterEach
    void reset() {
        Mockito.reset(daoProxy);
        Mockito.reset(comptabiliteDao);
        Mockito.reset(transactionManager);
    }

    @Test
    void getListCompteComptable_shouldGetListByCallingDao() {
        List<CompteComptable> compteComptables = new ArrayList<>();
        compteComptables.add(new CompteComptable(1));
        compteComptables.add(new CompteComptable(2));

        Mockito.when(comptabiliteDao.getListCompteComptable()).thenReturn(compteComptables);

        Assertions.assertThat(objectToTest.getListCompteComptable()).isEqualTo(compteComptables);
        Mockito.verify(comptabiliteDao).getListCompteComptable();
    }

    @Test
    void getListJournalComptable_shouldGetListByCallingDao() {
        List<JournalComptable> journalComptables = new ArrayList<>();
        journalComptables.add(new JournalComptable("BQ", "Banque"));
        journalComptables.add(new JournalComptable("FO", "Fournisseur"));

        Mockito.when(comptabiliteDao.getListJournalComptable()).thenReturn(journalComptables);

        Assertions.assertThat(objectToTest.getListJournalComptable()).isEqualTo(journalComptables);
        Mockito.verify(comptabiliteDao).getListJournalComptable();
    }

    @Test
    void getListEcritureComptable_shouldGetListByCallingDao() {
        List<EcritureComptable> ecritureComptables = new ArrayList<>();
        ecritureComptables.add(new EcritureComptable());
        ecritureComptables.add(new EcritureComptable());

        Mockito.when(comptabiliteDao.getListEcritureComptable()).thenReturn(ecritureComptables);

        Assertions.assertThat(objectToTest.getListEcritureComptable()).isEqualTo(ecritureComptables);
        Mockito.verify(comptabiliteDao).getListEcritureComptable();
    }


    @Test
    void addReferenceTest() {
        // Bien se réferer à la JavaDoc de cette méthode !
        /* Le principe :
                1.  Remonter depuis la persitance la dernière valeur de la séquence du journal pour l'année de l'écriture
                    (table sequence_ecriture_comptable)
                2.  * S'il n'y a aucun enregistrement pour le journal pour l'année concernée :
                        1. Utiliser le numéro 1.
                    * Sinon :
                        1. Utiliser la dernière valeur + 1
                3.  Mettre à jour la référence de l'écriture avec la référence calculée (RG_Compta_5)
                4.  Enregistrer (insert/update) la valeur de la séquence en persitance
                    (table sequence_ecriture_comptable)
         */
    }

    @Test
    void checkEcritureComptable_correctNewEcritureComptable_shouldNotThrowException() throws NotFoundException {
        Mockito.when(comptabiliteDao.getEcritureComptableByRef(Mockito.anyString())).thenThrow(new NotFoundException());

        Assertions.assertThatCode(() -> {
            objectToTest.checkEcritureComptable(sampleEcritureComptable);
        }).doesNotThrowAnyException();
    }

    @Test
    void checkEcritureComptable_alreadySavedEcritureComptable_shouldNotThrowException() throws NotFoundException {
        Mockito.when(comptabiliteDao.getEcritureComptableByRef(Mockito.anyString())).thenReturn(sampleEcritureComptable);

        Assertions.assertThatCode(() -> {
            objectToTest.checkEcritureComptable(sampleEcritureComptable);
        }).doesNotThrowAnyException();
    }

    @Test
    void checkEcritureComptable_ecritureComptableDoesNotRespectValidationConstraint_returnFunctionalExceptionWithCorrectMessage() {
        //Add only one LigneEcritureComptable, validation contraints expect at least two lines.
        sampleEcritureComptable.getListLigneEcriture().clear();
        sampleEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));

        Assertions.assertThatThrownBy(() -> {
            objectToTest.checkEcritureComptable(sampleEcritureComptable);
        }).isInstanceOf(FunctionalException.class)
                .hasMessageContaining(Constant.ECRITURE_COMPTABLE_MANAGEMENT_RULE_ERRORMSG);
    }

    @ParameterizedTest(name = "{0} is not a valid reference")
    @ValueSource(strings = {"BQ-201/00002", "B1-2019/00002", "BC2019/00002", "BC-201900002", "BC-2019/000028", "BC-201a/00002", "BC-20125/00002"})
    void checkEcritureComptable_invalidReferenceFormat_returnFunctionalExceptionWithCorrectMessage(String arg) {
        sampleEcritureComptable.setReference(arg);

        Assertions.assertThatThrownBy(() -> {
            objectToTest.checkEcritureComptable(sampleEcritureComptable);
        }).isInstanceOf(FunctionalException.class)
                .hasMessageContaining(Constant.ECRITURE_COMPTABLE_MANAGEMENT_RULE_ERRORMSG);
    }

    @Test
    @Tag("RG")
    @Tag("RG_Compta_2")
    void checkEcritureComptable_unbalancedEcritureComptable_returnFunctionalExceptionForRGCompta2Violation() {
        sampleEcritureComptable.getListLigneEcriture().clear();
        sampleEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        sampleEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(1234)));

        Assertions.assertThatThrownBy(() -> {
            objectToTest.checkEcritureComptable(sampleEcritureComptable);
        }).isInstanceOf(FunctionalException.class)
                .hasMessageContaining(Constant.RG_COMPTA_2_VIOLATION_ERRORMSG);
    }

    @Test
    @Tag("RG")
    @Tag("RG_Compta_3")
    void checkEcritureComptable_noCreditValue_returnFunctionalExceptionForRGCompta3Violation() {
        sampleEcritureComptable.getListLigneEcriture().clear();
        sampleEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(0),
                null));
        sampleEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(0),
                null));

        Assertions.assertThatThrownBy(() -> {
            objectToTest.checkEcritureComptable(sampleEcritureComptable);
        }).isInstanceOf(FunctionalException.class)
                .hasMessageContaining(Constant.RG_COMPTA_3_VIOLATION_ERRORMSG);
    }

    @Test
    @Tag("RG")
    @Tag("RG_Compta_3")
    void checkEcritureComptable_noDebitValue_returnFunctionalExceptionForRGCompta3Violation() {
        sampleEcritureComptable.getListLigneEcriture().clear();
        sampleEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null,
                new BigDecimal(0)));
        sampleEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, null,
                new BigDecimal(0)));

        Assertions.assertThatThrownBy(() -> {
            objectToTest.checkEcritureComptable(sampleEcritureComptable);
        }).isInstanceOf(FunctionalException.class)
                .hasMessageContaining(Constant.RG_COMPTA_3_VIOLATION_ERRORMSG);
    }

    @ParameterizedTest
    @ValueSource(strings = {"BQ-2020/00001", "AC-2019/00001"})
    @Tag("RG")
    @Tag("RG_Compta_5")
    void checkEcritureComptable_invalidReference_returnFunctionalExceptionForRGCompta5Violation(String arg1) {
        sampleEcritureComptable.setReference(arg1);

        Assertions.assertThatThrownBy(() -> {
            objectToTest.checkEcritureComptable(sampleEcritureComptable);
        }).isInstanceOf(FunctionalException.class)
                .hasMessageContaining(Constant.RG_COMPTA_5_VIOLATION_ERRORMSG);
    }

    @Test
    @Tag("RG")
    @Tag("RG_Compta_6")
    void checkEcritureComptable_notUniqueReference_returnFunctionalExceptionForRGCompta6Violation() throws NotFoundException {
        EcritureComptable ecritureComptableWithSameReference = new EcritureComptable();
        ecritureComptableWithSameReference.setReference("AC-2020/00001");

        //getEcritureComptableByRef return an EcritureComptable, that mean the reference is already used by an other EcritureComptable
        Mockito.when(comptabiliteDao.getEcritureComptableByRef(Mockito.anyString())).thenReturn(ecritureComptableWithSameReference);

        Assertions.assertThatThrownBy(() -> {
            objectToTest.checkEcritureComptable(sampleEcritureComptable);
        }).isInstanceOf(FunctionalException.class)
                .hasMessageContaining(Constant.RG_COMPTA_6_VIOLATION_ERRORMSG);
    }

}

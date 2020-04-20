package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.db.AbstractDbConsumer;
import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/com/dummy/myerp/consumer/consumerContext-test.xml"})
public class ComptabiliteDaoImplIT {

    private EcritureComptable testEcritureComptableInDB;

    private SequenceEcritureComptable testSequenceEcritureComptableInDB;

    private ComptabiliteDao objectToTest;

    @BeforeEach
    void init() throws ParseException {
        objectToTest = ComptabiliteDaoImpl.getInstance();
        testSequenceEcritureComptableInDB = new SequenceEcritureComptable(2016, new JournalComptable("AC", "Achat"), 40);
        testEcritureComptableInDB = new EcritureComptable();
        testEcritureComptableInDB.setId(-1);
        testEcritureComptableInDB.setJournal(new JournalComptable("AC", "Achat"));
        testEcritureComptableInDB.setReference("AC-2016/00001");
        testEcritureComptableInDB.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2016-12-31"));
        testEcritureComptableInDB.setLibelle("Cartouches d’imprimante");
        testEcritureComptableInDB.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(606,	"Achats non stockés de matières et fournitures"), "Cartouches d’imprimante", new BigDecimal("43.95"), null));
        testEcritureComptableInDB.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(4456,	"Taxes sur le chiffre d'affaires déductibles"), "TVA 20%", new BigDecimal("8.79"), null));
        testEcritureComptableInDB.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401,	"Fournisseurs"), "Facture F110001", null, new BigDecimal("52.74")));

    }

    @Test
    void applicationContextTest() {
        Assertions.assertThat(AbstractDbConsumer.getDaoProxy()).isNotNull();
    }

    @Test
    void getInstanceTest() {
        Assertions.assertThat(objectToTest).isNotNull();
    }

    @Test
    void getListCompteComptableTest() {

        List<CompteComptable> listCompteComptable = objectToTest.getListCompteComptable();

        Assertions.assertThat(listCompteComptable.size()).isEqualTo(7);
        Assertions.assertThat(listCompteComptable)
                .contains(new CompteComptable(401, "Fournisseurs"),
                new CompteComptable(411, "Clients"),
                new CompteComptable(4456, "Taxes sur le chiffre d'affaires déductibles"),
                new CompteComptable(4457, "Taxes sur le chiffre d'affaires collectées par l'entreprise"),
                new CompteComptable(512, "Banque"),
                new CompteComptable(606, "Achats non stockés de matières et fournitures"),
                new CompteComptable(706, "Prestations de services"));
    }

    @Test
    void getListJournalComptableTest() {
        List<JournalComptable> listJournalComptables = objectToTest.getListJournalComptable();

        Assertions.assertThat(listJournalComptables.size()).isEqualTo(4);
        Assertions.assertThat(listJournalComptables)
                .contains(new JournalComptable("AC", "Achat"),
                        new JournalComptable("VE", "Vente"),
                        new JournalComptable("BQ", "Banque"),
                        new JournalComptable("OD", "Opérations Diverses"));

    }

    @Test
    void getListEcritureComptableTest() {
        List<EcritureComptable> listEcritureComptables = objectToTest.getListEcritureComptable();

        Assertions.assertThat(listEcritureComptables.size()).isEqualTo(5);
        Assertions.assertThat(listEcritureComptables)
                .contains(testEcritureComptableInDB);
    }

    @Test
    void getEcritureComptable_idExistInDB_returnCorrectEcritureComptable() throws NotFoundException {
        EcritureComptable ecritureComptableResult = objectToTest.getEcritureComptable(testEcritureComptableInDB.getId());

        Assertions.assertThat(ecritureComptableResult).isEqualTo(testEcritureComptableInDB);
    }

    @Test
    void getEcritureComptable_idNotExistInDB_throwNotFoundException() {

        Assertions.assertThatThrownBy(() -> {
            objectToTest.getEcritureComptable(-200);
        }).isInstanceOf(NotFoundException.class);
    }

    @Test
    void getEcritureComptableByRef_idExistInDB_returnCorrectEcritureComptable() throws NotFoundException {
        EcritureComptable ecritureComptableResult = objectToTest.getEcritureComptableByRef(testEcritureComptableInDB.getReference());

        Assertions.assertThat(ecritureComptableResult).isEqualTo(testEcritureComptableInDB);
    }

    @Test
    void getEcritureComptableByRef_idNotExistInDB_throwNotFoundException() {

        Assertions.assertThatThrownBy(() -> {
            objectToTest.getEcritureComptableByRef("AC-2016/00300");
        }).isInstanceOf(NotFoundException.class);
    }

    @Test
    void loadListLigneEcritureTest() {
        testEcritureComptableInDB.getListLigneEcriture().clear();
        objectToTest.loadListLigneEcriture(testEcritureComptableInDB);

        Assertions.assertThat(testEcritureComptableInDB.getListLigneEcriture().size()).isEqualTo(3);
        Assertions.assertThat(testEcritureComptableInDB.getListLigneEcriture())
                .contains(new LigneEcritureComptable(new CompteComptable(606,	"Achats non stockés de matières et fournitures"), "Cartouches d’imprimante", new BigDecimal("43.95"), null));
    }

    @Test
    @Transactional
    @Rollback
    void insertEcritureComptableTest() throws ParseException, NotFoundException {
        EcritureComptable ecritureComptableToInsert = new EcritureComptable();
        ecritureComptableToInsert.setId(-10);
        ecritureComptableToInsert.setJournal(new JournalComptable("OD", "Opérations Diverses"));
        ecritureComptableToInsert.setReference("OD-2016/00006");
        ecritureComptableToInsert.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2016-12-31"));
        ecritureComptableToInsert.setLibelle("Remboursement");
        ecritureComptableToInsert.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(706,	"Prestations de services"), "TMA Appli Xxx", new BigDecimal("500.00"), null));
        ecritureComptableToInsert.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(706,	"Prestations de services"), "TMA LOGICIEL", new BigDecimal("300.00"), null));

        objectToTest.insertEcritureComptable(ecritureComptableToInsert);

        EcritureComptable insertedEcritureComptable = objectToTest.getEcritureComptableByRef("OD-2016/00006");

        Assertions.assertThat(insertedEcritureComptable).isEqualTo(ecritureComptableToInsert);
    }

    @Test
    @Transactional
    @Rollback
    void updateEcritureComptableTest() throws NotFoundException {
        testEcritureComptableInDB.setLibelle("Libelle changé");

        objectToTest.updateEcritureComptable(testEcritureComptableInDB);
        EcritureComptable newTestEcritureComptableInDB = objectToTest.getEcritureComptableByRef(testEcritureComptableInDB.getReference());

        Assertions.assertThat(newTestEcritureComptableInDB).isEqualTo(testEcritureComptableInDB);
    }

    @Test
    @Transactional
    @Rollback
    void deleteEcritureComptableTest() {
        objectToTest.deleteEcritureComptable(testEcritureComptableInDB.getId());
        List<EcritureComptable> ecritureComptableList = objectToTest.getListEcritureComptable();

        Assertions.assertThat(ecritureComptableList).doesNotContain(testEcritureComptableInDB);
    }

    @Test
    void getSequenceByYearAndJournalCode() throws NotFoundException {
        SequenceEcritureComptable resultSequence = objectToTest.getSequenceByYearAndJournalCode(2016, "AC");

        Assertions.assertThat(resultSequence).isEqualTo(testSequenceEcritureComptableInDB);
    }

    @Test
    @Transactional
    @Rollback
    void insertNewSequenceTest() throws NotFoundException {
        SequenceEcritureComptable sequenceToInsert = new SequenceEcritureComptable();
        sequenceToInsert.setJournal(new JournalComptable("AC", "Achat"));
        sequenceToInsert.setDerniereValeur(1);
        sequenceToInsert.setAnnee(2017);

        objectToTest.insertNewSequence(sequenceToInsert);
        SequenceEcritureComptable insertedSequence = objectToTest.getSequenceByYearAndJournalCode(sequenceToInsert.getAnnee(), sequenceToInsert.getJournal().getCode());

        Assertions.assertThat(insertedSequence).isEqualTo(sequenceToInsert);
    }

    @Test
    @Transactional
    @Rollback
    void updateSequenceEcritureComptableTest() throws NotFoundException {
        testSequenceEcritureComptableInDB.setDerniereValeur(50);

        objectToTest.updateSequenceEcritureComptable(testSequenceEcritureComptableInDB);
        SequenceEcritureComptable newTestSequenceEcritureComptableInDB = objectToTest.getSequenceByYearAndJournalCode(testSequenceEcritureComptableInDB.getAnnee(), testSequenceEcritureComptableInDB.getJournal().getCode());

        Assertions.assertThat(newTestSequenceEcritureComptableInDB).isEqualTo(testSequenceEcritureComptableInDB);
    }

}

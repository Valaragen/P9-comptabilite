package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.CompteComptableRM;
import com.dummy.myerp.consumer.db.AbstractDbConsumer;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/com/dummy/myerp/consumer/sqlContext.xml"})
public class ComptabiliteDaoImplTest {

    private String SQLgetListCompteComptable;
    private String SQLgetListJournalComptable;
    private String SQLgetListEcritureComptable;
    private static String SQLgetEcritureComptable;
    private static String SQLgetEcritureComptableByRef;
    private static String SQLloadListLigneEcriture;
    private static String SQLinsertEcritureComptable;
    private static String SQLinsertListLigneEcritureComptable;
    private static String SQLupdateEcritureComptable;
    private static String SQLdeleteEcritureComptable;
    private static String SQLdeleteListLigneEcritureComptable;
    private static String SQLgetSequenceEcritureComptableByAnneeAndJournalCode;
    private String SQLinsertSequenceEcritureComptable;
    private String SQLupdateSequenceEcritureComptable;
    private String SQLdeleteSequenceEcritureComptable;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private ComptabiliteDao objectToTest;

    @BeforeEach
    void init() {
        objectToTest = ComptabiliteDaoImpl.getInstance();
    }

    @AfterEach
    void clear() {
        Mockito.reset(jdbcTemplate);
    }

    @Test
    @Disabled
    void applicationContextTest() {
        Assertions.assertThat(AbstractDbConsumer.getDaoProxy()).isNotNull();
    }

    @Test
    @Disabled
    void getInstanceTest() {
        Assertions.assertThat(objectToTest).isNotNull();
    }

    @Test
    @Disabled
    void getListCompteComptable_shouldQueryWithGoodArgument_whenMethodIsCalled() {

        objectToTest.getListCompteComptable();

        Mockito.verify(jdbcTemplate).query(SQLgetListCompteComptable, new CompteComptableRM());
    }

    @Test
    @Disabled
    void getListJournalComptable_shouldReturnNotNullList_whenMethodIsCalled() {
        Assertions.assertThat(objectToTest.getListJournalComptable()).isNotNull();
    }

    @Test
    @Disabled
    void getListEcritureComptable_shouldReturnNotNullList_whenMethodIsCalled() {
        Assertions.assertThat(objectToTest.getListEcritureComptable()).isNotNull();
    }

    @Test
    @Disabled
    void getEcritureComptable_shouldReturnNotNullList_whenMethodIsCalled() throws NotFoundException {
        Assertions.assertThat(objectToTest.getEcritureComptable(0)).isNotNull();
    }
}

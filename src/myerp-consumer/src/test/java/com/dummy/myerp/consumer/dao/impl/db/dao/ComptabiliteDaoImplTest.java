package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.db.AbstractDbConsumer;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/com/dummy/myerp/consumer/consumerContext.xml"})
public class ComptabiliteDaoImplTest {

    private ComptabiliteDao objectToTest;

    @BeforeEach
    void init() {
        objectToTest = ComptabiliteDaoImpl.getInstance();
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
    @Disabled
    void getListCompteComptable_shouldReturnNotNullList_whenMethodIsCalled() {
        Assertions.assertThat(objectToTest.getListCompteComptable()).isNotNull();
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

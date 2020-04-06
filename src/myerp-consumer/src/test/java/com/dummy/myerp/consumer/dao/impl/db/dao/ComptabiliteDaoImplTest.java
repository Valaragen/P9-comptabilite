package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComptabiliteDaoImplTest {

    private ComptabiliteDao objectToTest;

    @BeforeEach
    void init() {
        objectToTest = ComptabiliteDaoImpl.getInstance();
    }

    @Test
    void getInstanceTest() {
        Assertions.assertThat(objectToTest).isNotNull();
    }

    @Test
    void getListCompteComptableTest() {
        System.out.println(objectToTest.getListCompteComptable());
    }
}

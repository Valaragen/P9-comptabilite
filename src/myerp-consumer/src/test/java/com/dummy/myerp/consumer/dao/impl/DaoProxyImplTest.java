package com.dummy.myerp.consumer.dao.impl;

import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DaoProxyImplTest {

    DaoProxy objectToTest = DaoProxyImpl.getInstance();

    @Test
    void getInstanceTest() {
        Assertions.assertThat(objectToTest).isNotNull();
    }

    @Test
    void getComptabiliteDaoTest() {
        Assertions.assertThat(objectToTest.getComptabiliteDao()).isNotNull();
    }
}

package com.dummy.myerp.consumer.dao.impl;

import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/com/dummy/myerp/consumer/consumerContext.xml"})
public class DaoProxyImplTest {

    DaoProxy objectToTest = DaoProxyImpl.getInstance();

    @Test
    void getInstance_shouldReturnInstance_whenMethodCalled() {
        Assertions.assertThat(objectToTest).isNotNull();
    }

    @Test
    void getComptabiliteDao_shouldReturnDao_whenContextIsCorrectlyLoaded() {
        Assertions.assertThat(objectToTest.getComptabiliteDao()).isNotNull();
    }
}

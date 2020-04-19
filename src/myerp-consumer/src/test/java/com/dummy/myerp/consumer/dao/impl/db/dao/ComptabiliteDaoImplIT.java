package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.db.AbstractDbConsumer;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/com/dummy/myerp/consumer/consumerContext.xml"})
public class ComptabiliteDaoImplIT {

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
    void getListCompteComptableTest() {

        List<CompteComptable> listCompteComptable = objectToTest.getListCompteComptable();

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
        List<CompteComptable> listJournalComptables = objectToTest.getListCompteComptable();

        Assertions.assertThat(listJournalComptables)
                .contains(new CompteComptable(401, "Fournisseurs"),
                        new CompteComptable(411, "Clients"),
                        new CompteComptable(4456, "Taxes sur le chiffre d'affaires déductibles"),
                        new CompteComptable(4457, "Taxes sur le chiffre d'affaires collectées par l'entreprise"),
                        new CompteComptable(512, "Banque"),
                        new CompteComptable(606, "Achats non stockés de matières et fournitures"),
                        new CompteComptable(706, "Prestations de services"));
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

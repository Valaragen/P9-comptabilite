package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.CompteComptableRM;
import com.dummy.myerp.consumer.db.AbstractDbConsumer;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    void getListCompteComptable_shouldQueryWithGoodArgument_whenMethodIsCalled() {

        List<CompteComptable> result = objectToTest.getListCompteComptable();

        List<Integer> resultCompteComptablesNumero = result.stream().map(CompteComptable::getNumero).collect(Collectors.toList());
        Assertions.assertThat(resultCompteComptablesNumero).contains(401, 411, 4456, 4457, 512, 606, 706);
        List<String> resultCompteComptablesLibelle = result.stream().map(CompteComptable::getLibelle).collect(Collectors.toList());
        Assertions.assertThat(resultCompteComptablesLibelle).contains("Fournisseurs", "Clients", "Taxes sur le chiffre d'affaires déductibles", "Taxes sur le chiffre d'affaires collectées par l'entreprise", "Banque", "Achats non stockés de matières et fournitures", "Prestations de services");
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

package com.dummy.myerp.model.bean.comptabilite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Tag("CompteComptableTest")
public class CompteComptableTest {

    private CompteComptable objectToTest;
    private CompteComptable fournisseur = new CompteComptable(4081, "Fournisseurs");
    private CompteComptable clientVentes = new CompteComptable(4111, "Clients - Ventes de biens ou de prestations de services");
    private CompteComptable clientAvances = new CompteComptable(4191, "Clients - Avances et acomptes reçus sur commandes");
    private List<CompteComptable> compteComptableList;

    @BeforeEach
    void init() {
        objectToTest = new CompteComptable();
        compteComptableList = new ArrayList<>();
        compteComptableList.add(fournisseur);
        compteComptableList.add(clientVentes);
        compteComptableList.add(clientAvances);
    }

    @Test
    void getByNumero_giveAnExistingCompteNumber_returnTheAssociatedCompteComptable() {
        objectToTest = CompteComptable.getByNumero(compteComptableList, 4081);

        Assertions.assertThat(objectToTest).isEqualTo(fournisseur);
    }

    @Test
    void getByNumero_giveANonExistingCompteNumber_returnNull() {
        objectToTest = CompteComptable.getByNumero(compteComptableList, 1000);

        Assertions.assertThat(objectToTest).isNull();
    }

    @Test
    void toString_shouldNotThrowException_whenCalled() {
        Assertions.assertThatCode(() -> objectToTest.toString()).doesNotThrowAnyException();
    }

}

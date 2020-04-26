package com.dummy.myerp.model.bean.comptabilite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SequenceEcritureComptableTest {

    private SequenceEcritureComptable objectToTest;

    @BeforeEach
    void init() {
        objectToTest = new SequenceEcritureComptable();
        objectToTest.setDerniereValeur(10);
        objectToTest.setAnnee(2020);
        objectToTest.setJournal(new JournalComptable());
    }

    @Test
    void toString_shouldNotThrowException_whenCalled() {
        Assertions.assertThatCode(() -> objectToTest.toString()).doesNotThrowAnyException();
    }
}

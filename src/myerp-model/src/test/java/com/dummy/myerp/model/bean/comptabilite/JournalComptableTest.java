package com.dummy.myerp.model.bean.comptabilite;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JournalComptableTest {

    private JournalComptable JCBanque = new JournalComptable("BQ", "Banque");
    private JournalComptable JCOperationsDiverses = new JournalComptable("OP", "Opérations diverses");

    private List<JournalComptable> journalComptableList = new ArrayList<>();

    @BeforeEach
    void init() {
        journalComptableList.add(JCBanque);
        journalComptableList.add(JCOperationsDiverses);
    }

    @Test
    void getByCode_giveAnExistingCompteNumber_returnTheAssociatedCompteComptable() {
        JournalComptable result = JournalComptable.getByCode(journalComptableList, "BQ");

        Assertions.assertThat(result).isEqualTo(JCBanque);
    }

    @Test
    void getByCode_giveANonExistingCompteNumber_returnNull() {
        JournalComptable result = JournalComptable.getByCode(journalComptableList, "NONEXISTING");

        Assertions.assertThat(result).isNull();
    }
}

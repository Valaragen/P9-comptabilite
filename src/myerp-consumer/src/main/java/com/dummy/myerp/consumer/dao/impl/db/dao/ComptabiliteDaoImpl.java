package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.*;
import com.dummy.myerp.consumer.db.AbstractDbConsumer;
import com.dummy.myerp.consumer.db.DataSourcesEnum;
import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;
import java.util.List;


/**
 * Implémentation de l'interface {@link ComptabiliteDao}
 */
public class ComptabiliteDaoImpl extends AbstractDbConsumer implements ComptabiliteDao {

    // ==================== Constructeurs ====================
    /**
     * Instance unique de la classe (design pattern Singleton)
     */
    private static final ComptabiliteDaoImpl INSTANCE = new ComptabiliteDaoImpl();
    /**
     * SQLgetListCompteComptable
     */
    private static String sqlGetListCompteComptable;
    /**
     * SQLgetListJournalComptable
     */
    private static String sqlGetListJournalComptable;


    // ==================== Méthodes ====================
    /**
     * SQLgetListEcritureComptable
     */
    private static String sqlGetListEcritureComptable;
    /**
     * SQLgetEcritureComptable
     */
    private static String sqlGetEcritureComptable;
    /**
     * SQLgetEcritureComptableByRef
     */
    private static String sqlGetEcritureComptableByRef;
    /**
     * SQLloadListLigneEcriture
     */
    private static String sqlLoadListLigneEcriture;
    /**
     * SQLinsertEcritureComptable
     */
    private static String sqlInsertEcritureComptable;
    /**
     * SQLinsertListLigneEcritureComptable
     */
    private static String sqlInsertListLigneEcritureComptable;

    // ==================== EcritureComptable - GET ====================
    /**
     * SQLupdateEcritureComptable
     */
    private static String sqlUpdateEcritureComptable;
    /**
     * SQLdeleteEcritureComptable
     */
    private static String sqlDeleteEcritureComptable;
    /**
     * SQLdeleteListLigneEcritureComptable
     */
    private static String sqlDeleteListLigneEcritureComptable;

    private static String sqlGetSequenceEcritureComptableByAnneeAndJournalCode;

    private static String sqlInsertSequenceEcritureComptable;

    private static String sqlUpdateSequenceEcritureComptable;


    /**
     * Constructeur.
     */
    protected ComptabiliteDaoImpl() {
        super();
    }

    /**
     * Renvoie l'instance unique de la classe (design pattern Singleton).
     *
     * @return {@link ComptabiliteDaoImpl}
     */
    public static ComptabiliteDaoImpl getInstance() {
        return ComptabiliteDaoImpl.INSTANCE;
    }

    public static void setSqlGetListCompteComptable(String pSQLgetListCompteComptable) {
        sqlGetListCompteComptable = pSQLgetListCompteComptable;
    }

    @Override
    public List<CompteComptable> getListCompteComptable() {
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.getDataSource(DataSourcesEnum.MYERP));
        CompteComptableRM vRM = new CompteComptableRM();
        return vJdbcTemplate.query(sqlGetListCompteComptable, vRM);
    }

    public static void setSqlGetListJournalComptable(String pSQLgetListJournalComptable) {
        sqlGetListJournalComptable = pSQLgetListJournalComptable;
    }

    @Override
    public List<JournalComptable> getListJournalComptable() {
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.getDataSource(DataSourcesEnum.MYERP));
        JournalComptableRM vRM = new JournalComptableRM();
        return vJdbcTemplate.query(sqlGetListJournalComptable, vRM);
    }

    public static void setSqlGetListEcritureComptable(String pSQLgetListEcritureComptable) {
        sqlGetListEcritureComptable = pSQLgetListEcritureComptable;
    }

    @Override
    public List<EcritureComptable> getListEcritureComptable() {
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.getDataSource(DataSourcesEnum.MYERP));
        EcritureComptableRM vRM = new EcritureComptableRM();
        return vJdbcTemplate.query(sqlGetListEcritureComptable, vRM);
    }

    public static void setSqlGetEcritureComptable(String pSQLgetEcritureComptable) {
        sqlGetEcritureComptable = pSQLgetEcritureComptable;
    }


    // ==================== EcritureComptable - INSERT ====================

    @Override
    public EcritureComptable getEcritureComptable(Integer pId) throws NotFoundException {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue("id", pId);
        EcritureComptableRM vRM = new EcritureComptableRM();
        EcritureComptable vBean;
        try {
            vBean = vJdbcTemplate.queryForObject(sqlGetEcritureComptable, vSqlParams, vRM);
        } catch (EmptyResultDataAccessException vEx) {
            throw new NotFoundException("EcritureComptable non trouvée : id=" + pId);
        }
        return vBean;
    }

    public static void setSqlGetEcritureComptableByRef(String pSQLgetEcritureComptableByRef) {
        sqlGetEcritureComptableByRef = pSQLgetEcritureComptableByRef;
    }

    @Override
    public EcritureComptable getEcritureComptableByRef(String pReference) throws NotFoundException {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue("reference", pReference);
        EcritureComptableRM vRM = new EcritureComptableRM();
        EcritureComptable vBean;
        try {
            vBean = vJdbcTemplate.queryForObject(sqlGetEcritureComptableByRef, vSqlParams, vRM);
        } catch (EmptyResultDataAccessException vEx) {
            throw new NotFoundException("EcritureComptable non trouvée : reference=" + pReference);
        }
        return vBean;
    }

    public static void setSqlLoadListLigneEcriture(String pSQLloadListLigneEcriture) {
        sqlLoadListLigneEcriture = pSQLloadListLigneEcriture;
    }

    @Override
    public void loadListLigneEcriture(EcritureComptable pEcritureComptable) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue("ecriture_id", pEcritureComptable.getId());
        LigneEcritureComptableRM vRM = new LigneEcritureComptableRM();
        List<LigneEcritureComptable> vList = vJdbcTemplate.query(sqlLoadListLigneEcriture, vSqlParams, vRM);
        pEcritureComptable.getListLigneEcriture().clear();
        pEcritureComptable.getListLigneEcriture().addAll(vList);
    }

    public static void setSqlInsertEcritureComptable(String pSQLinsertEcritureComptable) {
        sqlInsertEcritureComptable = pSQLinsertEcritureComptable;
    }


    // ==================== EcritureComptable - UPDATE ====================

    @Override
    public void insertEcritureComptable(EcritureComptable pEcritureComptable) {
        // ===== Ecriture Comptable
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue("journal_code", pEcritureComptable.getJournal().getCode());
        vSqlParams.addValue("reference", pEcritureComptable.getReference());
        vSqlParams.addValue("date", pEcritureComptable.getDate(), Types.DATE);
        vSqlParams.addValue("libelle", pEcritureComptable.getLibelle());

        vJdbcTemplate.update(sqlInsertEcritureComptable, vSqlParams);

        // ----- Récupération de l'id
        Integer vId = this.queryGetSequenceValuePostgreSQL(DataSourcesEnum.MYERP, "myerp.ecriture_comptable_id_seq",
                Integer.class);
        pEcritureComptable.setId(vId);

        // ===== Liste des lignes d'écriture
        this.insertListLigneEcritureComptable(pEcritureComptable);
    }

    public static void setSqlInsertListLigneEcritureComptable(String pSQLinsertListLigneEcritureComptable) {
        sqlInsertListLigneEcritureComptable = pSQLinsertListLigneEcritureComptable;
    }

    /**
     * Insert les lignes d'écriture de l'écriture comptable
     *
     * @param pEcritureComptable l'écriture comptable
     */
    private void insertListLigneEcritureComptable(EcritureComptable pEcritureComptable) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue("ecriture_id", pEcritureComptable.getId());

        int vLigneId = 0;
        for (LigneEcritureComptable vLigne : pEcritureComptable.getListLigneEcriture()) {
            vLigneId++;
            vSqlParams.addValue("ligne_id", vLigneId);
            vSqlParams.addValue("compte_comptable_numero", vLigne.getCompteComptable().getNumero());
            vSqlParams.addValue("libelle", vLigne.getLibelle());
            vSqlParams.addValue("debit", vLigne.getDebit());

            vSqlParams.addValue("credit", vLigne.getCredit());

            vJdbcTemplate.update(sqlInsertListLigneEcritureComptable, vSqlParams);
        }
    }


    // ==================== EcritureComptable - DELETE ====================

    public static void setSqlUpdateEcritureComptable(String pSQLupdateEcritureComptable) {
        sqlUpdateEcritureComptable = pSQLupdateEcritureComptable;
    }

    @Override
    public void updateEcritureComptable(EcritureComptable pEcritureComptable) {
        // ===== Ecriture Comptable
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue("id", pEcritureComptable.getId());
        vSqlParams.addValue("journal_code", pEcritureComptable.getJournal().getCode());
        vSqlParams.addValue("reference", pEcritureComptable.getReference());
        vSqlParams.addValue("date", pEcritureComptable.getDate(), Types.DATE);
        vSqlParams.addValue("libelle", pEcritureComptable.getLibelle());

        vJdbcTemplate.update(sqlUpdateEcritureComptable, vSqlParams);

        // ===== Liste des lignes d'écriture
        this.deleteListLigneEcritureComptable(pEcritureComptable.getId());
        this.insertListLigneEcritureComptable(pEcritureComptable);
    }

    public static void setSqlDeleteEcritureComptable(String pSQLdeleteEcritureComptable) {
        sqlDeleteEcritureComptable = pSQLdeleteEcritureComptable;
    }

    @Override
    public void deleteEcritureComptable(Integer pId) {
        // ===== Suppression des lignes d'écriture
        this.deleteListLigneEcritureComptable(pId);

        // ===== Suppression de l'écriture
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue("id", pId);
        vJdbcTemplate.update(sqlDeleteEcritureComptable, vSqlParams);
    }

    public static void setSqlDeleteListLigneEcritureComptable(String pSQLdeleteListLigneEcritureComptable) {
        sqlDeleteListLigneEcritureComptable = pSQLdeleteListLigneEcritureComptable;
    }

    /**
     * Supprime les lignes d'écriture de l'écriture comptable d'id {@code pEcritureId}
     *
     * @param pEcritureId id de l'écriture comptable
     */
    private void deleteListLigneEcritureComptable(Integer pEcritureId) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue("ecriture_id", pEcritureId);
        vJdbcTemplate.update(sqlDeleteListLigneEcritureComptable, vSqlParams);
    }

    public static void setSqlGetSequenceEcritureComptableByAnneeAndJournalCode(String pSQLgetSequenceEcritureComptableByAnneeAndJournalCode) {
        sqlGetSequenceEcritureComptableByAnneeAndJournalCode = pSQLgetSequenceEcritureComptableByAnneeAndJournalCode;
    }

    @Override
    public SequenceEcritureComptable getSequenceByYearAndJournalCode(Integer year, String code) throws NotFoundException {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue("annee", year);
        vSqlParams.addValue("journal_code", code);

        SequenceEcritureComptableRM vRM = new SequenceEcritureComptableRM();
        SequenceEcritureComptable vBean;
        try {
            vBean = vJdbcTemplate.queryForObject(sqlGetSequenceEcritureComptableByAnneeAndJournalCode, vSqlParams, vRM);
        } catch (EmptyResultDataAccessException vEx) {
            throw new NotFoundException("SequenceEcriture non trouvée : journal_code=" + code + " annee=" + year);
        }
        return vBean;
    }

    public static void setSqlInsertSequenceEcritureComptable(String pSQLinsertSequenceEcritureComptable) {
        sqlInsertSequenceEcritureComptable = pSQLinsertSequenceEcritureComptable;
    }

    @Override
    public void insertNewSequence(SequenceEcritureComptable sequenceEcritureComptable) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue("annee", sequenceEcritureComptable.getAnnee());
        vSqlParams.addValue("journal_code", sequenceEcritureComptable.getJournal().getCode());

        vSqlParams.addValue("derniere_valeur", sequenceEcritureComptable.getDerniereValeur());

        vJdbcTemplate.update(sqlInsertSequenceEcritureComptable, vSqlParams);
    }

    public static void setSqlUpdateSequenceEcritureComptable(String pSQLupdateSequenceEcritureComptable) {
        sqlUpdateSequenceEcritureComptable = pSQLupdateSequenceEcritureComptable;
    }

    @Override
    public void updateSequenceEcritureComptable(SequenceEcritureComptable sequenceEcritureComptable) {
        // ===== Ecriture Comptable
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue("annee", sequenceEcritureComptable.getAnnee());
        vSqlParams.addValue("journal_code", sequenceEcritureComptable.getJournal().getCode());

        vSqlParams.addValue("derniere_valeur", sequenceEcritureComptable.getDerniereValeur());

        vJdbcTemplate.update(sqlUpdateSequenceEcritureComptable, vSqlParams);

    }
}

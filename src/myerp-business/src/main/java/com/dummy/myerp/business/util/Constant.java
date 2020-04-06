package com.dummy.myerp.business.util;

public class Constant {
    public static String ECRITURE_COMPTABLE_MANAGEMENT_RULE_ERRORMSG = "L'écriture comptable ne respecte pas les règles de gestion.";
    public static String ECRITURE_COMPTABLE_VALIDATION_CONSTRAINT_ERRORMSG = "L'écriture comptable ne respecte pas les contraintes de validation.";
    public static String RG_COMPTA_2_VIOLATION_ERRORMSG = "L'écriture comptable n'est pas équilibrée. la somme des montants au crédit des lignes d'écriture doit être égale à la somme des montants au débit.";
    public static String RG_COMPTA_3_VIOLATION_ERRORMSG = "L'écriture comptable doit avoir au moins deux lignes : une ligne au débit et une ligne au crédit.";
}

package fr.perso.project.android.powerbapplication.model

import fr.perso.project.android.powerbapplication.model.enums.ETransactionType
import java.io.Serializable

/**
 * Created on 25/04/2020 - 11:41.
 * Transaction class to describe transaction object
 *
 * @author : JEAN-LOUIS Thessalène
 */
data class Transaction (val date : String, val libelle : String, val amount: Int, val transactionType : ETransactionType) :
    Serializable

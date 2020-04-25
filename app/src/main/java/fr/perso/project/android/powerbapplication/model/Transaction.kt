package fr.perso.project.android.powerbproject.model

import fr.perso.project.android.powerbapplication.model.enums.ETransactionCategory
import fr.perso.project.android.powerbproject.model.enums.ETransactionType
import java.util.*

/**
 * Created on 25/04/2020 - 11:41.
 * Transaction class to describe transaction object
 *
 * @author : JEAN-LOUIS Thessalène
 */
class Transaction (val date : Calendar, val libelle : String, val amount: Int, val transactionType : ETransactionType, val transactionCategory : ETransactionCategory = ETransactionCategory.UNKNOWN){

    override fun toString(): String {
        return "${libelle} ${transactionCategory.name} - ${transactionType.name} : $amount"
    }
}
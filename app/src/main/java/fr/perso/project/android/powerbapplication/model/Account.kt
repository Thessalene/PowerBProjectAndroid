package fr.perso.project.android.powerbapplication.model

import fr.perso.project.android.powerbapplication.model.enums.EAccountCategory
import fr.perso.project.android.powerbproject.model.enums.EBankName

/**
 * Created on 25/04/2020 - 11:44.
 * Account class to describe account object
 *
 * @author : JEAN-LOUIS Thessalène
 */
data class Account(
    val number: Int,
    val accountName:String,
    val solde: Double,
    val category: EAccountCategory = EAccountCategory.UNKNOWN,
    var transactions:List<Transaction>? = null,
    val bankName: EBankName= EBankName.INCONNUE){

    override fun toString(): String {
        return "${category.name} - $accountName"
    }
}
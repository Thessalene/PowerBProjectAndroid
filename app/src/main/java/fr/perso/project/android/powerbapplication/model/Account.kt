package fr.perso.project.android.powerbproject.model

import fr.perso.project.android.powerbapplication.model.enums.EAccountCategory
import fr.perso.project.android.powerbproject.model.enums.EBankName

/**
 * Created on 25/04/2020 - 11:44.
 * Account class to describe account object
 *
 * @author : JEAN-LOUIS Thessalène
 */
class Account(
    val number : Int,
    val accountName:String,
    val solde: Int,
    val category: EAccountCategory,
    val transactions:List<Transaction>,
    val bankName : EBankName= EBankName.CAISSE_EPÄRGNE){

    override fun toString(): String {
        return "${number} - $accountName"
    }
}
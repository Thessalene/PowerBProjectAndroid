package fr.perso.project.android.powerbproject.model

import fr.perso.project.android.powerbproject.model.enums.EBankName

/**
 * Created on 25/04/2020 - 11:48.
 * Bank class to describe bank objects
 *
 * @author : JEAN-LOUIS Thessalène
 */
class Bank(val bankName:EBankName, var accounts:List<Account>) {

    override fun toString(): String {
        return "${bankName.libelle} - ${accounts.toString()}"
    }
}
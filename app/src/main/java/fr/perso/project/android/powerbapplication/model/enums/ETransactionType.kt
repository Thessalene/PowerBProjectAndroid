package fr.perso.project.android.powerbapplication.model.enums

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created on 25/04/2020 - 11:43.
 * TODO: Add a class header comment!
 *
 * @author : JEAN-LOUIS Thessalène
 */
@Parcelize
enum class ETransactionType (val transactionTypeName : String): Parcelable{
    CREDIT("Revenus"),
    DEBIT("Dépenses")
}
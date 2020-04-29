package fr.perso.project.android.powerbapplication.model.enums

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created on 25/04/2020 - 16:32.
 * Transaction category enum to describe transaction category
 *
 * @author : JEAN-LOUIS Thessalène
 */
@Parcelize
enum class ETransactionCategory : Parcelable { //Add icon : String/Int to custom icon
    UNKNOWN,
    LOGEMENT,
    TRANSPORT,
    DIVERTISSEMENT,
    SPORT,
    BANK,
    AUTRE
}
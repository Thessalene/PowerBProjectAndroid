package fr.perso.project.android.powerbapplication.model

import android.os.Parcel
import android.os.Parcelable
import fr.perso.project.android.powerbapplication.model.enums.ETransactionCategory
import fr.perso.project.android.powerbapplication.model.enums.ETransactionType

/**
 * Created on 25/04/2020 - 11:41.
 * Transaction class to describe transaction object
 *
 * @author : JEAN-LOUIS Thessalène
 */
data class Transaction (val date : String, val libelle : String, val amount: Int, val eTransactionType : ETransactionType, val eTransactionCategory : ETransactionCategory = ETransactionCategory.UNKNOWN) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readValue(ETransactionType::class.java.classLoader) as ETransactionType,
        parcel.readValue(ETransactionCategory::class.java.classLoader) as ETransactionCategory
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(libelle)
        parcel.writeInt(amount)
        parcel.writeValue(eTransactionType)
        parcel.writeValue(eTransactionCategory)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Transaction> {
        override fun createFromParcel(parcel: Parcel): Transaction {
            return Transaction(parcel)
        }

        override fun newArray(size: Int): Array<Transaction?> {
            return arrayOfNulls(size)
        }
    }

}

package fr.perso.project.android.powerbapplication.Utils

/**
 * Created on 01/05/2020 - 12:34.
 * TODO: Add a class header comment!
 *
 * @author : JEAN-LOUIS Thessalène
 */

fun convertStringCSVDoubleToDouble(value : String) : Double{
    return value.replace("\"", "").replace(",",".").toDouble()
}
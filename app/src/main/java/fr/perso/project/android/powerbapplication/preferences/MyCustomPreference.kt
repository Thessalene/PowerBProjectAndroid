package fr.perso.project.android.powerbapplication.preferences

import android.content.Context
import androidx.preference.Preference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.perso.project.android.powerbapplication.R


/**
 * Created on 01/05/2020 - 16:26.
 * TODO: Add a class header comment!
 *
 * @author : JEAN-LOUIS Thessalène
 */
class MyCustomPreference(context: Context) : Preference(context){

    protected fun onCreateView(parent: ViewGroup): View {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return li.inflate(R.layout.my_custom_preference, parent, false)
    }
}
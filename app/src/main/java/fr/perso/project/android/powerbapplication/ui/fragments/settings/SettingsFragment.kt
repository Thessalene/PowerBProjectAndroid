package fr.perso.project.android.powerbapplication.ui.fragments.settings

import android.os.Bundle
import android.view.*
import androidx.preference.PreferenceFragmentCompat
import fr.perso.project.android.powerbapplication.R
import android.widget.Toast
import androidx.preference.Preference


/**
 * TODO
 */
class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        val key = preference?.key

        when(preference?.key){
            //General settings
            //Account settings
            "add_account_settings" -> Toast.makeText(context, "Ajouter un compte", Toast.LENGTH_SHORT).show()
            //Notifications settings

            else -> Toast.makeText(context, "Autre", Toast.LENGTH_SHORT).show()
        }

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear() //To avoid inheriting from the parent's menu
        inflater.inflate(R.menu.settings_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}

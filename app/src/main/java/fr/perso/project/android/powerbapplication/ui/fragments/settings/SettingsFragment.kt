package fr.perso.project.android.powerbapplication.ui.fragments.settings

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import fr.perso.project.android.powerbapplication.R

/**
 * TODO
 */
class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear() //To avoid inheriting from the parent's menu
        inflater.inflate(R.menu.settings_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}

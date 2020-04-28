package fr.perso.project.android.powerbapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import fr.perso.project.android.powerbapplication.R
import fr.perso.project.android.powerbapplication.ui.main.SectionsPagerAdapter

/**
 * TODO
 */
class AccountFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_account, container, false)

        val tabs: TabLayout = view.findViewById(R.id.tabs)
        val sectionsPagerAdapter = SectionsPagerAdapter(context!!, fragmentManager!!)
        val viewPager: ViewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)
        return view
    }
}

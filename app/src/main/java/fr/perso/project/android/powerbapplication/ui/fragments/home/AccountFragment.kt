package fr.perso.project.android.powerbapplication.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import fr.perso.project.android.powerbapplication.R
import fr.perso.project.android.powerbapplication.adapter.SectionsPagerAdapter

/**
 * TODO
 */
class AccountFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_account, container, false)

        //Tab settings (All, current,...)
        val tabs: TabLayout = view.findViewById(R.id.tabs)
        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                context!!,
                fragmentManager!!
            )
        val viewPager: ViewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)
        return view
    }

}

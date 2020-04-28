package fr.perso.project.android.powerbapplication.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import fr.perso.project.android.powerbapplication.R
import fr.perso.project.android.powerbapplication.ui.home.AccountListFragment

private val TAB_TITLES = arrayOf(
    R.string.lbl_all,
    R.string.lbl_current,
    R.string.lbl_saving,
    R.string.lbl_forecast,
    R.string.lbl_credit
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when(position){
            0 -> AccountListFragment.newInstance(context.getString(R.string.lbl_all))
            1 -> AccountListFragment.newInstance(context.getString(R.string.lbl_current))
            2 -> AccountListFragment.newInstance(context.getString(R.string.lbl_saving))
            3 -> AccountListFragment.newInstance(context.getString(R.string.lbl_forecast))
            4 -> AccountListFragment.newInstance(context.getString(R.string.lbl_credit))
            else -> AccountListFragment.newInstance(context.getString(R.string.lbl_all))
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return TAB_TITLES.size
    }
}
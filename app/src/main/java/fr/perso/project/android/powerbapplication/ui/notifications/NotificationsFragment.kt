package fr.perso.project.android.powerbapplication.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import fr.perso.project.android.powerbapplication.R
import fr.perso.project.android.powerbapplication.ui.dashboard.DashboardFragment
import fr.perso.project.android.powerbapplication.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_notifications.view.*

class NotificationsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        // Setting ViewPager for each Tabs
        val viewPager : ViewPager = view.viewpager as ViewPager
        setupViewPager(viewPager)
        // Set Tabs inside Toolbar
        val tabs : TabLayout = view.result_tabs as TabLayout
        tabs.setupWithViewPager(viewPager)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true);
    }


    // Add Fragments to Tabs
    fun setupViewPager(viewPager : ViewPager) {
        val adapter = Adapter(childFragmentManager)
        adapter.addFragment(HomeFragment(), "Today")
        adapter.addFragment(DashboardFragment(), "Week")
        viewPager.adapter = adapter
    }

    class Adapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager){
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

       override fun getCount() : Int{
            return mFragmentList.size
        }

        fun addFragment(fragment : Fragment, title : String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
}
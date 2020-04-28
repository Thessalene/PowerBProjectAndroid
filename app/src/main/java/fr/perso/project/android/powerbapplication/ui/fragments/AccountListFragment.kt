package fr.perso.project.android.powerbapplication.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fr.perso.project.android.powerbapplication.R
import fr.perso.project.android.powerbapplication.adapter.AccountAdapter
import fr.perso.project.android.powerbproject.mocks.MockClass.Companion.mockAccountList
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Fragment showing account list in function of an account filter (defined by tab selected : Courant, Epargne,...)
 */
class AccountListFragment : Fragment() {

    private var ACCOUNT_FILTER : String = "Tous"

    companion object {
        @JvmStatic
        fun newInstance(accountFilter : String) : AccountListFragment {
           //print("[HOME FRAGMENT]")
           return AccountListFragment().apply {
                    arguments = Bundle().apply {
                        putString("AccountFilter", accountFilter)
                    }
                }
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        var listToDisplay = mockAccountList()
        //println("List to display : $listToDisplay" )
        val adapterAccount = AccountAdapter(context!!, ACCOUNT_FILTER, listToDisplay)

        // Fill recycler view with accountList
        view.recyclerView_accountList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        view.recyclerView_accountList.adapter = adapterAccount
        //println("MOCK BANK LIST " + mockBankList().toString())

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString("AccountFilter")?.let {
            ACCOUNT_FILTER = it
        }
    }
}

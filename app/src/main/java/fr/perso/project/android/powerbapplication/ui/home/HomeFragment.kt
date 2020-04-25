package fr.perso.project.android.powerbapplication.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fr.perso.project.android.powerbapplication.R
import fr.perso.project.android.powerbapplication.adapter.AccountAdapter
import fr.perso.project.android.powerbproject.mocks.MockClass.Companion.mockBankList
import fr.perso.project.android.powerbapplication.model.enums.EAccountCategory
import fr.perso.project.android.powerbproject.mocks.MockClass.Companion.mockAccountList
import fr.perso.project.android.powerbproject.model.Account
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private var ACCOUNT_FILTER : String = "Tous"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        var listToDisplay = mockAccountList()
        val adapterAccount = AccountAdapter(context!!, listToDisplay)

        // Fill recycler view with accountList
        view.recyclerView_accountList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        view.recyclerView_accountList.adapter = adapterAccount

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}
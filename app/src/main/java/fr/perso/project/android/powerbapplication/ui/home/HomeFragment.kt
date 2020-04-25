package fr.perso.project.android.powerbapplication.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fr.perso.project.android.powerbapplication.R
import fr.perso.project.android.powerbapplication.adapter.AccountAdapter
import fr.perso.project.android.powerbapplication.adapter.AccountChoicesAdapter
import fr.perso.project.android.powerbproject.mocks.MockClass.Companion.mockBankList
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Fill recycler view with bankList
        view.recyclerView_accountList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        view.recyclerView_accountList.adapter = AccountAdapter(context!!, mockBankList())
        println("MOCK BANK LIST " + mockBankList().toString())

        //Fill spinner
        val stringArray =  resources.getStringArray(R.array.accountChoices)
        if (view.spinner_account_choice != null) {
            val adapter = ArrayAdapter(
                context!!,
                android.R.layout.simple_spinner_item, stringArray
            )
            view.spinner_account_choice.adapter = adapter
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}
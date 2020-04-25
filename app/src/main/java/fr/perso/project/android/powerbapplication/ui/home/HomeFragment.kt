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
import fr.perso.project.android.powerbproject.model.Bank
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {


    private var ACCOUNT_FILTER : String = "Tous"

    companion object{
        lateinit var listToDisplay : MutableList<Bank>
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        listToDisplay = mockBankList()
        val adapterAccount = AccountAdapter(context!!, listToDisplay)

        // Fill recycler view with bankList
        view.recyclerView_accountList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        view.recyclerView_accountList.adapter = adapterAccount

        //Fill spinner
        val stringArray =  resources.getStringArray(R.array.accountChoices)
        if (view.spinner_account_choice != null) {
            val adapter = ArrayAdapter(
                context!!,
                android.R.layout.simple_spinner_item, stringArray
            )
            view.spinner_account_choice.adapter = adapter
        }

        view.spinner_account_choice.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) =
                Toast.makeText(context, "NOTHING CHANGED", Toast.LENGTH_SHORT).show()

            override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
                Toast.makeText(context, parent?.selectedItem.toString(), Toast.LENGTH_SHORT).show()
                ACCOUNT_FILTER = parent?.selectedItem.toString()
                if(ACCOUNT_FILTER == "Tous"){
                    listToDisplay = mockBankList()
                    println("LISTE TOUT : " +listToDisplay.toString())
                } else {
                    var i = 0
                    for(bank in listToDisplay){
                        bank.accounts = bank.accounts.asSequence().filter{ o -> o.category.name.equals(ACCOUNT_FILTER)}.toList()
                        println("COMPTES : " + bank.accounts.toString())
                        adapterAccount.notifyItemChanged(i)
                        i++
                    }
                    listToDisplay.removeAt(0)
                    println("LISTE AUTRE : " +listToDisplay.toString())

                }
                view.recyclerView_accountList.adapter=adapterAccount
                adapterAccount.notifyDataSetChanged()
                println("ENUM LIST : " + EAccountCategory.values().toString())
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}
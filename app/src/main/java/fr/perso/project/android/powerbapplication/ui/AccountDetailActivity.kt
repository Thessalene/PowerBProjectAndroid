package fr.perso.project.android.powerbapplication.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.VISIBLE
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.perso.project.android.powerbapplication.R
import fr.perso.project.android.powerbapplication.adapter.TransactionAdapter
import fr.perso.project.android.powerbapplication.model.Transaction

import kotlinx.android.synthetic.main.activity_account_detail.*
import kotlinx.android.synthetic.main.content_account_detail.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.io.Serializable

class AccountDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_detail)
        setSupportActionBar(toolbar)

        //Back button on toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Retrieve account info
        intent.apply {
            accountName = getStringExtra("accountName")
            accountSolde = getIntExtra("accountSolde", 0)
            accountTransactions = getSerializableExtra("transactionList") as ArrayList<Transaction>
        }

        title = accountName

        println("-- Account detail activity --")
        println(" $accountName : $accountSolde ")
        println(" Transactions : ${accountTransactions.toString()}")

        // Fill recycler view with accountList
        recyclerView_transactionList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        transactionAdapter = TransactionAdapter(this, accountTransactions!!)
        recyclerView_transactionList.adapter = transactionAdapter

        if(accountTransactions.isNullOrEmpty()){
            tv_error_no_transaction.visibility= VISIBLE
        }

        //Edit text filter
        edt_filter_transaction.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        } )
    }

    fun filter(filter : String){
        var filteredList = ArrayList<Transaction>()

        for(transaction in accountTransactions!!){
            if(transaction.libelle.toLowerCase().contains(filter.toLowerCase()))
                filteredList.add(transaction)
        }
        transactionAdapter.filteredList(filteredList)
    }

    companion object {
        var accountName : String? = "Unknown account"
        var accountSolde : Int = -1
        var accountTransactions: ArrayList<Transaction>? = null

        lateinit var transactionAdapter : TransactionAdapter

        fun newIntent(context: Context, accountName : String, accountSolde : Double, transactionList : List<Transaction?>): Intent {
            val intent = Intent(context, AccountDetailActivity::class.java)
            intent.putExtra("accountName", accountName)
            intent.putExtra("accountSolde", accountSolde)
            intent.putExtra("transactionList", transactionList as Serializable)
            return intent
        }
    }
}
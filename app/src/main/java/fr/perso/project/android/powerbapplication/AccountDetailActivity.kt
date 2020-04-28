package fr.perso.project.android.powerbapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import fr.perso.project.android.powerbapplication.model.Transaction

import kotlinx.android.synthetic.main.activity_account_detail.*

class AccountDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_detail)
        setSupportActionBar(toolbar)

        intent.apply {
            accountName = getStringExtra("position")
            accountSolde = getIntExtra("position", 0)
            accountTransactions = getSerializableExtra("position") as ArrayList<Transaction>?
        }

        println("-- Account detail activity --")
        println(" ${accountName} : ${accountSolde} ")
        println(" Transactions : ${accountTransactions.toString()}")

            fab.setOnClickListener { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        private val INTENT_USER_ID = "user_id"
        var accountName : String? = "Unknown account"
        var accountSolde : Int = -1
        var accountTransactions: ArrayList<Transaction>? = null

        fun newIntent(context: Context, accountName : String, accountSolde : Int, transactionList : ArrayList<Transaction>): Intent {
            val intent = Intent(context, AccountDetailActivity::class.java)
            intent.putExtra("accountName", accountName)
            intent.putExtra("accountSolde", accountSolde)
            intent.putExtra("transactionList", transactionList)
            return intent
        }
    }
}
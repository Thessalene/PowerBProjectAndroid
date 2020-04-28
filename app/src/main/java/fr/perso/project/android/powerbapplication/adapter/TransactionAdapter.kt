package fr.perso.project.android.powerbapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.perso.project.android.powerbapplication.ui.AccountDetailActivity
import fr.perso.project.android.powerbapplication.R
import fr.perso.project.android.powerbapplication.model.enums.EAccountCategory
import fr.perso.project.android.powerbapplication.model.Account
import kotlinx.android.synthetic.main.list_item_account.view.*

/**
 * Created on 25/04/2020 - 13:29.
 * TODO: Add a class header comment!
 *
 * @author : JEAN-LOUIS Thessalène
 */
class AccountAdapter(val context : Context, val accountFilter:String, val accountList : List<Account>) :
    RecyclerView.Adapter<AccountAdapter.ViewHolder>() {

    companion object{
        private const val TYPE_HEADER = 0
        private const val TYPE_ACCOUNT = 1
    }
    var accountOrHeaderList = ArrayList<AccountOrHeader>()

    init {
        convertAccountListToAccountOrHeaderList(accountFilter)
    }

    class AccountOrHeader(val header : Header?, val account : Account?, val type : Int){
        override fun toString(): String {
            return "${header?.accountCategory} - ${account?.accountName}"
        }
    }

    class Header(val accountCategory : String)

    fun convertAccountListToAccountOrHeaderList(accountFilter : String){
        println("---------- CONVERT LIST ----------")
        var listeTempo = ArrayList<AccountOrHeader>()
        println("[Convert] Filter : $accountFilter")

        if(accountFilter == "Tous") {
            for(category in EAccountCategory.values()) {
                var notreListe = this.accountList
                val listeF = accountList.asSequence().filter { o -> o.category==category }.toList()
                if(!listeF.isEmpty())
                    listeTempo.add(AccountOrHeader(Header(category.categoryName), null, TYPE_HEADER))
                //println("CATEGORY : " + category.categoryName)
                for(account in listeF){
                    listeTempo.add(AccountOrHeader(null, account, TYPE_ACCOUNT))
                }
            }
        }else {
            var notreListe = this.accountList
            var listeF = accountList.asSequence().filter { o -> o.category.categoryName==accountFilter }.toList()
            if(!listeF.isEmpty())
                listeTempo.add(AccountOrHeader(Header(accountFilter), null, TYPE_HEADER))
            //println("CATEGORY : " + category.categoryName)
            for(account in listeF){
                listeTempo.add(AccountOrHeader(null, account, TYPE_ACCOUNT))
            }
        }

        accountOrHeaderList.addAll(listeTempo)
        println("[Convert] result : ${accountOrHeaderList}")
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val header = view.tv_bank_header_part as TextView
        val relativeLayoutAccountPart = view.relativeLayout_account_part as RelativeLayout
        val accountNumber = view.tv_account_number as TextView
        val accountName = view.tv_account_name as TextView
        val accountSolde = view.tv_account_solde as TextView
        val accountBankName = view.tv_account_bankName as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_account, parent, false))
    }

    override fun getItemCount(): Int {
        //println("---------- GET ITEM COUNT ${accountOrHeaderList.size} ----------")
        return accountOrHeaderList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = accountOrHeaderList[position]

        if(item.type == TYPE_HEADER){
            holder.header.visibility = VISIBLE
            holder.relativeLayoutAccountPart.visibility = GONE
            holder.header.text = item.header?.accountCategory
        } else {
            holder.header.visibility = GONE
            holder.relativeLayoutAccountPart.visibility = VISIBLE
            holder.accountNumber.text = context.getString(R.string.lbl_number_account,item.account?.number)
            holder.accountName.text = "${item.account?.accountName}"
            holder.accountSolde.text = context.getString(R.string.lbl_amount_euro,item.account?.solde)
            holder.accountBankName.text = item.account?.bankName?.name
            //println("TEST : " + "${item.account?.number}" + "${item.account?.accountName}" + "${item.account?.solde}")
        }

        holder.relativeLayoutAccountPart.setOnClickListener(View.OnClickListener {
            if(item.type == TYPE_ACCOUNT)
                goToAccountDetailActivity(position)
        })
    }

    private fun goToAccountDetailActivity(position : Int) {
        //println("[Account Adapter] Go to Account detail activity ")
        //println("[Account Adapter] Account sent : ${accountOrHeaderList[position].account.toString()}")
        context.startActivity(
            AccountDetailActivity.newIntent(context,
            accountOrHeaderList[position].account?.accountName!!,
            accountOrHeaderList[position].account?.solde!!,
            accountOrHeaderList[position].account?.transactions!!
            ))
    }

    override fun getItemViewType(position: Int): Int {
        return accountOrHeaderList[position].type
    }

}
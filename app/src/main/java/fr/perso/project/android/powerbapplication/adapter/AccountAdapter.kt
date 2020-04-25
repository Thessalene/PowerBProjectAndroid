package fr.perso.project.android.powerbapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.perso.project.android.powerbapplication.R
import fr.perso.project.android.powerbproject.model.Account
import fr.perso.project.android.powerbproject.model.Bank
import kotlinx.android.synthetic.main.list_item_account.view.*
import org.w3c.dom.Text

/**
 * Created on 25/04/2020 - 13:29.
 * TODO: Add a class header comment!
 *
 * @author : JEAN-LOUIS Thessalène
 */
class AccountAdapter(val context : Context, var bankList : List<Bank>) :
    RecyclerView.Adapter<AccountAdapter.ViewHolder>() {

    companion object{
        private const val TYPE_BANK_HEADER = 0
        private const val TYPE_ACCOUNT = 1
        val accountOrHeaderList = ArrayList<AccountOrHeader>()
    }

    init {
        convertBankListToAccountOrHeaderList(bankList)
    }

    class AccountOrHeader(val header : Header?, val account : Account?, val type : Int)

    class Header(val bankName : String)

    fun convertBankListToAccountOrHeaderList(bankList: List<Bank>){

        for(bank in bankList){
            accountOrHeaderList.add(AccountOrHeader(Header(bank.bankName.libelle), null, TYPE_BANK_HEADER))
            for(account in bank.accounts){
                accountOrHeaderList.add(AccountOrHeader(null, account, TYPE_ACCOUNT))
            }
        }
    }

    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val bankHeader = view.tv_bank_header_part as TextView
        val relativeLayoutAccountPart = view.relativeLayout_account_part as RelativeLayout
        val accountNumber = view.tv_account_number as TextView
        val accountName = view.tv_account_name as TextView
        val accountSolde = view.tv_account_solde as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_account, parent, false))
    }

    override fun getItemCount(): Int {
        return accountOrHeaderList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = accountOrHeaderList[position]

//        for(account in accountOrHeaderList){
//            println("ON BIND Bank: " + account.header?.bankName)
//            println("ON BIND Account: " + account.account?.accountName)
//        }

        if(item.type == TYPE_BANK_HEADER){
            holder.bankHeader.visibility = VISIBLE
            holder.relativeLayoutAccountPart.visibility = GONE
            holder.bankHeader.text = item.header?.bankName
        } else {
            holder.bankHeader.visibility = GONE
            holder.relativeLayoutAccountPart.visibility = VISIBLE
            holder.accountNumber.text = context.getString(R.string.lbl_number_account,item.account?.number)
            holder.accountName.text = "${item.account?.accountName}"
            holder.accountSolde.text = context.getString(R.string.lbl_amount_euro,item.account?.solde)
            //println("TEST : " + "${item.account?.number}" + "${item.account?.accountName}" + "${item.account?.solde}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return accountOrHeaderList[position].type
    }

}
package fr.perso.project.android.powerbapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.perso.project.android.powerbapplication.R
import fr.perso.project.android.powerbapplication.model.Transaction
import kotlinx.android.synthetic.main.list_item_transaction.view.*

/**
 * Created on 25/04/2020 - 13:29.
 * TODO: Add a class header comment!
 *
 * @author : JEAN-LOUIS Thessalène
 */
class TransactionAdapter(val context : Context, var transactionList : ArrayList<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val dateTextView = view.tv_date_transaction as TextView
        val libelleTextView = view.tv_libelle as TextView
        val amountTextView = view.tv_amount as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_transaction, parent, false))
    }

    override fun getItemCount(): Int {
        //println("---------- GET ITEM COUNT ${accountOrHeaderList.size} ----------")
        return transactionList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = transactionList.get(position)

        holder.dateTextView.text = item.date
        holder.libelleTextView.text = item.libelle
        holder.amountTextView.text = context.getString(R.string.lbl_amount_euro, item.amount)

    }

    fun filteredList(filteredList: ArrayList<Transaction>) {
        transactionList = filteredList
        notifyDataSetChanged()
    }
}
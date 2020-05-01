package fr.perso.project.android.powerbapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.perso.project.android.powerbapplication.R
import fr.perso.project.android.powerbapplication.model.Transaction
import kotlinx.android.synthetic.main.list_item_transaction.view.*

/**
 * Created on 25/04/2020 - 13:29.
 * Transaction adapter to display the transaction list sorted by date
 *
 * @author : JEAN-LOUIS Thessalène
 */
class TransactionAdapter(val context : Context, var transactionList : ArrayList<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    companion object{
        private const val TYPE_HEADER = 0
        private const val TYPE_TRANSACTION = 1
    }
    var transactionOrHeaderList = ArrayList<TransactionOrHeader>()

    init {
        convertTransactionListToTransactionOrHeaderList()
    }

    /**
     * Inner classes : Transaction Or Header and Header
     */
    data class TransactionOrHeader(val header : Header?, val transaction : Transaction?, val type : Int){
        override fun toString(): String {
            return "${header?.date} - ${transaction?.libelle}"
        }
    }

    inner class Header(val date : String)

    /**
     * Function to convert our transactionList into a TransactionOrHeaderList (our data source)
     */
    private fun convertTransactionListToTransactionOrHeaderList() {
        val listTempo = ArrayList<TransactionOrHeader>()

        //Extract a list of date fro list input
        val dateList= transactionList.asSequence().map{it.date}.distinct().toList()

        for (date in dateList) {
            val filteredList = transactionList.asSequence().filter { o -> o.date == date }.toList()
            if (filteredList.isNotEmpty()) {
                listTempo.add(
                    TransactionOrHeader(
                        Header(date),
                        null,
                        TYPE_HEADER
                    )
                )
                for (transaction in filteredList) {
                    listTempo.add(
                        TransactionOrHeader(
                            null,
                            transaction,
                            TYPE_TRANSACTION
                        )
                    )
                }
            }
        }
        transactionOrHeaderList.addAll(listTempo)
        println("[Convert] result : $transactionOrHeaderList")
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val dateTextView = view.tv_date_transaction as TextView
        val linearTransactionDetail = view.linear_transactionDetail as LinearLayout
        val wordingTextView = view.tv_wording as TextView
        val amountTextView = view.tv_amount as TextView
        val dividerView = view.divider as View
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_transaction, parent, false))
    }

    override fun getItemCount(): Int {
        return transactionOrHeaderList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = transactionOrHeaderList[position]

        if(item.type == TYPE_HEADER){
            holder.dateTextView.visibility = VISIBLE
            holder.linearTransactionDetail.visibility = GONE
            holder.dividerView.visibility=GONE
            holder.dateTextView.text = item.header?.date
        } else {
            holder.dateTextView.visibility = GONE
            holder.linearTransactionDetail.visibility = VISIBLE
            holder.dividerView.visibility=VISIBLE
            holder.wordingTextView.text = item.transaction?.libelle
            holder.amountTextView.text = context.getString(R.string.lbl_amount_euro, item.transaction?.amount)

            //If it's the item below a date header or the last one, we hide the divider
            if(position != transactionOrHeaderList.size-1 && transactionOrHeaderList[position+1].type == TYPE_HEADER ||
                position == transactionOrHeaderList.size-1){
                holder.dividerView.visibility=GONE
            }
        }
    }

    /**
     *Filtered method to update our data list of transaction
     */
    fun filteredList(filteredList: ArrayList<Transaction>) {
        transactionList = filteredList
        notifyDataSetChanged()
    }
}
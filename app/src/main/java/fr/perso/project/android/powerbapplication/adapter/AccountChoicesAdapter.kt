package fr.perso.project.android.powerbapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.perso.project.android.powerbapplication.R
import kotlinx.android.synthetic.main.list_item_account_choices.view.*

/**
 * Created on 25/04/2020 - 12:50.
 * TODO: Add a class header comment!
 *
 * @author : JEAN-LOUIS Thessalène
 */
class AccountChoicesAdapter(val context: Context, val choiceList:Array<String>) : RecyclerView.Adapter<AccountChoicesAdapter.ViewHolder>(){

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        //val buttonLeft = view.button_left as ImageButton
        val accountChoiceTextView = view.tv_account_choice as TextView
        //val buttonRight = view.button_right as ImageButton

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_account_choices, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.accountChoiceTextView.text=choiceList[position]
    }

    override fun getItemCount(): Int {
       return choiceList.size
    }

}
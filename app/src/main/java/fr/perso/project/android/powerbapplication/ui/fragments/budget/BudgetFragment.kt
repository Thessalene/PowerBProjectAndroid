package fr.perso.project.android.powerbapplication.ui.fragments.budget

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import fr.perso.project.android.powerbapplication.R
import fr.perso.project.android.powerbapplication.model.Transaction
import fr.perso.project.android.powerbapplication.model.enums.ETransactionCategory
import fr.perso.project.android.powerbapplication.model.enums.ETransactionType
import fr.perso.project.android.powerbproject.mocks.MockClass.Companion.mockAccountWithTransactionCategorie
import kotlinx.android.synthetic.main.fragment_budget.*
import kotlinx.android.synthetic.main.fragment_budget.view.*


class BudgetFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_budget, container, false)

        val pieChart = root.findViewById(R.id.piechart) as PieChart


        //Calculs des dépenses liées aux différentes catégories
        var transactionsMock = mockAccountWithTransactionCategorie().transactions

        //PieChart
        setupPieChart(pieChart, transactionsMock, ETransactionType.DEBIT)

        root.btn_expenses.setOnClickListener{
            root.btn_incomes.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
            root.btn_expenses.setBackgroundColor(resources.getColor(R.color.Light_pink))
            //PieChart
            setupPieChart(pieChart, transactionsMock, ETransactionType.DEBIT)
            btn_expenses.isEnabled = false
            btn_incomes.isEnabled = true
        }

        root.btn_incomes.setOnClickListener{
            root.btn_incomes.setBackgroundColor(resources.getColor(R.color.Light_pink))
            root.btn_expenses.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
            btn_incomes.isEnabled = false
            btn_expenses.isEnabled = true
            //PieChart
            setupPieChart(pieChart, transactionsMock, ETransactionType.CREDIT)
        }

        return root
    }

    fun setupPieChart(pieChart : PieChart, transactionsMock : ArrayList<Transaction>, filter : ETransactionType){
        //Init
        val NoOfEmp = ArrayList<PieEntry>()

        println("TRANSACTION LIST : " + transactionsMock.toString())

        println("Somme : " + calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.UNKNOWN, filter))

        //Colors
        var colorClassArray = Array<Int>(7){ Color.DKGRAY; Color.MAGENTA; Color.BLUE; Color.RED; Color.YELLOW; Color.GRAY; Color.GREEN}

        NoOfEmp.add(PieEntry(calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.UNKNOWN, filter).toFloat(), context!!.getString(R.string.lbl_no_categorised)))
        NoOfEmp.add(PieEntry(calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.LOGEMENT, filter).toFloat(), context!!.getString(R.string.lbl_housing)))
        NoOfEmp.add(PieEntry(calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.TRANSPORT, filter).toFloat(), context!!.getString(R.string.lbl_transport)))
        NoOfEmp.add(PieEntry(calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.DIVERTISSEMENT, filter).toFloat(), context!!.getString(R.string.lbl_entertainment)))
        NoOfEmp.add(PieEntry(calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.SPORT, filter).toFloat(), context!!.getString(R.string.lbl_sport)))
        NoOfEmp.add(PieEntry(calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.BANK, filter).toFloat(), context!!.getString(R.string.lbl_bank)))
        NoOfEmp.add(PieEntry(calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.AUTRE, filter).toFloat(), context!!.getString(R.string.lbl_other)))
        val dataSet = PieDataSet(NoOfEmp, "Analyse des ${filter.transactionTypeName} par catégorie")

        val data = PieData(dataSet)
        pieChart.setData(data)
        pieChart.centerText ="Analyse des ${filter.transactionTypeName.toLowerCase()} par catégorie"
        dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        //dataSet.setColors(colorClassArray.toMutableList())
        //pieChart.animateXY(5000, 5000)
        pieChart.invalidate()

    }

    fun calculSommeDepenseForCategory(transactionList : List<Transaction>, category : ETransactionCategory, type : ETransactionType) : Int{
        return transactionList
            .asSequence().filter{o -> o.eTransactionCategory==category}
            .filter { o -> o.eTransactionType==type }
            .map {it.amount}
            .sum()
    }
}
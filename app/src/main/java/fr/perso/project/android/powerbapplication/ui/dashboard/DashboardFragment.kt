package fr.perso.project.android.powerbapplication.ui.dashboard

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
import fr.perso.project.android.powerbapplication.model.enums.ETransactionCategory
import fr.perso.project.android.powerbproject.mocks.MockClass.Companion.mockAccountWithTransactionCategorie
import fr.perso.project.android.powerbproject.model.Transaction
import fr.perso.project.android.powerbproject.model.enums.ETransactionType


class DashboardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        //Init
        val pieChart = root.findViewById(R.id.piechart) as PieChart
        val NoOfEmp = ArrayList<PieEntry>()

        //Calculs des dépenses liées aux différentes catégories
        var transactionsMock = mockAccountWithTransactionCategorie().transactions
        println("TRANSACTION LIST : " + transactionsMock.toString())

        //Colors
        var colorClassArray = Array<Int>(7){ Color.DKGRAY; Color.MAGENTA; Color.BLUE; Color.RED; Color.YELLOW; Color.GRAY; Color.GREEN}

        NoOfEmp.add(PieEntry(calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.UNKNOWN, ETransactionType.DEBIT).toFloat(), "Non catégorisé"))
        NoOfEmp.add(PieEntry(calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.LOGEMENT, ETransactionType.DEBIT).toFloat(), "Logement"))
        NoOfEmp.add(PieEntry(calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.TRANSPORT, ETransactionType.DEBIT).toFloat(), "Transport"))
        NoOfEmp.add(PieEntry(calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.DIVERTISSEMENT, ETransactionType.DEBIT).toFloat(), "Divertissement"))
        NoOfEmp.add(PieEntry(calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.SPORT, ETransactionType.DEBIT).toFloat(), "Sport"))
        NoOfEmp.add(PieEntry(calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.BANK, ETransactionType.DEBIT).toFloat(), "Debit"))
        NoOfEmp.add(PieEntry(calculSommeDepenseForCategory(transactionsMock, ETransactionCategory.AUTRE, ETransactionType.DEBIT).toFloat(), "Autre"))
        val dataSet = PieDataSet(NoOfEmp, "Analyse des dépenses par catégorie")

        val data = PieData(dataSet)
        pieChart.setData(data)
        pieChart.centerText ="Analyse des dépenses par catégorie"
        dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        //pieChart.animateXY(5000, 5000)
        return root
    }

    fun calculSommeDepenseForCategory(transactionList : List<Transaction>, category : ETransactionCategory, type : ETransactionType) : Int{
        return transactionList
            .asSequence().filter{o -> o.transactionCategory==category}
            .filter { o -> o.transactionType==type }
            .map {it.amount}
            .sum()
    }
}
package fr.perso.project.android.powerbapplication.ui.fragments.saving

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import android.graphics.DashPathEffect
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import fr.perso.project.android.powerbapplication.R
import kotlinx.android.synthetic.main.fragment_notifications.view.*


class SavingFragment : Fragment() {

    private lateinit var creditSavingSet: LineDataSet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        view.line_chart.setTouchEnabled(true)
        view.line_chart.setPinchZoom(true)

        setupLineChart(view.line_chart)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setupLineChart(lineChart: LineChart) {
        val values = mockValues()

        if (lineChart.data != null && lineChart.getData().getDataSetCount() > 0) {
            creditSavingSet = lineChart.getData().getDataSetByIndex(0) as LineDataSet
            creditSavingSet.values = values
            lineChart.data.notifyDataChanged()
            lineChart.notifyDataSetChanged()
        } else {
            creditSavingSet = LineDataSet(values, "Saving evolution")
            creditSavingSet.setDrawIcons(false)
            creditSavingSet.enableDashedLine(10f, 5f, 0f)
            creditSavingSet.enableDashedHighlightLine(10f, 5f, 0f)
            creditSavingSet.color = Color.DKGRAY
            creditSavingSet.setCircleColor(Color.DKGRAY)
            creditSavingSet.lineWidth = 1f
            creditSavingSet.circleRadius = 3f
            creditSavingSet.setDrawCircleHole(false)
            creditSavingSet.valueTextSize = 9f
            creditSavingSet.setDrawFilled(true)
            creditSavingSet.formLineWidth = 1f
            creditSavingSet.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            creditSavingSet.formSize = 15f
            creditSavingSet.fillColor = resources.getColor(R.color.Dark_pink2)

            //To add our firstSetCreated
            val dataSets = ArrayList<LineDataSet>()
            dataSets.add(creditSavingSet)
            val data = LineData(dataSets as List<ILineDataSet>?)
            lineChart.data = data
        }
    }

    fun mockValues() : ArrayList<Entry>{
        var values = ArrayList<Entry>()
        values.add(Entry(1f, 50f))
        values.add(Entry(2f, 100f))
        values.add(Entry(3f, 150f))
        values.add(Entry(4f, 170f))
        values.add(Entry(5f, 270f))
        values.add(Entry(6f, 50f))
        values.add(Entry(7f, 150f))
        values.add(Entry(8f, 120f))
        values.add(Entry(9f, 120f))
        values.add(Entry(10f, 50f))
        values.add(Entry(11f, 170f))
        values.add(Entry(12f, 140f))
        values.add(Entry(13f, 180f))
        values.add(Entry(14f, 80f))
        values.add(Entry(15f, 90f))
        values.add(Entry(16f, 220f))
        return values
    }
}
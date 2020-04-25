package fr.perso.project.android.powerbproject.Utils

import java.util.*

/**
 * Created on 25/04/2020 - 11:56.
 * Date Utils class to format date
 *
 * @author : JEAN-LOUIS Thessalène
 */
class DateUtils {
    companion object{
        @JvmStatic
        fun formatCalendarDateToFrDate(calendarDate : Calendar):String{
            return "${calendarDate.get(Calendar.DAY_OF_WEEK)}/${calendarDate.get(Calendar.DAY_OF_WEEK)}/${calendarDate.get(Calendar.DAY_OF_WEEK)}"
        }
    }
}
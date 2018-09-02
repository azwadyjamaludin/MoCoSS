package upsi.edu.mocos.model.MyObject

import android.util.Log
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.LogBook

object LogBookMgr {
    var initString = ""
    var weekString = ""
    var weekTitle = ""

    init{
        initString = "LogBooMgr"
        //weekInput()
    }

    fun weekInput(): ArrayList<LogBook>{
        var weekInt: Int

        val lbList :ArrayList<LogBook> = arrayListOf()

        for (item in 1..16) {
            if (MiscSetting.BI) {
                weekInt = item
                weekString = "Week " + weekInt
                weekTitle = "Log Book"
                lbList.add(LogBook(weekTitle,weekString))
            }

            if (MiscSetting.BM){
                weekInt = item
                weekString = "Minggu " + weekInt
                weekTitle = "Buku Log"
                lbList.add(LogBook(weekTitle,weekString))
            }
        }
        Log.d("lbList", lbList.toString())
        return lbList
    }
}
package upsi.edu.mocos.model.MyObject

import android.util.Log
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.InnerListData
import upsi.edu.mocos.model.MyData.NumberData

object NumberMgr {
    var initString = ""
    var numData = ""


    init {
        initString = "NumberMgr"
    }

    fun numInputReflect():ArrayList<NumberData> {
        var numInt: Int
        val numList: ArrayList<NumberData> = arrayListOf()
        for (item in 1..16) {
            if (MiscSetting.BI) {
                numInt = item
                numData = numInt.toString()
                numList.add(NumberData("week"+"-"+numData+":"))
            }
            if (MiscSetting.BM) {
                numInt = item
                numData = numInt.toString()
                numList.add(NumberData("minggu"+"-"+numData+":"))
            }
        }
        Log.d("weekList",numList.toString())
        return numList
    }

    fun numInputInner():ArrayList<InnerListData> {
        var numInt: Int
        val innerList: ArrayList<InnerListData> = arrayListOf()
        for (item in 1..8) {
            numInt = item
            numData = numInt.toString()
            innerList.add(InnerListData(numData,"","",""))
        }
        Log.d("innerList",innerList.toString())
        return innerList
    }

    fun numInputInd():ArrayList<NumberData> {
        var numInt: Int
        val numList: ArrayList<NumberData> = arrayListOf()
        for (item in 1..120) {
            numInt = item
            numData = numInt.toString()
            numList.add(NumberData(numData))
        }
        Log.d("numList",numList.toString())
        return numList
    }

    fun numInputGrp():ArrayList<NumberData> {
        var numInt: Int
        val numList: ArrayList<NumberData> = arrayListOf()
        for (item in 1..60) {
            numInt = item
            numData = numInt.toString()
            numList.add(NumberData(numData))
        }
        Log.d("numList",numList.toString())
        return numList
    }

    fun numInput3():ArrayList<NumberData> {
        var numInt: Int
        val numList: ArrayList<NumberData> = arrayListOf()
        for (item in 1..7) {
            numInt = item
            numData = numInt.toString()
            numList.add(NumberData(numData))
        }
        Log.d("numList",numList.toString())
        return numList
    }

    fun increaseCached():ArrayList<NumberData> {
        var numInt: Int
        val numList: ArrayList<NumberData> = arrayListOf()
        for (item in 1..360) {
            numInt = item
            numData = numInt.toString()
            numList.add(NumberData(numData))
        }
        //Log.d("numList",numList.toString())
        return numList
    }

}
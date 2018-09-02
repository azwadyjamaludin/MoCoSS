package upsi.edu.mocos.model.MyObject

import android.util.Log
import upsi.edu.mocos.model.MyData.NumberData

object NumberMgr {
    var initString = ""
    var numData = ""


    init {
        initString = "NumberMgr"
    }

    fun numInput1():ArrayList<NumberData> {
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

    fun numInput2():ArrayList<NumberData> {
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
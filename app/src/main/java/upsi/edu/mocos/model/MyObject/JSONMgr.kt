package upsi.edu.mocos.model.MyObject

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import org.json.JSONObject
import upsi.edu.mocos.model.MyData.*
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList
import kotlin.text.Charsets.UTF_8

object JSONMgr {
        private var className:String = ""
        private var indHour: ArrayList<Int> = arrayListOf()
        private var grpHour: ArrayList<Int> = arrayListOf()
        private var psytestHour: ArrayList<Int> = arrayListOf()

    init {
        className = "JSONMgr"
    }

    private fun readJSONFile(context: Context): String {
        val assetManager:AssetManager = context.assets
        var jsonString: String = ""
        try {
            val inputStream: InputStream = assetManager.open("dummyData.json")
            val size = inputStream.available()
            val buffer: ByteArray = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            jsonString = String(buffer, UTF_8)
            Log.d("dummyData",jsonString)
        }catch (ex:IOException) {
            ex.printStackTrace()
        }
        return jsonString
    }

    fun parseJSONIndData(context: Context):ArrayList<JSONIndData> {
        val dummyInd: ArrayList<JSONIndData> = arrayListOf()
        val jsonObject = JSONObject(readJSONFile(context))
        var jsonArrayInd = jsonObject.getJSONArray("dummyInd")

            for (jsonIndex in 0..(jsonArrayInd.length())-1) {

                //var intString:String = jsonArrayInd[jsonIndex].toString()
                val sessionDate:String = jsonArrayInd.getJSONObject(jsonIndex).getString("sessionDate")
                //val newDate:String = simpleDateFormat(sessionDate)

                val clientCode:String = jsonArrayInd.getJSONObject(jsonIndex).getString("clientCode")
                val sessionStart:String = jsonArrayInd.getJSONObject(jsonIndex).getString("sessionHourStart")
                val sessionEnd:String = jsonArrayInd.getJSONObject(jsonIndex).getString("sessionHourEnd")

                val sessionHour = sessionStart+"-"+sessionEnd

                val sessionNum:Int = jsonArrayInd.getJSONObject(jsonIndex).getInt("sessionNum")
                dummyInd.add(JSONIndData(sessionDate,clientCode,sessionHour,sessionNum,""))
            } //0..(jsonArrayInd.length()) - 1
        return dummyInd
    }

    fun parseJSONGrpData(context: Context):ArrayList<JSONGrpData> {
        val dummyGrp: ArrayList<JSONGrpData> = arrayListOf()
        val jsonObject = JSONObject(readJSONFile(context))
        var jsonArrayGrp = jsonObject.getJSONArray("dummyGrp")

            for (jsonIndex in 0..(jsonArrayGrp.length())-1) {
                //val intString:String = jsonIndex.toString()
                val sessionDate:String = jsonArrayGrp.getJSONObject(jsonIndex).getString("sessionDate")
                //val newDate:String = simpleDateFormat(sessionDate)

                val clientCode:String = jsonArrayGrp.getJSONObject(jsonIndex).getString("clientGroupCode")
                val sessionStart:String = jsonArrayGrp.getJSONObject(jsonIndex).getString("sessionHourStart")
                val sessionEnd:String = jsonArrayGrp.getJSONObject(jsonIndex).getString("sessionHourEnd")

                val sessionHour = sessionStart+"-"+sessionEnd

                val sessionNum:Int = jsonArrayGrp.getJSONObject(jsonIndex).getInt("sessionNum")
                dummyGrp.add(JSONGrpData(sessionDate,clientCode,sessionHour,sessionNum,""))
        } //0..(jsonArrayGrp.length()) - 1
        return dummyGrp
    }

    fun parseJSONPsyTestData(context: Context):ArrayList<JSONPsyTestData> {
        val dummyPsyTest: ArrayList<JSONPsyTestData> = arrayListOf()
        val jsonObject = JSONObject(readJSONFile(context))
        var jsonArrayPsyTest = jsonObject.getJSONArray("dummyPsyTest")

        for (jsonIndex in 0 until (jsonArrayPsyTest.length())-1) {
            val psytestNum = (jsonIndex+1).toString()
            val psytestDate = jsonArrayPsyTest.getJSONObject(jsonIndex).getString("psytestDate")
            val studentCode = jsonArrayPsyTest.getJSONObject(jsonIndex).getString("studentCode")
            val inventName = jsonArrayPsyTest.getJSONObject(jsonIndex).getString("inventName")
            val psytestStart = jsonArrayPsyTest.getJSONObject(jsonIndex).getString("psytestTimeStart")
            val psytestEnd = jsonArrayPsyTest.getJSONObject(jsonIndex).getString("psytestTimeEnd")

            val psytestHour = psytestStart+"-"+psytestEnd
            dummyPsyTest.add(JSONPsyTestData(psytestNum,psytestDate,psytestHour,studentCode,inventName,""))
        }

        return dummyPsyTest
    }

    fun parseJSONRDData(context: Context):ArrayList<JSONRefDetailData> {
        val dummyRD: ArrayList<JSONRefDetailData> = arrayListOf()
        val jsonObject = JSONObject(readJSONFile(context))
        var jsonArrayRD = jsonObject.getJSONArray("dummyRefer")

        for (jsonIndex in 0..(jsonArrayRD.length())-1) {
            val referNum = (jsonIndex+1).toString()
            val referStud = jsonArrayRD.getJSONObject(jsonIndex).getString("referStud")
            val referReason = jsonArrayRD.getJSONObject(jsonIndex).getString("referReason")

            dummyRD.add(JSONRefDetailData(referNum,referStud,referReason,""))
        }

        return dummyRD
    }

    fun parseJSONCDData(context: Context):ArrayList<JSONConsDetailData> {
        val dummyCD: ArrayList<JSONConsDetailData> = arrayListOf()
        val jsonObject = JSONObject(readJSONFile(context))
        var jsonArrayCD = jsonObject.getJSONArray("dummyConsult")

        for (jsonIndex in 0..(jsonArrayCD.length())-1) {
            val consNum = (jsonIndex+1).toString()
            val consField = jsonArrayCD.getJSONObject(jsonIndex).getString("consField")
            val consEnt = jsonArrayCD.getJSONObject(jsonIndex).getString("consEnt")

            dummyCD.add(JSONConsDetailData(consNum,consEnt,consField,""))
        }

        return dummyCD
    }

    fun getIntHoursInd(context: Context): ArrayList<Int> {
        val jsonObject = JSONObject(readJSONFile(context))
        var jsonArrayInd = jsonObject.getJSONArray("dummyInd")
        for (jsonIndex in 0..(jsonArrayInd.length())-1) {
            val sessionStart:String = jsonArrayInd.getJSONObject(jsonIndex).getString("sessionHourStart")
            val sessionEnd:String = jsonArrayInd.getJSONObject(jsonIndex).getString("sessionHourEnd")
            val hourDifInd = getHours(sessionStart,sessionEnd)
            indHour.add(hourDifInd)
        }
        Log.d("indHour", indHour.toString())
        return indHour
    }

    fun getIntHoursGrp(context: Context): ArrayList<Int> {
        val jsonObject = JSONObject(readJSONFile(context))
        var jsonArrayGrp = jsonObject.getJSONArray("dummyGrp")
        for (jsonIndex in 0..(jsonArrayGrp.length())-1) {
            val sessionStart:String = jsonArrayGrp.getJSONObject(jsonIndex).getString("sessionHourStart")
            val sessionEnd:String = jsonArrayGrp.getJSONObject(jsonIndex).getString("sessionHourEnd")
            val hourDifGrp = getHours(sessionStart,sessionEnd)
            grpHour.add(hourDifGrp)
        }
        Log.d("grpHour2", grpHour.toString())
        return grpHour
    }

    fun getIntHourPsyTest(context: Context):ArrayList<Int> {
        val jsonObject = JSONObject(readJSONFile(context))
        var jsonArrayPsyTest = jsonObject.getJSONArray("dummyPsyTest")
        for (jsonIndex in 0..(jsonArrayPsyTest.length())-1) {
            val psytestStart = jsonArrayPsyTest.getJSONObject(jsonIndex).getString("psytestTimeStart")
            val psytestEnd = jsonArrayPsyTest.getJSONObject(jsonIndex).getString("psytestTimeEnd")
            val hourDifPsyTest = getHours(psytestStart,psytestEnd)
            psytestHour.add(hourDifPsyTest)
        }
        Log.d("psytestHour", psytestHour.toString())
        return psytestHour
    }

    private fun getHours(start:String, end:String): Int {
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        val date1 = simpleDateFormat.parse(start)
        val date2 = simpleDateFormat.parse(end)

        val diff:Long = date2.time.minus(date1.time) / (60*60*1000)%24
        Log.d("diff =",diff.toString())
        return diff.toInt()
    }
}
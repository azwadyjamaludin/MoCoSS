package upsi.edu.mocos.model.MyObject

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import upsi.edu.mocos.model.MyData.JSONGrpData
import upsi.edu.mocos.model.MyData.JSONIndData
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.lang.Integer.parseInt
import java.lang.Long.parseLong
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.text.Charsets.UTF_8

object JSONMgr {
        private var className:String = ""
        private var indHour: ArrayList<Int> = arrayListOf()
        private var grpHour: ArrayList<Int> = arrayListOf()

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

            for (jsonIndex in 0..(jsonArrayInd.length()) - 1) {

                //var intString:String = jsonArrayInd[jsonIndex].toString()
                val sessionDate:String = jsonArrayInd.getJSONObject(jsonIndex).getString("sessionDate")
                //val newDate:String = simpleDateFormat(sessionDate)

                val clientCode:String = jsonArrayInd.getJSONObject(jsonIndex).getString("clientCode")
                val sessionStart:String = jsonArrayInd.getJSONObject(jsonIndex).getString("sessionHourStart")
                val sessionEnd:String = jsonArrayInd.getJSONObject(jsonIndex).getString("sessionHourEnd")

                val sessionHour = sessionStart+"-"+sessionEnd

                val sessionNum:Int = jsonArrayInd.getJSONObject(jsonIndex).getInt("sessionNum")
                dummyInd.add(JSONIndData(sessionDate,clientCode,sessionHour,sessionNum))
            } //0..(jsonArrayInd.length()) - 1
        return dummyInd
    }

    fun parseJSONGrpData(context: Context):ArrayList<JSONGrpData> {
        val dummyGrp: ArrayList<JSONGrpData> = arrayListOf()
        val jsonObject = JSONObject(readJSONFile(context))
        var jsonArrayGrp = jsonObject.getJSONArray("dummyGrp")

            for (jsonIndex in 0..(jsonArrayGrp.length()) - 1) {
                //val intString:String = jsonIndex.toString()
                val sessionDate:String = jsonArrayGrp.getJSONObject(jsonIndex).getString("sessionDate")
                //val newDate:String = simpleDateFormat(sessionDate)

                val clientCode:String = jsonArrayGrp.getJSONObject(jsonIndex).getString("clientGroupCode")
                val sessionStart:String = jsonArrayGrp.getJSONObject(jsonIndex).getString("sessionHourStart")
                val sessionEnd:String = jsonArrayGrp.getJSONObject(jsonIndex).getString("sessionHourEnd")

                val sessionHour = sessionStart+"-"+sessionEnd

                val sessionNum:Int = jsonArrayGrp.getJSONObject(jsonIndex).getInt("sessionNum")
                dummyGrp.add(JSONGrpData(sessionDate,clientCode,sessionHour,sessionNum))
        } //0..(jsonArrayGrp.length()) - 1
        return dummyGrp
    }

    fun getIntHoursInd(context: Context): ArrayList<Int> {
        val jsonObject = JSONObject(readJSONFile(context))
        var jsonArrayInd = jsonObject.getJSONArray("dummyInd")
        for (jsonIndex in 0..(jsonArrayInd.length()) - 1) {
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

    fun getHours(start:String, end:String): Int {
        val convertStart = start
        val convertEnd = end
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        val date1 = simpleDateFormat.parse(convertStart)
        val date2 = simpleDateFormat.parse(convertEnd)

        val diff:Long = date2.time.minus(date1.time) / (60*60*1000)%24
        Log.d("diff =",diff.toString())
        return diff.toInt()
    }
}
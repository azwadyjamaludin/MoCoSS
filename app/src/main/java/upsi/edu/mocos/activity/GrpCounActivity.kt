package upsi.edu.mocos.activity

import android.content.ComponentCallbacks2
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_grp_coun.*
import kotlinx.android.synthetic.main.activity_grp_coun.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import upsi.edu.mocos.R
import upsi.edu.mocos.adapter.listadapter.GrpCounContentListAdapter
import upsi.edu.mocos.adapter.listadapter.GrpCounContentListAdapter2
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONGrpData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.MyObject.JSONMgr
import upsi.edu.mocos.model.MyObject.NumberMgr
import upsi.edu.mocos.model.PageNavigate

class GrpCounActivity : MocoSSParentActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    var jsonGrpData:ArrayList<JSONGrpData> = arrayListOf()
    var numbering:ArrayList<NumberData> = arrayListOf()
    var totalHourGrp:ArrayList<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grp_coun)
        layoutManager = LinearLayoutManager(this)
        grpCounLV.layoutManager = layoutManager

        initPage(grpCounActivity)
        attachAdapter(grpCounActivity)
        newText(grpCounActivity)
    }

    override fun onBackPressed() {
        totalHourGrp.clear()
        goToPage(PageNavigate.ContentPage,this@GrpCounActivity)

    }

    private fun attachAdapter(view: View) {

        if (MiscSetting.user == "tc") {
            numbering = NumberMgr.increaseCached()
            jsonGrpData = JSONMgr.parseJSONGrpData(this@GrpCounActivity)
            val listadapter = GrpCounContentListAdapter(numbering,jsonGrpData)
            val grpCounLV = view.grpCounLV

            grpCounLV.adapter = listadapter
        }
        if (MiscSetting.user == "gc"||MiscSetting.user == "sl") {
            numbering = NumberMgr.increaseCached()
            jsonGrpData = JSONMgr.parseJSONGrpData(this@GrpCounActivity)
            val listadapter = GrpCounContentListAdapter2(numbering,jsonGrpData)
            val grpCounLV = view.grpCounLV

            grpCounLV.adapter = listadapter
        }

        view.totalHourGrp.text = totalHour()
    }

    fun initPage(view: View) {
        val grpCoun = view.grpCounTB
        if (MiscSetting.BM) {
            grpCoun.title = "Kaunseling Kelompok"
        }
        if (MiscSetting.BI) {
            grpCoun.title = "Group Counseling"
        }

    }

    fun newText(view: View) {

        if(MiscSetting.BM) {
            view.numTextGrp.text = "Bil."
            view.dateTextGrp.text = "Tarikh"
            view.sescodeTextGrp.text = "Kod Sesi"
            view.seshourTextGrp.text = "Jam Sesi"
            view.sesrptTextGrp.text = "Laporan Sesi"
            view.avTextGrp.text = "Rekod Audio/Video"
            view.noteTextGrp.text = "Catatan"
            view.totHourTextGrp.text = "Jumlah Jam:"

        }
        if(MiscSetting.BI) {
            view.numTextGrp.text = "No."
            view.dateTextGrp.text = "Date"
            view.sescodeTextGrp.text = "Session Code"
            view.seshourTextGrp.text = "Session Hour"
            view.sesrptTextGrp.text = "Session Report"
            view.avTextGrp.text = "Audio/Video Record"
            view.noteTextGrp.text = "Notes"
            view.totHourTextGrp.text = "Total Hour:"
        }

    }

    private fun getHoursGrp():ArrayList<Int> {
        totalHourGrp = JSONMgr.getIntHoursGrp(this@GrpCounActivity)
        return totalHourGrp
    }

    private fun totalHour():String{
        var numArray:ArrayList<Int> = getHoursGrp()
        var sum = 0
        for (num in numArray) {
            sum += num
        }
        return sum.toString()
    }
}

package upsi.edu.mocos.activity

import android.content.ComponentCallbacks2
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_ind_coun.*
import kotlinx.android.synthetic.main.activity_ind_coun.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import upsi.edu.mocos.R
import upsi.edu.mocos.adapter.listadapter.IndCounContentListAdapter
import upsi.edu.mocos.adapter.listadapter.IndCounContentListAdapter2
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONIndData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.MyObject.JSONMgr
import upsi.edu.mocos.model.MyObject.NumberMgr
import upsi.edu.mocos.model.PageNavigate

class IndCounActivity : MocoSSParentActivity(), ComponentCallbacks2 {
    private lateinit var layoutManager: LinearLayoutManager
    var jsonDataInd:ArrayList<JSONIndData> = arrayListOf()
    var numbering:ArrayList<NumberData> = arrayListOf()
    var totalHourInd:ArrayList<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ind_coun)
        layoutManager = LinearLayoutManager(this)
        indCounLV.layoutManager = layoutManager

        initPage(indCounActivity)
        newText(indCounActivity)
        attachAdapter(indCounActivity)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when (level) {

            ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN -> {
            }

            ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE,
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW,
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL -> {

            }

            ComponentCallbacks2.TRIM_MEMORY_BACKGROUND,
            ComponentCallbacks2.TRIM_MEMORY_MODERATE,
            ComponentCallbacks2.TRIM_MEMORY_COMPLETE -> {

            }

            else -> {

            }
        }
    }

    override fun onBackPressed() {
        totalHourInd.clear()
        goToPage(PageNavigate.ContentPage,this@IndCounActivity)

    }

    private fun attachAdapter(view: View) {

        if (MiscSetting.user == "tc") {
            jsonDataInd = JSONMgr.parseJSONIndData(this@IndCounActivity)
            numbering = NumberMgr.increaseCached()
            val listadapter = IndCounContentListAdapter(numbering,jsonDataInd)
            val indCounLV = view.indCounLV

            indCounLV.adapter = listadapter
        }
        if (MiscSetting.user == "gc"||MiscSetting.user == "sl") {
            numbering = NumberMgr.increaseCached()
            jsonDataInd = JSONMgr.parseJSONIndData(this@IndCounActivity)
            val listadapter = IndCounContentListAdapter2(numbering,jsonDataInd)
            val indCounLV = view.indCounLV

            indCounLV.adapter = listadapter
        }
        view.totalHourInd.text = totalHour()
    }

    private fun initPage(view: View) {
        val indCoun = view.indCounTB
        if (MiscSetting.BM) {
            indCoun.title = "Kaunseling Individu"
        }
        if (MiscSetting.BI) {
            indCoun.title = "Individual Counseling"
        }
    }

    private fun newText(view: View) {
        if(MiscSetting.BM) {
            view.numTextInd.text = "Bil."
            view.dateTextInd.text = "Tarikh"
            view.sescodeTextInd.text = "Kod Sesi"
            view.seshourTextInd.text = "Jam Sesi"
            view.sesrptTextInd.text = "Laporan Sesi"
            view.avTextInd.text = "Rekod Audio/Video"
            view.noteTextInd.text = "Catatan"
            view.totHourTextInd.text = "Jumlah Jam:"

        }
        if(MiscSetting.BI) {
            view.numTextInd.text = "No."
            view.dateTextInd.text = "Date"
            view.sescodeTextInd.text = "Session Code"
            view.seshourTextInd.text = "Session Hour"
            view.sesrptTextInd.text = "Session Report"
            view.avTextInd.text = "Audio/Video Record"
            view.noteTextInd.text = "Notes"
            view.totHourTextInd.text = "Total Hour:"
        }

    }

    private fun getHoursInd():ArrayList<Int> {
        totalHourInd = JSONMgr.getIntHoursInd(this@IndCounActivity)
        return totalHourInd
    }

    private fun totalHour():String{
        var numArray:ArrayList<Int> = getHoursInd()
        var sum = 0
        for (num in numArray) {
            sum += num
        }
        return sum.toString()
    }

}


package upsi.edu.mocos.activity

import android.content.ComponentCallbacks2
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_ind_coun.*
import kotlinx.android.synthetic.main.activity_ind_coun.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.adapter.listadapter.IndCounContentListAdapter
import upsi.edu.mocos.adapter.listadapter.IndCounContentListAdapter2
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyObject.NumberMgr

class IndCounActivity : MocoSSParentActivity(), ComponentCallbacks2 {
    private lateinit var layoutManager: LinearLayoutManager

    override fun createActivity(savedInstanceState: Bundle?) {
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

    private fun attachAdapter(view: View) {
        if (MiscSetting.user == "tc") {
            val numbering = NumberMgr.numInput1()
            val listadapter = IndCounContentListAdapter(numbering)
            val indCounLV = view.indCounLV

            indCounLV.adapter = listadapter
        }
        if (MiscSetting.user == "gc"||MiscSetting.user == "sl") {
            val numbering = NumberMgr.numInput1()
            val listadapter = IndCounContentListAdapter2(numbering)
            val indCounLV = view.indCounLV

            indCounLV.adapter = listadapter
        }
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
            view.totHourTextInd.text = "Jumlah Jam"

        }
        if(MiscSetting.BI) {
            view.numTextInd.text = "No."
            view.dateTextInd.text = "Date"
            view.sescodeTextInd.text = "Session Code"
            view.seshourTextInd.text = "Session Hour"
            view.sesrptTextInd.text = "Session Report"
            view.avTextInd.text = "Audio/Video Record"
            view.noteTextInd.text = "Notes"
            view.totHourTextInd.text = "Total Hour"
        }
    }
}


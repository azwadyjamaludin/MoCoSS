package upsi.edu.mocos.activity

import android.content.ComponentCallbacks2
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_grp_coun.*
import kotlinx.android.synthetic.main.activity_grp_coun.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.adapter.listadapter.GrpCounContentListAdapter
import upsi.edu.mocos.adapter.listadapter.GrpCounContentListAdapter2
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyObject.NumberMgr

class GrpCounActivity : AppCompatActivity(), ComponentCallbacks2 {
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grp_coun)
        layoutManager = LinearLayoutManager(this)
        grpCounLV.layoutManager = layoutManager

        initPage(grpCounActivity)
        attachAdapter(grpCounActivity)
        newText(grpCounActivity)
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
            val numbering = NumberMgr.numInput2()
            val listadapter = GrpCounContentListAdapter(numbering)
            val grpCounLV = view.grpCounLV

            grpCounLV.adapter = listadapter
        }
        if (MiscSetting.user == "gc"||MiscSetting.user == "sl") {
            val numbering = NumberMgr.numInput2()
            val listadapter = GrpCounContentListAdapter2(numbering)
            val grpCounLV = view.grpCounLV

            grpCounLV.adapter = listadapter
        }
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
            view.totHourTextGrp.text = "Jumlah Jam"

        }
        if(MiscSetting.BI) {
            view.numTextGrp.text = "No."
            view.dateTextGrp.text = "Date"
            view.sescodeTextGrp.text = "Session Code"
            view.seshourTextGrp.text = "Session Hour"
            view.sesrptTextGrp.text = "Session Report"
            view.avTextGrp.text = "Audio/Video Record"
            view.noteTextGrp.text = "Notes"
            view.totHourTextGrp.text = "Total Hour"
        }
    }

}

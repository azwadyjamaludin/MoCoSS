package upsi.edu.mocos.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_grp_coun.*
import kotlinx.android.synthetic.main.activity_grp_coun.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.GrpCounContentListAdapter
import upsi.edu.mocos.model.GrpCounContentListAdapter2
import upsi.edu.mocos.model.MiscSetting

class GrpCounActivity : AppCompatActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    var list = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grp_coun)
        layoutManager = LinearLayoutManager(this)
        grpCounLV.layoutManager = layoutManager

        initPage(grpCounActivity)
        attachAdapter(grpCounActivity)
        newText(grpCounActivity)
    }

    private fun attachAdapter(view: View) {
        if (MiscSetting.user == "tc") {
            val listadapter = GrpCounContentListAdapter(addElement())
            val grpCounLV = view.grpCounLV

            grpCounLV.adapter = listadapter
        }
        if (MiscSetting.user == "gc"||MiscSetting.user == "sl") {
            val listadapter = GrpCounContentListAdapter2(addElement())
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

    private fun addElement():ArrayList<String> {
        for (item in 1..60) {
            list.add(item.toString())
            print("list:"+list)
        }
        return list
    }
}

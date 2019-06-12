package upsi.edu.mocos.activity.RefCons

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_cons_detail.*
import kotlinx.android.synthetic.main.activity_cons_detail.view.*
import org.jetbrains.anko.doAsync
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.MoCoSSParentActivity
import upsi.edu.mocos.adapter.listadapter.ConsDetailListAdapter
import upsi.edu.mocos.adapter.listadapter.ConsDetailListAdapter2
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONConsDetailData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.MyObject.JSONMgr
import upsi.edu.mocos.model.MyObject.NumberMgr

class ConsDetailActivity : MoCoSSParentActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    var numbering:ArrayList<NumberData> = arrayListOf()
    var jsonDataCD:ArrayList<JSONConsDetailData> = arrayListOf()
    val REQUEST_CHOOSER = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cons_detail)

        layoutManager = LinearLayoutManager(this)
        consDetailRV.layoutManager = layoutManager

        attachAdapter(consDetailCL)
        mainText(consDetailCL)
        detailText(consDetailCL)
    }

    private fun attachAdapter(view: View) {
        val origin = this
        doAsync {
            if (MiscSetting.user == "tc") {
                numbering = NumberMgr.increaseCached()
                jsonDataCD = JSONMgr.parseJSONCDData(this@ConsDetailActivity)
                var listAdapter = ConsDetailListAdapter(jsonDataCD,origin)
                var consDetailRV = view.consDetailRV

                consDetailRV.adapter = listAdapter
            }
            if (MiscSetting.user == "gc"||MiscSetting.user == "sl") {
                numbering = NumberMgr.increaseCached()
                jsonDataCD = JSONMgr.parseJSONCDData(this@ConsDetailActivity)
                var listAdapter = ConsDetailListAdapter2(jsonDataCD,origin)
                var consDetailRV = view.consDetailRV

                consDetailRV.adapter = listAdapter
            }

        }
    }

    private fun mainText(view: View) {
        if (MiscSetting.BM) {
            view.consDetailTB.title = "Konsultasi"
        }
        if (MiscSetting.BI) {
            view.consDetailTB.title = "Consultation"
        }
    }

    private fun detailText(view: View){
        if (MiscSetting.BM) {
            view.consDetailNum.text = getString(R.string.numberMy)
            view.consDetailEnt.text = "Pihak Dikonsultasi"
            view.consDetailField.text = "Bidang Konsultasi"
            view.consDetailUp.text = "Laporan Konsultasi"
            view.consDetailNote.text = getString(R.string.notesMY)
        }
        if (MiscSetting.BI) {
            view.consDetailNum.text = getString(R.string.numberEN)
            view.consDetailEnt.text = "Consulted Entity"
            view.consDetailField.text = "Consulted Field"
            view.consDetailUp.text = "Consultation Report"
            view.consDetailNote.text = getString(R.string.notesEN)
        }
    }
}

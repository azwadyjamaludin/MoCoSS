package upsi.edu.mocos.activity.RefCons

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_ref_detail.*
import kotlinx.android.synthetic.main.activity_ref_detail.view.*
import org.jetbrains.anko.doAsync
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.MoCoSSParentActivity
import upsi.edu.mocos.adapter.listadapter.RefDetailListAdapter
import upsi.edu.mocos.adapter.listadapter.RefDetailListAdapter2
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONRefDetailData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.MyObject.JSONMgr
import upsi.edu.mocos.model.MyObject.NumberMgr

class RefDetailActivity : MoCoSSParentActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    var numbering:ArrayList<NumberData> = arrayListOf()
    var jsonDataRD:ArrayList<JSONRefDetailData> = arrayListOf()
    val REQUEST_CHOOSER = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ref_detail)

        layoutManager = LinearLayoutManager(this)
        refDetailRV.layoutManager = layoutManager

        attachAdapter(refDetailCL)
        mainText(refDetailCL)
        detailText(refDetailCL)
    }

    private fun attachAdapter(view: View) {
        val origin = this
        doAsync {
            if (MiscSetting.user == "tc") {
                numbering = NumberMgr.increaseCached()
                jsonDataRD = JSONMgr.parseJSONRDData(this@RefDetailActivity)
                val listAdapter = RefDetailListAdapter(jsonDataRD,origin)
                val refDetailRV = view.refDetailRV

                refDetailRV.adapter = listAdapter
            }
            if (MiscSetting.user == "gc"||MiscSetting.user == "sl") {
                numbering = NumberMgr.increaseCached()
                jsonDataRD = JSONMgr.parseJSONRDData(this@RefDetailActivity)
                val listAdapter = RefDetailListAdapter2(jsonDataRD,origin)
                val refDetailRV = view.refDetailRV

                refDetailRV.adapter = listAdapter
            }
        }
    }

    private fun mainText(view: View) {
        if (MiscSetting.BM) {
            view.refDetailTB.title = "Rujukan"
        }
        if (MiscSetting.BI) {
            view.refDetailTB.title = "Refer"
        }
    }

    private fun detailText(view: View) {
        if (MiscSetting.BM) {
            view.refDetailNum.text = getString(R.string.numberMy)
            view.refDetailStud.text = "Pelajar Dirujuk"
            view.refDetailRSN.text = "Sebab Dirujuk"
            view.refDetailUp.text = "Laporan Rujukan"
            view.refDetailNote.text = getString(R.string.notesMY)
        }
        if (MiscSetting.BI) {
            view.refDetailNum.text = getString(R.string.numberEN)
            view.refDetailStud.text = "Referred Student"
            view.refDetailRSN.text = "Referred Reason"
            view.refDetailUp.text = "Referred Report"
            view.refDetailNote.text = getString(R.string.notesEN)
        }
    }

}

package upsi.edu.mocos.activity.logBook

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_inner_list.*
import kotlinx.android.synthetic.main.activity_inner_list.view.*
import kotlinx.android.synthetic.main.activity_inner_list_2.*
import kotlinx.android.synthetic.main.activity_inner_list_2.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.MoCoSSParentActivity
import upsi.edu.mocos.adapter.miscadapter.LogBookListInnerCustomAdapter
import upsi.edu.mocos.adapter.miscadapter.LogBookListInnerCustomAdapter2
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyObject.NumberMgr

class InnerListActivity : MoCoSSParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (MiscSetting.user == "tc") {
            setContentView(R.layout.activity_inner_list)
            initView(InnerListActivity)
            val context: Context = this
            val innerLBLV: ListView = findViewById(R.id.innerLBLV)
            attachInnerAdapter(context,innerLBLV)
        }
        if (MiscSetting.user == "gc"|| MiscSetting.user == "sl") {
            setContentView(R.layout.activity_inner_list_2)
            initView2(InnerListActivity_2nd)
            val context = this
            val innerLV_2nd: ListView = findViewById(R.id.innerLBLV_2nd)
            attachInnerAdapter2(context,innerLV_2nd)
        }
    }

    fun initView(view: View) {
        if (MiscSetting.BM) {
            val getData = intent.getStringExtra("dateTextMY")
            val dateText = view.dateTextLB
            dateText.text = getData
            view.dayTextLB.text = "Hari :"
            view.timeTextLB.text = "Masa"
            view.activityLB.text = "Aktiviti"
            view.notesTextLB.text = "Catatan"
        }
        if (MiscSetting.BI) {
            val getData = intent.getStringExtra("dateTextEN")
            val dateText = view.dateTextLB
            dateText.text = getData
            view.dayTextLB.text = "Day :"
            view.timeTextLB.text = "Time"
            view.activityLB.text = "Activity"
            view.notesTextLB.text = "Notes"
        }
    }

    fun initView2(view: View) {
        if (MiscSetting.BM) {
            val getData = intent.getStringExtra("dateTextMY2")
            val dateText = view.dateTextLB_2nd
            dateText.text = getData
            view.dayTextLB_2nd.text = "Hari :"
            view.timeTextLB_2nd.text = "Masa"
            view.activityLB_2nd.text = "Aktiviti"
            view.notesTextLB_2nd.text = "Catatan"
        }
        if (MiscSetting.BI) {
            val getData = intent.getStringExtra("dateTextEN2")
            val dateText = view.dateTextLB_2nd
            dateText.text = getData
            view.dayTextLB_2nd.text = "Day :"
            view.timeTextLB_2nd.text = "Time"
            view.activityLB_2nd.text = "Activity"
            view.notesTextLB_2nd.text = "Notes"
        }
    }

    private fun attachInnerAdapter(context: Context, lv: ListView) {
        doAsync {
            val innerData = NumberMgr.numInputInner()
            val innerListAdapter = LogBookListInnerCustomAdapter(context, innerData)
            lv.adapter = innerListAdapter
            uiThread {
                toast("doAsync")
            }
        }
    }

    private fun attachInnerAdapter2(context:Context, lv2: ListView) {
        doAsync {
        val innerData2 = NumberMgr.numInputInner()
        val innerListAdapter2 = LogBookListInnerCustomAdapter2(context,innerData2)
        lv2.adapter = innerListAdapter2
            uiThread {
                toast("doAsync")
            }
        }
    }

}

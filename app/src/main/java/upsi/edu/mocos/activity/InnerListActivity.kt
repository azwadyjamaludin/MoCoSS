package upsi.edu.mocos.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_inner_list.*
import kotlinx.android.synthetic.main.activity_inner_list.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import upsi.edu.mocos.R
import upsi.edu.mocos.adapter.miscadapter.LogBookListInnerCustomAdapter
import upsi.edu.mocos.adapter.miscadapter.LogBookListInnerCustomAdapter2
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyObject.NumberMgr

class InnerListActivity : MocoSSParentActivity() {

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
            val context = this
            val innerLV_2nd: ListView = findViewById(R.id.innerLBLV_2nd)
            attachInnerAdapter2(context,innerLV_2nd)
        }
    }

    fun initView(view: View) {
        val getData = intent.getStringExtra("dateText")
        val dateText = view.dateTextLB
        dateText.text = getData
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

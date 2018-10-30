package upsi.edu.mocos.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_intern_info.*
import kotlinx.android.synthetic.main.activity_intern_info.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.adapter.listadapter.InternInfoListAdapter
import upsi.edu.mocos.model.MyObject.ImgMgr
import upsi.edu.mocos.model.MyObject.NumberMgr

class InternInfoActivity : MocoSSParentActivity() {
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intern_info)

        layoutManager = LinearLayoutManager(this)
        internInfoRV.layoutManager = layoutManager

        attachRVAdapter(internInfoActivity)

    }


    private fun attachRVAdapter(view: View) {
        val interinfoList = ImgMgr.imgInput()
        val numbering = NumberMgr.increaseCached()
        val rvAdapter = InternInfoListAdapter(interinfoList,numbering)

        val internInfoRV = view.internInfoRV
        internInfoRV.adapter = rvAdapter

    }
}

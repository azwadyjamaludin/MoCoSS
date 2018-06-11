package upsi.edu.mocos.model

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.grp_coun_listadapter.view.*
import kotlinx.android.synthetic.main.grp_coun_listadapter_2.view.*
import upsi.edu.mocos.R

class GrpCounContentListAdapter (
        private var numbering: ArrayList<String>
): RecyclerView.Adapter<GrpCounContentListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrpCounContentListAdapter.ViewHolder {
        val inflatedView = parent.inflate(R.layout.grp_coun_listadapter, false)
        val inflatedView2 = parent.inflate(R.layout.grp_coun_listadapter_2, false)
        if (MiscSetting.user == "gl" || MiscSetting.user == "sl") {
            return GrpCounContentListAdapter.ViewHolder(inflatedView2)
        }
        return GrpCounContentListAdapter.ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return numbering.size
    }

    override fun onBindViewHolder(holder: GrpCounContentListAdapter.ViewHolder, position: Int) {
        val number = numbering[position]
        holder.decoWidget(number)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private var view: View = view

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            private val NUMBERING_KEY = "NUMBERING"
        }

        fun decoWidget(number: String) {

            view.numDataGrp.text = number

            if (MiscSetting.BM) {
                view.sesRptBtnGrp.text = "MuatNaik"
                view.avBtnGrp.text = "MuatNaik"
            }
            if (MiscSetting.BM && MiscSetting.user == "gl"||MiscSetting.user == "sl") {
                view.sesRptBtnGrp2.text = "MuatTurun"
                view.avBtnGrp2.text = "MuatTurun"
            }

            if (MiscSetting.BI) {
                view.sesRptBtnGrp.text = "Upload"
                view.avBtnGrp.text = "Upload"
            }
            if (MiscSetting.BI && MiscSetting.user == "gl"||MiscSetting.user == "sl") {
                view.sesRptBtnGrp2.text = "Download"
                view.avBtnGrp2.text = "Download"

            }
        }
    }
}
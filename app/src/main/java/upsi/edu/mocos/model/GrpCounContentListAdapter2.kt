package upsi.edu.mocos.model

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.grp_coun_listadapter.view.*
import kotlinx.android.synthetic.main.grp_coun_listadapter_2.view.*
import upsi.edu.mocos.R

class GrpCounContentListAdapter2 (
        private var numbering: ArrayList<String>
): RecyclerView.Adapter<GrpCounContentListAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrpCounContentListAdapter2.ViewHolder {
        val inflatedView = parent.inflate(R.layout.grp_coun_listadapter_2, false)
        return GrpCounContentListAdapter2.ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return numbering.size
    }

    override fun onBindViewHolder(holder: GrpCounContentListAdapter2.ViewHolder, position: Int) {
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

            view.numDataGrp2.text = number

            if (MiscSetting.BM) {
                view.sesRptBtnGrp2.text = "MuatTurun"
                view.avBtnGrp2.text = "MuatTurun"
            }
            if (MiscSetting.BI) {
                view.sesRptBtnGrp2.text = "Download"
                view.avBtnGrp2.text = "Download"

            }
        }
    }
}
package upsi.edu.mocos.model

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.ind_coun_listadapter.view.*
import kotlinx.android.synthetic.main.ind_coun_listadapter_2.view.*
import upsi.edu.mocos.R

class IndCounContentListAdapter(
        private var numbering: ArrayList<String>
):RecyclerView.Adapter<IndCounContentListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):IndCounContentListAdapter.ViewHolder {
        val inflatedView = parent.inflate(R.layout.ind_coun_listadapter, false)
        val inflatedView2 = parent.inflate(R.layout.ind_coun_listadapter_2, false)

        if (MiscSetting.user == "gl"||MiscSetting.user == "sl") {
            return IndCounContentListAdapter.ViewHolder(inflatedView2)
        }
        return IndCounContentListAdapter.ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return numbering.size
    }

    override fun onBindViewHolder(holder:IndCounContentListAdapter.ViewHolder, position: Int) {
        val number = numbering[position]
        holder.decoWidget(number)

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private var view: View = view
        //private var number: String? = null

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
            view.numDataInd.text = number

            if (MiscSetting.BM) {
                view.sesRptBtnInd.text = "MuatNaik"
                view.avBtnInd.text = "MuatNaik"
            }
            if (MiscSetting.BM && MiscSetting.user == "gl"||MiscSetting.user == "sl") {
                view.sesRptBtnInd2.text = "MuatTurun"
                view.avBtnInd2.text = "MuatTurun"
            }

            if (MiscSetting.BI) {
                view.sesRptBtnInd.text = "Upload"
                view.avBtnInd.text = "Upload"
            }
            if (MiscSetting.BI && MiscSetting.user == "gl"||MiscSetting.user == "sl") {
                view.sesRptBtnInd2.text = "Download"
                view.avBtnInd2.text = "Download"
            }

        }

    }

}
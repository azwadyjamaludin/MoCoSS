package upsi.edu.mocos.model

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.ind_coun_listadapter.view.*
import kotlinx.android.synthetic.main.ind_coun_listadapter_2.view.*
import upsi.edu.mocos.R

class IndCounContentListAdapter2(
        private var numbering: ArrayList<String>
):RecyclerView.Adapter<IndCounContentListAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):IndCounContentListAdapter2.ViewHolder {
        val inflatedView = parent.inflate(R.layout.ind_coun_listadapter_2, false)
        return IndCounContentListAdapter2.ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return numbering.size
    }

    override fun onBindViewHolder(holder:IndCounContentListAdapter2.ViewHolder, position: Int) {
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
            view.numDataInd2.text = number

            if (MiscSetting.BM) {
                view.sesRptBtnInd2.text = "MuatTurun"
                view.avBtnInd2.text = "MuatTurun"
            }

            if (MiscSetting.BI) {
                view.sesRptBtnInd2.text = "Download"
                view.avBtnInd2.text = "Download"
            }

        }

    }

}
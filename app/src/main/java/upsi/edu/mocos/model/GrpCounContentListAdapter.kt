package upsi.edu.mocos.model

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.grp_coun_listadapter.view.*

import upsi.edu.mocos.R

class GrpCounContentListAdapter (
        context: Context,
        private var numbering: ArrayList<String>
): RecyclerView.Adapter<GrpCounContentListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrpCounContentListAdapter.ViewHolder{
        val inflatedView = parent.inflate(R.layout.grp_coun_listadapter, false)
        return GrpCounContentListAdapter.ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return numbering.size
    }

    override fun onBindViewHolder(holder:GrpCounContentListAdapter.ViewHolder, position: Int) {
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
            view.numDataGrp.text = number
            if (MiscSetting.BM) {
                view.sesRptBtnGrp.text = "MuatNaik"
                view.avBtnGrp.text = "MuatNaik"
            }
            if (MiscSetting.BI) {
                view.sesRptBtnGrp.text = "Upload"
                view.avBtnGrp.text = "Upload"
            }
        }
    }
}
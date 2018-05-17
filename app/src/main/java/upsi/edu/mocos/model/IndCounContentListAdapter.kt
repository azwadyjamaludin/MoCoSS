package upsi.edu.mocos.model

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.ind_coun_listadapter.view.*
import upsi.edu.mocos.R

class IndCounContentListAdapter(
        context: Context,
        private var numbering: ArrayList<String>
):RecyclerView.Adapter<IndCounContentListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):IndCounContentListAdapter.ViewHolder {
        val inflatedView = parent.inflate(R.layout.ind_coun_listadapter, false)
        return ViewHolder(inflatedView)
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
            if (MiscSetting.BI) {
                view.sesRptBtnInd.text = "Upload"
                view.avBtnInd.text = "Upload"
            }
        }
    }



}
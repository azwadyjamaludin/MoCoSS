package upsi.edu.mocos.adapter.listadapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.ind_coun_listadapter_2.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONIndData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.inflate

class IndCounContentListAdapter2(
        private var numbering: ArrayList<NumberData>,
        private var jsonArrayInd: ArrayList<JSONIndData>
):RecyclerView.Adapter<IndCounContentListAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.ind_coun_listadapter_2, false)
            return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return jsonArrayInd.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number = numbering[position].numData
        val dateEdit = jsonArrayInd[position].sessionDate
        val sessionCode = jsonArrayInd[position].clientCode
        val sessionHour = jsonArrayInd[position].sessionHour
        holder.decoWidget(number,dateEdit,sessionCode,sessionHour)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private var view: View = view
        private var context = view.context


        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            private val NUMBERING_KEY = "NUMBERING"
        }

        fun decoWidget(number: String,dateEdit:String,sessionCode:String, sessionHour:String) {
            view.numDataInd2.text = number
            view.dateEditInd2.text = dateEdit
            view.sesCodeEditInd2.text = sessionCode
            view.sesHourEditInd2.text = sessionHour

            if (MiscSetting.BM) {
                view.sesRptBtnInd2.text = context.getString(R.string.downloadFileMY)
                view.sesRptBtnInd2.setOnClickListener({
                    alertOtherBM()
                })
                view.avBtnInd2.text = context.getString(R.string.downloadFileMY)
                view.avBtnInd2.setOnClickListener({
                    alertOtherBM()
                })
            }

            if (MiscSetting.BI) {
                view.sesRptBtnInd2.text = context.getString(R.string.downloadFileEN)
                view.sesRptBtnInd2.setOnClickListener({
                    alertOtherBI()
                })
                view.avBtnInd2.text = context.getString(R.string.downloadFileEN)
                view.avBtnInd2.setOnClickListener({
                    alertOtherBI()
                })
            }

        }

        private fun alertOtherBM() {
            Toast.makeText(context,"Dalam pembangunan", Toast.LENGTH_SHORT)
        }

        private fun alertOtherBI() {
            Toast.makeText(context,"Under construction",Toast.LENGTH_SHORT)
        }

    }

}
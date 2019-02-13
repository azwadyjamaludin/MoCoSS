package upsi.edu.mocos.adapter.listadapter

import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.ind_coun_listadapter.view.*
import kotlinx.android.synthetic.main.ind_coun_listadapter_2.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONIndData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.inflate

class IndCounContentListAdapter(
        private var numbering: ArrayList<NumberData>,
        private var jsonArrayInd: ArrayList<JSONIndData>
):RecyclerView.Adapter<IndCounContentListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.ind_coun_listadapter, false)
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

        fun decoWidget(number:String,dateEdit:String,sessionCode:String, sessionHour:String) {

            var dateEdit = SpannableStringBuilder(dateEdit)
            var sesCodeEdit = SpannableStringBuilder(sessionCode)
            var sesHourEdit = SpannableStringBuilder(sessionHour)

            view.numDataInd.text = number
            view.dateEditInd.text = dateEdit
            view.sesCodeEditInd.text = sesCodeEdit
            view.sesHourEditInd.text = sesHourEdit

            if (MiscSetting.BM) {
                view.sesRptBtnInd.text = context.getString(R.string.uploadFileMY)
                view.sesRptBtnInd.setOnClickListener({
                    alertOtherBM()
                })
                view.avBtnInd.text = context.getString(R.string.uploadFileMY)
                view.avBtnInd.setOnClickListener({
                    alertOtherBM()
                })
            }

            if (MiscSetting.BI) {
                view.sesRptBtnInd.text = context.getString(R.string.uploadFileEN)
                view.sesRptBtnInd.setOnClickListener({
                    alertOtherBI()
                })
                view.avBtnInd.text = context.getString(R.string.uploadFileEN)
                view.avBtnInd.setOnClickListener({
                    alertOtherBI()
                })
            }

        }

        private fun alertOtherBM() {
            Toast.makeText(context,"Dalam pembangunan",Toast.LENGTH_SHORT)
        }

        private fun alertOtherBI() {
            Toast.makeText(context,"Under Construction",Toast.LENGTH_SHORT)
        }

    }

}
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
import kotlinx.android.synthetic.main.grp_coun_listadapter.view.*
import kotlinx.android.synthetic.main.grp_coun_listadapter_2.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONGrpData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.inflate

class GrpCounContentListAdapter (
        private var numbering: ArrayList<NumberData>,
        private var jsonArrayGrp: ArrayList<JSONGrpData>
): RecyclerView.Adapter<GrpCounContentListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.grp_coun_listadapter, false)
            return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return jsonArrayGrp.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number = numbering[position].numData
        val dateEdit = jsonArrayGrp[position].sessionDate
        val sessionCode = jsonArrayGrp[position].clientGroupCode
        val sessionHour = jsonArrayGrp[position].sessionHour
        holder.decoWidget(number,dateEdit,sessionCode,sessionHour)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private var view: View = view
        private var context  = view.context

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            private val NUMBERING_KEY = "NUMBERING"
        }

        fun decoWidget(number:String,dateEdit:String,sessionCode:String,sessionHour:String) {
            var dateEdit = SpannableStringBuilder(dateEdit)
            var sesCodeEdit = SpannableStringBuilder(sessionCode)
            var sessionHour = SpannableStringBuilder(sessionHour)

            view.numDataGrp.text = number
            view.dateEditGrp.text = dateEdit
            view.sesCodeEditGrp.text = sesCodeEdit
            view.sesHourEditGrp.text = sessionHour

            if (MiscSetting.BM) {
                view.sesRptBtnGrp.text = context.getString(R.string.uploadFileMY)
                view.sesRptBtnGrp.setOnClickListener({
                    alertOtherBM()
                })
                view.avBtnGrp.text = context.getString(R.string.uploadFileMY)
                view.avBtnGrp.setOnClickListener({
                    alertOtherBM()
                })
            }

            if (MiscSetting.BI) {
                view.sesRptBtnGrp.text = context.getString(R.string.uploadFileEN)
                view.sesRptBtnGrp.setOnClickListener({
                    alertOtherBI()
                })
                view.avBtnGrp.text = context.getString(R.string.uploadFileEN)
                view.avBtnGrp.setOnClickListener({
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
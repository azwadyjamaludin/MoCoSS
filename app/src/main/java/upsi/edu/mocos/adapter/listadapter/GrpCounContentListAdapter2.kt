package upsi.edu.mocos.adapter.listadapter

import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import kotlinx.android.synthetic.main.activity_grp_coun.*
import kotlinx.android.synthetic.main.grp_coun_listadapter_2.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.GrpCoun.GrpCounActivity
import upsi.edu.mocos.activity.GrpCoun.GrpCounPDFActivity
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONGrpData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.inflate

class GrpCounContentListAdapter2 (
        private var numbering: ArrayList<NumberData>,
        private var jsonArrayGrp: ArrayList<JSONGrpData>,
        val origin: GrpCounActivity
): RecyclerView.Adapter<GrpCounContentListAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.grp_coun_listadapter_2, false)
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
        holder.decoWidget(number,dateEdit,sessionCode,sessionHour,origin)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
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

        fun decoWidget(number:String,dateEdit:String,sessionCode:String,sessionHour:String,origin: GrpCounActivity) {
            view.numDataGrp2.text = number
            view.dateEditGrp2.text = dateEdit
            view.sesCodeEditGrp2.text = sessionCode
            view.sesHourEditGrp2.text = sessionHour

            if (MiscSetting.BM) {
                view.sesRptBtnGrp2.text = context.getString(R.string.downloadFileMY)
                view.sesRptBtnGrp2.setOnClickListener({
                    val grpCounPDFIntent = Intent(origin,GrpCounPDFActivity::class.java)
                    grpCounPDFIntent.putExtra("downGrp","downMY")
                    origin.startActivity(grpCounPDFIntent)
                })
                view.avBtnGrp2.text = context.getString(R.string.downloadFileMY)
                view.avBtnGrp2.setOnClickListener({
                    alertOtherBM()
                })
            }
            if (MiscSetting.BI) {
                view.sesRptBtnGrp2.text = context.getString(R.string.downloadFileEN)
                view.sesRptBtnGrp2.setOnClickListener({
                    val grpCounPDFIntent = Intent(origin,GrpCounPDFActivity::class.java)
                    grpCounPDFIntent.putExtra("downGrp","downEN")
                    origin.startActivity(grpCounPDFIntent)
                })
                view.avBtnGrp2.text = context.getString(R.string.downloadFileEN)
                view.avBtnGrp2.setOnClickListener({
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
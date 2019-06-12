package upsi.edu.mocos.adapter.listadapter

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import upsi.edu.mocos.activity.IndCoun.IndCounActivity
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONIndData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.inflate

class IndCounContentListAdapter(
        private var numbering: ArrayList<NumberData>,
        private var jsonArrayInd: ArrayList<JSONIndData>,
        val origin: IndCounActivity
):RecyclerView.Adapter<IndCounContentListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.ind_coun_listadapter, false)

            return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return numbering.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number = numbering[position].numData
        val dateEdit = jsonArrayInd[position].sessionDate
        val sessionCode = jsonArrayInd[position].clientCode
        val sessionHour = jsonArrayInd[position].sessionHour
        val note = jsonArrayInd[position].sessionNote
        holder.decoWidget(number,dateEdit,sessionCode,sessionHour,note,origin)

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

        fun decoWidget(number:String,dateEdit:String,sessionCode:String, sessionHour:String, sessionNote:String,origin: IndCounActivity) {

            var dateEdit = SpannableStringBuilder(dateEdit)
            var sesCodeEdit = SpannableStringBuilder(sessionCode)
            var sesHourEdit = SpannableStringBuilder(sessionHour)

            view.numDataInd.text = number
            view.dateEditInd.text = dateEdit
            view.sesCodeEditInd.text = sesCodeEdit
            view.sesHourEditInd.text = sesHourEdit
            view.notesInd.text = sessionNote

            if (MiscSetting.BM) {
                view.sesRptBtnInd.text = context.getString(R.string.uploadFileMY)
                view.sesRptBtnInd.setOnClickListener({
                    indCoudUpMY(origin)
                })
                view.avBtnInd.text = context.getString(R.string.uploadFileMY)
                view.avBtnInd.setOnClickListener({
                    indCoudUpVidMY(origin)
                })
            }

            if (MiscSetting.BI) {
                view.sesRptBtnInd.text = context.getString(R.string.uploadFileEN)
                view.sesRptBtnInd.setOnClickListener({
                    indCounUpEN(origin)
                })
                view.avBtnInd.text = context.getString(R.string.uploadFileEN)
                view.avBtnInd.setOnClickListener({
                    indCounUpVidEN(origin)
                })
            }

        }

        private fun alertOtherBM() {
            Toast.makeText(context,"Dalam pembangunan",Toast.LENGTH_SHORT)
        }

        private fun alertOtherBI() {
            Toast.makeText(context,"Under Construction",Toast.LENGTH_SHORT)
        }

        private fun indCounUpEN(origin: IndCounActivity) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            intent.addCategory(Intent.CATEGORY_OPENABLE)

            try {
                origin.startActivityForResult(
                        Intent.createChooser(intent, "Select a File to Upload"),
                        origin.REQUEST_CHOOSER)
            } catch (ex: android.content.ActivityNotFoundException) {
                // Potentially direct the user to the Market with a Dialog
                Toast.makeText(origin, "Please install a File Manager.",
                        Toast.LENGTH_SHORT).show()
            }

        }

        private fun indCounUpVidEN(origin: IndCounActivity) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "video/*"
            intent.type = "audio/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)

            try {
                origin.startActivityForResult(
                        Intent.createChooser(intent, "Select a File to Upload"),
                        origin.REQUEST_CHOOSER)
            } catch (ex: android.content.ActivityNotFoundException) {
                // Potentially direct the user to the Market with a Dialog
                Toast.makeText(origin, "Please install a File Manager.",
                        Toast.LENGTH_SHORT).show()
            }

        }

        private fun indCoudUpMY(origin: IndCounActivity) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            intent.addCategory(Intent.CATEGORY_OPENABLE)

            try {
                origin.startActivityForResult(
                        Intent.createChooser(intent, "Sila pilih fail untuk muat naik"),
                        origin.REQUEST_CHOOSER)
            } catch (ex: android.content.ActivityNotFoundException) {
                // Potentially direct the user to the Market with a Dialog
                Toast.makeText(origin, "Sila install 'File Manager'.",
                        Toast.LENGTH_SHORT).show()
            }

        }

        private fun indCoudUpVidMY(origin: IndCounActivity) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "video/*"
            intent.type = "audio/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)

            try {
                origin.startActivityForResult(
                        Intent.createChooser(intent, "Sila pilih fail untuk muat naik"),
                        origin.REQUEST_CHOOSER)
            } catch (ex: android.content.ActivityNotFoundException) {
                // Potentially direct the user to the Market with a Dialog
                Toast.makeText(origin, "Sila install 'File Manager'.",
                        Toast.LENGTH_SHORT).show()
            }

        }

    }

}
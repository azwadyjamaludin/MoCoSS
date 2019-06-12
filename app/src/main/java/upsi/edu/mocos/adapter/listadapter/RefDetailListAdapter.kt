package upsi.edu.mocos.adapter.listadapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.ref_detail_list_adapter.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.RefCons.RefDetailActivity
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONRefDetailData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.inflate

class RefDetailListAdapter(
        private var jsonArrayRD: ArrayList<JSONRefDetailData>,
        val origin:RefDetailActivity
): RecyclerView.Adapter<RefDetailListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflatedView = p0.inflate(R.layout.ref_detail_list_adapter, false)

        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return jsonArrayRD.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val number = jsonArrayRD[p1].rDNum
        val studName = jsonArrayRD[p1].rDStudName
        val referRSN = jsonArrayRD[p1].rDReason
        val referNote = jsonArrayRD[p1].rDNote
        p0.decoWidget(number,studName,referRSN,referNote,origin)
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

        fun decoWidget(number:String,studName:String,referRSN:String,referNote:String,origin: RefDetailActivity){
            var rDStudName = SpannableStringBuilder(studName)
            var rDReason = SpannableStringBuilder(referRSN)

            view.refDetailNumA.text = number
            view.refDetailStudA.text = rDStudName
            view.refDetailRSNA.text = rDReason
            view.refDetailNoteA.text = referNote

            if (MiscSetting.BM) {
                view.refDetailUpA.text = context.getString(R.string.uploadFileMY)
                view.refDetailUpA.setOnClickListener {
                    consDetailUpMY(origin)
                }
            }
            if (MiscSetting.BI) {
                view.refDetailUpA.text = context.getString(R.string.uploadFileEN)
                view.refDetailUpA.setOnClickListener {
                    consDetailUpEN(origin)
                }
            }

        }

        private fun consDetailUpMY(origin: RefDetailActivity) {
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

        private fun consDetailUpEN(origin: RefDetailActivity) {
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
    }
}
package upsi.edu.mocos.adapter.listadapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.cons_detail_list_adapter.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.RefCons.ConsDetailActivity
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONConsDetailData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.inflate

class ConsDetailListAdapter(
        private var jsonArrayCD:ArrayList<JSONConsDetailData>,
        val origin: ConsDetailActivity
        ):RecyclerView.Adapter<ConsDetailListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflatedView = p0.inflate(R.layout.cons_detail_list_adapter, false)

        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return jsonArrayCD.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val number = jsonArrayCD[p1].cDNum
        val cEntity = jsonArrayCD[p1].cDEnt
        val cField = jsonArrayCD[p1].cDField
        val cNote = jsonArrayCD[p1].cDNote
        p0.decoWidget(number,cEntity,cField,cNote,origin)
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

        fun decoWidget(number: String, cEntity: String, cField: String, cNote: String,origin: ConsDetailActivity) {
            var cDEnt = SpannableStringBuilder(cEntity)
            var cDField = SpannableStringBuilder(cField)

            view.consDetailNumA.text = number
            view.consDetailEntA.text = cDEnt
            view.consDetailFieldA.text = cDField
            view.consDetailNoteA.text = cNote

            if (MiscSetting.BM) {
                view.consDetailUpA.text = context.getString(R.string.uploadFileMY)
                view.consDetailUpA.setOnClickListener {
                    consDetailUpMY(origin)
                }
            }
            if (MiscSetting.BI) {
                view.consDetailUpA.text = context.getString(R.string.uploadFileEN)
                view.consDetailUpA.setOnClickListener {
                    consDetailUpEN(origin)
                }
            }

        }

        private fun consDetailUpMY(origin: ConsDetailActivity) {
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

        private fun consDetailUpEN(origin: ConsDetailActivity) {
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
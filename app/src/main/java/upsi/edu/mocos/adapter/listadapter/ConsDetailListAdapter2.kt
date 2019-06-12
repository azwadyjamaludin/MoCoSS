package upsi.edu.mocos.adapter.listadapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cons_detail_list_adapter_2.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.RefCons.ConsDetailActivity
import upsi.edu.mocos.activity.RefCons.ConsDetailPDFActivity
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONConsDetailData
import upsi.edu.mocos.model.inflate

class ConsDetailListAdapter2(
        private var jsonArrayCD:ArrayList<JSONConsDetailData>,
        val origin:ConsDetailActivity
        ): RecyclerView.Adapter<ConsDetailListAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflatedView = p0.inflate(R.layout.cons_detail_list_adapter_2, false)

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

        fun decoWidget(number:String,cEntity:String,cField:String,cNote:String,origin:ConsDetailActivity) {
            var cDNote = SpannableStringBuilder(cNote)

            view.consDetailNumA2.text = number
            view.consDetailEntA2.text = cEntity
            view.consDetailFieldA2.text = cField
            view.consDetailNoteA2.text = cDNote

            if (MiscSetting.BM) {
                view.consDetailUpA2.text = context.getString(R.string.downloadFileMY)
                view.consDetailUpA2.setOnClickListener({
                    val consDetailPDFIntent = Intent(context,ConsDetailPDFActivity::class.java)
                    consDetailPDFIntent.putExtra("downConsDet","downMY")
                    origin.startActivity(consDetailPDFIntent)
                })
            }
            if (MiscSetting.BI) {
                view.consDetailUpA2.text = context.getString(R.string.downloadFileEN)
                view.consDetailUpA2.setOnClickListener({
                    val consDetailPDFIntent = Intent(context,ConsDetailPDFActivity::class.java)
                    consDetailPDFIntent.putExtra("downConsDet","downEN")
                    origin.startActivity(consDetailPDFIntent)
                })
            }
        }
    }
}
package upsi.edu.mocos.adapter.listadapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.ref_detail_list_adapter_2.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.RefCons.RefDetailActivity
import upsi.edu.mocos.activity.RefCons.RefDetailPDFActivity
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONRefDetailData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.inflate

class RefDetailListAdapter2(
        private var jsonArrayRD: ArrayList<JSONRefDetailData>,
        val origin: RefDetailActivity
): RecyclerView.Adapter<RefDetailListAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflatedView = p0.inflate(R.layout.ref_detail_list_adapter_2, false)

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


        fun decoWidget(number:String,studName:String,referRSN:String,referNote:String,origin: RefDetailActivity) {
            var rDNote = SpannableStringBuilder(referNote)

            view.refDetailNumA2.text = number
            view.refDetailStudA2.text = studName
            view.refDetailRSNA2.text = referRSN
            view.refDetailNoteA2.text = rDNote

            if (MiscSetting.BM) {
                view.refDetailUpA2.text = context.getString(R.string.downloadFileMY)
                view.refDetailUpA2.setOnClickListener({
                    val consDetailPDFIntent = Intent(context, RefDetailPDFActivity::class.java)
                    consDetailPDFIntent.putExtra("downRefDet","downMY")
                    origin.startActivity(consDetailPDFIntent)
                })
            }
            if (MiscSetting.BI) {
                view.refDetailUpA2.text = context.getString(R.string.downloadFileEN)
                view.refDetailUpA2.setOnClickListener({
                    val consDetailPDFIntent = Intent(context,RefDetailPDFActivity::class.java)
                    consDetailPDFIntent.putExtra("downRefDet","downEN")
                    origin.startActivity(consDetailPDFIntent)
                })
            }
        }
    }
}
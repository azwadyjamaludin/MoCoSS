package upsi.edu.mocos.adapter.listadapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import kotlinx.android.synthetic.main.log_book_listadapter_2.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.InnerListActivity
import upsi.edu.mocos.adapter.miscadapter.LogBookListInnerCustomAdapter2
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.MyObject.NumberMgr
import upsi.edu.mocos.model.inflate

class LogBookListAdapter2(
        private var lbList: ArrayList<NumberData>
): RecyclerView.Adapter<LogBookListAdapter2.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.log_book_listadapter_2)
        /*val context = inflatedView.context
        val innerLV_2nd: ListView = inflatedView.findViewById(R.id.innerLBLV_2nd)
        attachInnerAdapter2(context,innerLV_2nd)*/
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return lbList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number = lbList[position].numData
        holder.decoWidget(number)
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

        fun decoWidget(number: String) {

            if(MiscSetting.BM) {
                view.dateTextLB_2nd.text = "Tarikh hari "+number+" :"
                var dateText = view.dateTextLB_2nd.text
                view.dayTextLB_2nd.text = "Hari :"
                view.timeTextLB_2nd.text = "Masa"
                view.activityLB_2nd.text = "Aktiviti"
                view.notesTextLB_2nd.text = "Catatan"
                view.tabForDetail_2nd.text = "Tab Untuk Butiran Lanjut"
                view.tabForDetail_2nd.setOnClickListener ({
                    val toPageInnerList = Intent(context, InnerListActivity::class.java)
                    toPageInnerList.putExtra("dateText",dateText)
                    context.startActivity(toPageInnerList)
                })

            }
            if (MiscSetting.BI) {
                view.dateTextLB_2nd.text = "Date of day "+number+" :"
                var dateText = view.dateTextLB_2nd.text
                view.dayTextLB_2nd.text = "Day :"
                view.timeTextLB_2nd.text = "Time"
                view.activityLB_2nd.text = "Activity"
                view.notesTextLB_2nd.text = "Notes"
                view.tabForDetail_2nd.text = "Tab For Details"
                view.tabForDetail_2nd.setOnClickListener ({
                    val toPageInnerList =Intent(context,InnerListActivity::class.java)
                    toPageInnerList.putExtra("dateText",dateText)
                    context.startActivity(toPageInnerList)
                })
            }

        }



    }
}
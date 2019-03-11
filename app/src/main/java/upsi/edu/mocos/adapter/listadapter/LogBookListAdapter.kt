package upsi.edu.mocos.adapter.listadapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.log_book_listadapter.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.logBook.InnerListActivity
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.inflate

class LogBookListAdapter(
        private var lbList: ArrayList<NumberData>
): RecyclerView.Adapter<LogBookListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.log_book_listadapter)

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
                view.dateTextLB.text = "Tarikh hari "+number+" :"
                var dateText = view.dateTextLB.text
                view.dayTextLB.text = "Hari :"
                view.timeTextLB.text = "Masa"
                view.activityLB.text = "Aktiviti"
                view.notesTextLB.text = "Catatan"
                view.tabForDetail.text = "Tab Untuk Butiran Lanjut"
                view.tabForDetail.setOnClickListener ({
                    val toPageInnerList =Intent(context, InnerListActivity::class.java)
                    toPageInnerList.putExtra("dateTextMY",dateText)
                    context.startActivity(toPageInnerList)
                })

            }
            if (MiscSetting.BI) {
                view.dateTextLB.text = "Date of day "+number+" :"
                var dateText = view.dateTextLB.text
                view.dayTextLB.text = "Day :"
                view.timeTextLB.text = "Time"
                view.activityLB.text = "Activity"
                view.notesTextLB.text = "Notes"
                view.tabForDetail.text = "Tab For Details"
                view.tabForDetail.setOnClickListener ({
                    val toPageInnerList =Intent(context, InnerListActivity::class.java)
                    toPageInnerList.putExtra("dateTextEN",dateText)
                    context.startActivity(toPageInnerList)
                })
            }


        }

    }
}
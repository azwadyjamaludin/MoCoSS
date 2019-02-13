package upsi.edu.mocos.adapter.miscadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MyData.InnerListData

class LogBookListInnerCustomAdapter2 :BaseAdapter {
    private var context: Context? = null
    private var dataList = ArrayList<InnerListData> ()

    constructor(context: Context, datalist:ArrayList<InnerListData>):super() {
        this.context = context
        this.dataList = datalist
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View?
        val vh: ViewHolder
        val inflater: LayoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (p1 == null) {
            view = inflater.inflate(R.layout.log_book_inner_listadapter_2,p2,false)
            vh = ViewHolder(view)
            view.tag = vh
        }else {
            view = p1
            vh = view.tag as ViewHolder
        }
        vh.numDataLB_2nd.text = dataList[p0].numData
        vh.timeEditLB_2nd.text = dataList[p0].timeData
        vh.activityEditLB_2nd.text = dataList[p0].activityData
        vh.notesEditLB_2nd.text = dataList[p0].notesData
        return view!!
    }

    override fun getItem(p0: Int): Any {
        return dataList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return dataList.size

    }

    private class ViewHolder(view: View?) {
        val numDataLB_2nd: TextView
        val timeEditLB_2nd: TextView
        val activityEditLB_2nd: TextView
        val notesEditLB_2nd: TextView


    init {
        this.numDataLB_2nd = view?.findViewById(R.id.numDataLB_2nd) as TextView
        this.timeEditLB_2nd = view?.findViewById(R.id.timeEditLB_2nd) as TextView
        this.activityEditLB_2nd = view?.findViewById(R.id.activityEditLB_2nd) as TextView
        this.notesEditLB_2nd = view?.findViewById(R.id.notesEditLB_2nd) as TextView
        }
    }
}
package upsi.edu.mocos.adapter.miscadapter

import android.content.Context
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MyData.InnerListData

class LogBookListInnerCustomAdapter : BaseAdapter {
    private var context: Context? = null
    private var dataList = ArrayList<InnerListData> ()

    constructor(context: Context,datalist:ArrayList<InnerListData> ):super() {
        this.context = context
        this.dataList = datalist
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View?
        val vh: ViewHolder
        val inflater: LayoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (p1 == null) {
            view = inflater.inflate(R.layout.log_book_inner_listadapter,p2,false)
            vh = ViewHolder(view)
            view.tag = vh
        }else {
            view = p1
            vh = view.tag as ViewHolder
        }
        var timeData = SpannableStringBuilder(dataList[p0].timeData)
        var activityData = SpannableStringBuilder(dataList[p0].activityData)
        var notesData = SpannableStringBuilder(dataList[p0].notesData)

        vh.numDataLB.text = dataList[p0].numData
        vh.timeEditLB.text = timeData
        vh.activityEditLB.text = activityData
        vh.notesEditLB.text = notesData

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
        val numDataLB: TextView
        val timeEditLB: EditText
        val activityEditLB: EditText
        val notesEditLB: EditText

        init {
            this.numDataLB = view?.findViewById(R.id.numDataLB) as TextView
            this.timeEditLB = view?.findViewById(R.id.timeEditLB) as EditText
            this.activityEditLB = view?.findViewById(R.id.activityEditLB) as EditText
            this.notesEditLB = view?.findViewById(R.id.notesEditLB)
        }
    }


}
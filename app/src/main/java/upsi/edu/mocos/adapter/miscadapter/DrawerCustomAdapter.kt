package upsi.edu.mocos.adapter.miscadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MyData.DrawerData

class DrawerCustomAdapter: BaseAdapter {

    private var drawerDataList = ArrayList<DrawerData>()
    private var context: Context? = null


    constructor(context: Context, drawerDataList: ArrayList<DrawerData>) :super() {
        this.drawerDataList = drawerDataList
        this.context = context
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View?
        val vh: ViewHolder
        val inflater: LayoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (p1 == null) {
            view = inflater.inflate(R.layout.custom_list_layout, p2,false)
            vh = ViewHolder(view)
            view.tag = vh
        } else {
            view = p1
            vh = view.tag as ViewHolder
        }
        vh.customImage.text = drawerDataList[p0].buttonText
        return view!!
    }

    override fun getItem(p0: Int): Any {
        return drawerDataList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return drawerDataList.size
    }

}

private class ViewHolder (view: View?) {
        val customImage:TextView

    init {
        this.customImage = view?.findViewById(R.id.customImage) as TextView
    }
}

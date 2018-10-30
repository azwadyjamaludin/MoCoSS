package upsi.edu.mocos.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MyData.DrawerData

class DrawerCustomAdapter(context: Context, resource:Int, list:ArrayList<DrawerData> )
    : ArrayAdapter<DrawerData>(context, resource, list) {
    var resource: Int
    var inflater: LayoutInflater
    var drawerData = arrayListOf<DrawerData>()

    init {
        this.drawerData = list
        this.resource = resource
        this.inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var drawerData = getItem(position)
        val retView = inflater.inflate(resource,parent,false)
        //val customButton = retView.findViewById(R.id.customButton) as LinearLayout
        val buttonText = retView.findViewById(R.id.customImage) as TextView
        buttonText.text = drawerData.buttonText
        //buttonText.typeface = Typeface.DEFAULT_BOLD

        return retView
    }

}

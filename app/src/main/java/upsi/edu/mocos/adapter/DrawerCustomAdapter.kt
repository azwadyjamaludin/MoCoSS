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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val retView = super.getView(position, convertView, parent)
        val customButton = retView.findViewById<LinearLayout>(R.id.customButton)
        val buttonText = customButton.findViewById<TextView>(R.id.buttonText)
        buttonText.typeface = Typeface.DEFAULT_BOLD

        return retView
    }

    internal class ViewHolder {
        //var image: ImageView? = null
    }
}

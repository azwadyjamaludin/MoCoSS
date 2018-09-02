package upsi.edu.mocos.adapter.listadapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.intern_info_listadapter.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MyData.ImgInfo
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.inflate

class InternInfoListAdapter(
        private var internInfoList: ArrayList<ImgInfo>,
        private var numbering: ArrayList<NumberData>
):RecyclerView.Adapter<InternInfoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InternInfoListAdapter.ViewHolder {
        val inflatedView = parent.inflate(R.layout.intern_info_listadapter)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return internInfoList.size
    }

    override fun onBindViewHolder(holder: InternInfoListAdapter.ViewHolder, position: Int) {
        val imgID = internInfoList[position].imgID
        Log.d("increasedCachedMemory", numbering.toString())
        holder.decoWidget(imgID)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private var view: View = view

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            private val IMG_ID = "IMG_ID"
        }

        fun decoWidget(imgID: String) {
            val infoImg = view.infoImg
            val context = view.context
            Picasso.with(context)
                    .load(view.resources.getIdentifier(imgID, "drawable", context.packageName))
                    .into(infoImg)

        }

    }
}
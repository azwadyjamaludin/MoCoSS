package upsi.edu.mocos.adapter.listadapter

import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.content.FileProvider
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import kotlinx.android.synthetic.main.activity_reflect.*
import kotlinx.android.synthetic.main.reflect_list_adapter_2.view.*
import upsi.edu.mocos.BuildConfig
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.Reflect.ReflectActivity
import upsi.edu.mocos.activity.Reflect.ReflectPDFActivity
import upsi.edu.mocos.model.DummyData
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.inflate

class ReflectListAdapter2(
private var numbering: ArrayList<NumberData>,
val origin: ReflectActivity
):RecyclerView.Adapter<ReflectListAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.reflect_list_adapter_2, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return numbering.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number = numbering[position].numData
        holder.decoWidget(number,origin)

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private var view: View = view
        private var context = view.context
        val dd: DummyData = DummyData("DummyData")

        init {
            view.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            private val NUMBERING_KEY = "NUMBERING"
        }

        fun decoWidget(number: String,origin: ReflectActivity) {
            val reflectWeekText2 = view.reflectWeekText2
            val reflectDownloadBtn = view.reflectDownloadBtn
            val shareReflect = view.shareReflect

            reflectWeekText2.text = number
            if (MiscSetting.BM) {
                reflectDownloadBtn.text = context.getString(R.string.downloadFileMY)
                reflectDownloadBtn.setOnClickListener({
                    val refPDFIntent = Intent(origin,ReflectPDFActivity::class.java)
                    refPDFIntent.putExtra("Ref", "Reflect")
                    origin.startActivity(refPDFIntent)
                })
                shareReflect.text = context.getString(R.string.shareMY)
                shareReflect.setOnCheckedChangeListener({ _: CompoundButton, isChecked ->
                    if (shareReflect.isChecked) {
                        val shareIntent = Intent(android.content.Intent.ACTION_SEND)
                        shareIntent.setDataAndType(Uri.fromFile(dd.writeReflect(origin)), "application/pdf")
                        shareReflect.isChecked = false
                        origin.startActivity(Intent.createChooser(shareIntent, "Kongsi fail dengan"))
                    }
                })
            }
            if (MiscSetting.BI) {
                reflectDownloadBtn.text = context.getString(R.string.downloadFileEN)
                reflectDownloadBtn.setOnClickListener({
                    val refPDFIntent = Intent(origin,ReflectPDFActivity::class.java)
                    refPDFIntent.putExtra("Ref", "Reflect")
                    origin.startActivity(refPDFIntent)
                })
                shareReflect.text = context.getString(R.string.shareEN)
                shareReflect.setOnCheckedChangeListener({ _: CompoundButton, isChecked ->
                    if (shareReflect.isChecked) {
                        val shareIntent = Intent(android.content.Intent.ACTION_SEND)
                        shareIntent.setDataAndType(Uri.fromFile(dd.writeReflect(origin)), "application/pdf")
                        shareReflect.isChecked = false
                        origin.startActivity(Intent.createChooser(shareIntent, "Share file using"))
                    }
                })
            }
        }
    }
}

package upsi.edu.mocos.adapter.listadapter

import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import kotlinx.android.synthetic.main.activity_case_anal_2.view.*
import kotlinx.android.synthetic.main.activity_reflect.*
import kotlinx.android.synthetic.main.reflect_list_adapter_2.view.*
import org.jetbrains.anko.doAsync
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.Reflect.ReflectActivity
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.MyObject.DummyMgr
import upsi.edu.mocos.model.MyObject.NumberMgr
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

            reflectWeekText2.text = number
            if (MiscSetting.BM) {
                reflectDownloadBtn.text = context.getString(R.string.downloadFileMY)
            }
            if (MiscSetting.BI) {
                reflectDownloadBtn.text = context.getString(R.string.downloadFileEN)
            }
            reflectDownloadBtn.setOnClickListener({

                var pdfView = origin.reflectPDF
                pdfView.visibility = View.VISIBLE
                pdfView.fromAsset("Reflect.pdf")
                        .defaultPage(origin.pageNumber)
                        .enableSwipe(true)
                        .swipeHorizontal(false)
                        .onPageChange(origin)
                        .enableAnnotationRendering(true)
                        .onLoad(origin)
                        .scrollHandle(DefaultScrollHandle(origin))
                        .load()

            })

        }
    }
}

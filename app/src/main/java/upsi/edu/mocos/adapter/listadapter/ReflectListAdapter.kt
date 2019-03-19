package upsi.edu.mocos.adapter.listadapter

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.reflect_list_adapter.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.Reflect.ReflectActivity
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.inflate

class ReflectListAdapter(
private var numbering: ArrayList<NumberData>,
val origin:ReflectActivity
):RecyclerView.Adapter<ReflectListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.reflect_list_adapter, false)
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
            val reflectWeekText = view.reflectWeekText
            val reflectAttachBtn = view.reflectAttachBtn

                    reflectWeekText.text = number
                    if (MiscSetting.BM) {
                        reflectAttachBtn.text = context.getString(R.string.attachFileMY)
                        reflectAttachBtn.setOnClickListener({
                            val intent = Intent(Intent.ACTION_GET_CONTENT)
                            intent.type = "*/*"
                            intent.addCategory(Intent.CATEGORY_OPENABLE)

                            try {
                                origin.startActivityForResult(
                                        Intent.createChooser(intent, "Pilih fail untuk muat naik"),
                                        origin.REQUEST_CHOOSER)
                            } catch (ex: android.content.ActivityNotFoundException) {
                                // Potentially direct the user to the Market with a Dialog
                                Toast.makeText(context, "Sila install 'File Manager'.",
                                        Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                    if (MiscSetting.BI) {
                        reflectAttachBtn.text = context.getString(R.string.attachFileEN)
                        reflectAttachBtn.setOnClickListener({
                            val intent = Intent(Intent.ACTION_GET_CONTENT)
                            intent.type = "*/*"
                            intent.addCategory(Intent.CATEGORY_OPENABLE)

                            try {
                                origin.startActivityForResult(
                                        Intent.createChooser(intent, "Select a File to Upload"),
                                        origin.REQUEST_CHOOSER)
                            } catch (ex: android.content.ActivityNotFoundException) {
                                // Potentially direct the user to the Market with a Dialog
                                Toast.makeText(context, "Please install a File Manager.",
                                        Toast.LENGTH_SHORT).show()
                            }
                        })
                    }

        }

    }
}

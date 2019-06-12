package upsi.edu.mocos.adapter.listadapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.psy_test_list_adapter.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.PsyTest.PsyTestActivity
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONPsyTestData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.inflate

class PsyTestListAdapter(
        private var jsonArrayPsyTest: ArrayList<JSONPsyTestData>,
        val origin: PsyTestActivity
        ):RecyclerView.Adapter<PsyTestListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflatedView = p0.inflate(R.layout.psy_test_list_adapter, false)

        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return jsonArrayPsyTest.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val number = jsonArrayPsyTest[p1].psyTestNum
        val date = jsonArrayPsyTest[p1].psyTestDate
        val time = jsonArrayPsyTest[p1].psyTestHour
        val studCode = jsonArrayPsyTest[p1].studCode
        val invName = jsonArrayPsyTest[p1].invenName
        val note = jsonArrayPsyTest[p1].psytestNote
        p0.decoWidget(number,date,time,studCode,invName,note,origin)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
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

        fun decoWidget(number:String,date:String,time:String,studCode:String,invName:String,note:String,origin:PsyTestActivity) {
            var date = SpannableStringBuilder(date)
            var time = SpannableStringBuilder(time)
            var studCode = SpannableStringBuilder(studCode)
            var invName = SpannableStringBuilder(invName)

            view.psyTestNumE.text = number
            view.psyTestDateE.text = date
            view.psyTestHourE.text = time
            view.psyTestStudCodeE.text = studCode
            view.psyTestInvNameE.text = invName
            view.psyTestNote.text = note

            if (MiscSetting.BM) {
                view.psyTestUpFileE.text = context.getString(R.string.uploadFileMY)
                view.psyTestUpFileE.setOnClickListener {
                    psyTestUpMY(origin)
                }
            }
            if (MiscSetting.BI) {
                view.psyTestUpFileE.text = context.getString(R.string.uploadFileEN)
                view.psyTestUpFileE.setOnClickListener {
                    psyTestUpEN(origin)
                }
            }
        }

        private fun psyTestUpMY(origin:PsyTestActivity) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            intent.addCategory(Intent.CATEGORY_OPENABLE)

            try {
                origin.startActivityForResult(
                        Intent.createChooser(intent, "Sila pilih fail untuk muat naik"),
                        origin.REQUEST_CHOOSER)
            } catch (ex: android.content.ActivityNotFoundException) {
                // Potentially direct the user to the Market with a Dialog
                Toast.makeText(origin, "Sila install 'File Manager'.",
                        Toast.LENGTH_SHORT).show()
            }
        }

        private fun psyTestUpEN(origin:PsyTestActivity) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            intent.addCategory(Intent.CATEGORY_OPENABLE)

            try {
                origin.startActivityForResult(
                        Intent.createChooser(intent, "Select a File to Upload"),
                        origin.REQUEST_CHOOSER)
            } catch (ex: android.content.ActivityNotFoundException) {
                // Potentially direct the user to the Market with a Dialog
                Toast.makeText(origin, "Please install a File Manager.",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }
}
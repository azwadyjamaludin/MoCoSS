package upsi.edu.mocos.adapter.listadapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.psy_test_list_adapter_2.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.PsyTest.PsyTestActivity
import upsi.edu.mocos.activity.PsyTest.PsyTestPDFActivity
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONPsyTestData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.inflate

class PsyTestListAdapter2(
        private var jsonArrayPsyTest: ArrayList<JSONPsyTestData>,
        val origin: PsyTestActivity
):RecyclerView.Adapter<PsyTestListAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PsyTestListAdapter2.ViewHolder {
        val inflatedView = p0.inflate(R.layout.psy_test_list_adapter_2, false)

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
            var psytestNote = SpannableStringBuilder(note)

            view.psyTestNumE2.text = number
            view.psyTestDateE2.text = date
            view.psyTestHourE2.text = time
            view.psyTestStudCodeE2.text = studCode
            view.psyTestInvNameE2.text = invName

            if (MiscSetting.BM) {
                view.psyTestUpFileE2.text = context.getString(R.string.downloadFileMY)
                view.psyTestUpFileE2.setOnClickListener({
                    val psyTestPDFIntent = Intent(context, PsyTestPDFActivity::class.java)
                    psyTestPDFIntent.putExtra("downPsyTest","downMY")
                    origin.startActivity(psyTestPDFIntent)
                })
            }
            if (MiscSetting.BI) {
                view.psyTestUpFileE2.text = context.getString(R.string.downloadFileEN)
                view.psyTestUpFileE2.setOnClickListener({
                    val psyTestPDFIntent = Intent(context,PsyTestPDFActivity::class.java)
                    psyTestPDFIntent.putExtra("downPsyTest","downEN")
                    origin.startActivity(psyTestPDFIntent)
                })
            }
        }
    }
}
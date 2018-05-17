package upsi.edu.mocos.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_ind_coun.*
import kotlinx.android.synthetic.main.activity_ind_coun.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.IndCounContentListAdapter
import upsi.edu.mocos.model.MiscSetting

class IndCounActivity : AppCompatActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    var list = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ind_coun)
        layoutManager = LinearLayoutManager(this)
        indCounLV.layoutManager = layoutManager

        initPage(indCounActivity)
        newText(indCounActivity)
        attachAdapter(indCounActivity)
    }

    private fun attachAdapter(view: View) {
        val listadapter = IndCounContentListAdapter(this,addElement())
        val indCounLV = view.indCounLV

        indCounLV.adapter = listadapter

    }

    private fun initPage(view: View) {
        val indCoun = view.indCounTB
        if (MiscSetting.BM) {
            indCoun.title = "Kaunseling Individu"
        }
        if (MiscSetting.BI) {
            indCoun.title = "Individual Counseling"
        }
    }

    private fun newText(view: View) {
        if(MiscSetting.BM) {
            view.numTextInd.text = "Bil."
            view.dateTextInd.text = "Tarikh"
            view.sescodeTextInd.text = "Kod Sesi"
            view.seshourTextInd.text = "Jam Sesi"
            view.sesrptTextInd.text = "Laporan Sesi"
            view.avTextInd.text = "Rekod Audio/Video"
            view.noteTextInd.text = "Catatan"

        }
        if(MiscSetting.BI) {
            view.numTextInd.text = "No."
            view.dateTextInd.text = "Date"
            view.sescodeTextInd.text = "Session Code"
            view.seshourTextInd.text = "Session Hour"
            view.sesrptTextInd.text = "Session Report"
            view.avTextInd.text = "Audio/Video Record"
            view.noteTextInd.text = "Notes"
        }
    }

    private fun addElement():ArrayList<String> {
        for (item in 1..120) {
            list.add(item.toString())
            print("list:"+list)
        }
        return list
    }
}


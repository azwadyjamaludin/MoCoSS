package upsi.edu.mocos.activity.GrpCoun

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.ipaulpro.afilechooser.utils.FileUtils
import com.shockwave.pdfium.PdfDocument
import kotlinx.android.synthetic.main.activity_grp_coun.*
import kotlinx.android.synthetic.main.activity_grp_coun.view.*
import org.jetbrains.anko.doAsync
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.MoCoSSParentActivity
import upsi.edu.mocos.adapter.listadapter.GrpCounContentListAdapter
import upsi.edu.mocos.adapter.listadapter.GrpCounContentListAdapter2
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONGrpData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.MyObject.JSONMgr
import upsi.edu.mocos.model.MyObject.NumberMgr
import upsi.edu.mocos.model.PageNavigate

class GrpCounActivity : MoCoSSParentActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    var jsonGrpData:ArrayList<JSONGrpData> = arrayListOf()
    var numbering:ArrayList<NumberData> = arrayListOf()
    var totalHourGrp:ArrayList<Int> = arrayListOf()
    val REQUEST_CHOOSER = 1234


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grp_coun)

        layoutManager = LinearLayoutManager(this)
        grpCounLV.layoutManager = layoutManager

        initPage(grpCounActivity)
        attachAdapter(grpCounActivity)
        newText(grpCounActivity)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CHOOSER -> if (resultCode == Activity.RESULT_OK) {
                // Get the Uri of the selected file
                val uri = data!!.data
                Log.d("TAG", "File Uri: " + uri!!.toString())
                // Get the path
                val path = FileUtils.getPath(this, uri)
                Log.d("TAG", "File Path: " + path!!)
                // Get the file instance
                // File file = new File(path);
                // Initiate the upload
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.grp_coun_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        var score :MenuItem = menu!!.findItem(R.id.scoreGC)
        if (MiscSetting.BM) {
            score.title = getString(R.string.scoreMY)
        }
        if (MiscSetting.BI) {
            score.title = getString(R.string.scoreEN)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId) {
                R.id.scoreGC -> {
                    if (MiscSetting.user == "sl") {
                        val grpCounIntent = Intent(this, GrpCounScoreActivity::class.java)
                        startActivity(grpCounIntent)
                    }
                    if (MiscSetting.user == "gc" || MiscSetting.user == "tc") {
                        if(MiscSetting.BM) {
                            alertOtherBM()
                        }
                        if (MiscSetting.BI) {
                            alertOtherBI()
                        }
                    }
                    return true
                }
                else -> return super.onOptionsItemSelected(item)
            }
    }

    override fun onBackPressed() {
        totalHourGrp.clear()
        goToPage(PageNavigate.ContentPage,this@GrpCounActivity)

    }



    private fun attachAdapter(view: View) {
        val origin = this
            doAsync {
        if (MiscSetting.user == "tc") {
            numbering = NumberMgr.numInputGrp()
            jsonGrpData = JSONMgr.parseJSONGrpData(this@GrpCounActivity)
            val listadapter = GrpCounContentListAdapter(numbering,jsonGrpData,origin)
            val grpCounLV = view.grpCounLV

            grpCounLV.adapter = listadapter
        }
        if (MiscSetting.user == "gc"||MiscSetting.user == "sl") {
            numbering = NumberMgr.numInputGrp()
            jsonGrpData = JSONMgr.parseJSONGrpData(this@GrpCounActivity)
            val listadapter = GrpCounContentListAdapter2(numbering,jsonGrpData,origin)
            val grpCounLV = view.grpCounLV

            grpCounLV.adapter = listadapter
        }

        view.totalHourGrp.text = totalHour()
            }
    }

    fun initPage(view: View) {
        val grpCoun = view.grpCounTB

        if (MiscSetting.BM) {
            grpCoun.title = getString(R.string.content2MY)
        }
        if (MiscSetting.BI) {
            grpCoun.title = getString(R.string.content2EN)
        }
        setSupportActionBar(grpCoun)
        supportActionBar
    }

    fun newText(view: View) {

        if(MiscSetting.BM) {
            view.numTextGrp.text = getString(R.string.numberMy)
            view.dateTextGrp.text = getString(R.string.dateMy)
            view.sescodeTextGrp.text = "Kod Sesi"
            view.seshourTextGrp.text = "Jam Sesi"
            view.sesrptTextGrp.text = "Laporan Sesi"
            view.avTextGrp.text = "Rekod Audio/Video"
            view.noteTextGrp.text = getString(R.string.notesMY)
            view.totHourTextGrp.text = getString(R.string.totalHourMY)

        }
        if(MiscSetting.BI) {
            view.numTextGrp.text = getString(R.string.numberEN)
            view.dateTextGrp.text = getString(R.string.dateEN)
            view.sescodeTextGrp.text = "Session Code"
            view.seshourTextGrp.text = "Session Hour"
            view.sesrptTextGrp.text = "Session Report"
            view.avTextGrp.text = "Audio/Video Record"
            view.noteTextGrp.text = getString(R.string.notesEN)
            view.totHourTextGrp.text = getString(R.string.totalHourEN)
        }

    }

    private fun getHoursGrp():ArrayList<Int> {
        totalHourGrp = JSONMgr.getIntHoursGrp(this@GrpCounActivity)
        return totalHourGrp
    }

    private fun totalHour():String{
        var numArray:ArrayList<Int> = getHoursGrp()
        var sum = 0
        for (num in numArray) {
            sum += num
        }
        return sum.toString()
    }

    private fun alertOtherBM() {
        val dialogBuilder = AlertDialog.Builder(this)
        val dismissDialog = LayoutInflater.from(this).inflate(R.layout.dialog_no_input,null)
        val wrCrText = dismissDialog.findViewById<TextView>(R.id.wrongCredText)
        val dismissBtn = dismissDialog.findViewById<Button>(R.id.dismissButton)

        dialogBuilder.setView(dismissDialog)
        val alert = dialogBuilder.create()
        wrCrText.setText("Anda tiada akses")
        dismissBtn.setText("Keluar")
        dismissBtn.setOnClickListener({
            alert.dismiss()
        })
        alert.show()
    }

    private fun alertOtherBI() {
        val dialogBuilder = AlertDialog.Builder(this)
        val dismissDialog = LayoutInflater.from(this).inflate(R.layout.dialog_no_input,null)
        val wrCrText = dismissDialog.findViewById<TextView>(R.id.wrongCredText)
        val dismissBtn = dismissDialog.findViewById<Button>(R.id.dismissButton)

        dialogBuilder.setView(dismissDialog)
        val alert = dialogBuilder.create()
        wrCrText.setText("You don't have access")
        dismissBtn.setText("Dismiss")
        dismissBtn.setOnClickListener({
            alert.dismiss()
        })
        alert.show()
    }
}

package upsi.edu.mocos.activity.IndCoun

import android.content.ComponentCallbacks2
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_ind_coun.*
import kotlinx.android.synthetic.main.activity_ind_coun.view.*
import org.jetbrains.anko.doAsync
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.MoCoSSParentActivity
import upsi.edu.mocos.adapter.listadapter.IndCounContentListAdapter
import upsi.edu.mocos.adapter.listadapter.IndCounContentListAdapter2
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONIndData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.MyObject.JSONMgr
import upsi.edu.mocos.model.MyObject.NumberMgr
import upsi.edu.mocos.model.PageNavigate
import android.app.Activity
import android.util.Log
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.ipaulpro.afilechooser.utils.FileUtils
import com.shockwave.pdfium.PdfDocument


class IndCounActivity : MoCoSSParentActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    var jsonDataInd:ArrayList<JSONIndData> = arrayListOf()
    var numbering:ArrayList<NumberData> = arrayListOf()
    var totalHourInd:ArrayList<Int> = arrayListOf()
    val REQUEST_CHOOSER = 1234
    var pageNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ind_coun)

        layoutManager = LinearLayoutManager(this)
        indCounLV.layoutManager = layoutManager

        initPage(indCounActivity)
        newText(indCounActivity)
        attachAdapter(indCounActivity)
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
            menuInflater.inflate(R.menu.ind_coun_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        var score :MenuItem = menu!!.findItem(R.id.scoreIC)
        if (MiscSetting.BM) {
            score.title = getString(R.string.scoreMY)
        }
        if (MiscSetting.BI) {
            score.title = getString(R.string.scoreEN)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.scoreIC -> {
                if (MiscSetting.user == "sl") {
                    val indCounIntent = Intent(this, IndCounScoreActivity::class.java)
                    startActivity(indCounIntent)
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
        totalHourInd.clear()
        goToPage(PageNavigate.ContentPage,this@IndCounActivity)
    }


    private fun attachAdapter(view: View) {
        val origin = this
            doAsync {
        if (MiscSetting.user == "tc") {
            jsonDataInd = JSONMgr.parseJSONIndData(this@IndCounActivity)
            numbering = NumberMgr.increaseCached()
            val listadapter = IndCounContentListAdapter(numbering,jsonDataInd,origin)
            val indCounLV = view.indCounLV

            indCounLV.adapter = listadapter
        }
        if (MiscSetting.user == "gc"||MiscSetting.user == "sl") {
            numbering = NumberMgr.increaseCached()
            jsonDataInd = JSONMgr.parseJSONIndData(this@IndCounActivity)
            val listadapter = IndCounContentListAdapter2(numbering,jsonDataInd,origin)
            val indCounLV = view.indCounLV

            indCounLV.adapter = listadapter
        }
        view.totalHourInd.text = totalHour()
            }
    }

    private fun initPage(view: View) {
        val indCoun = view.indCounTB

        if (MiscSetting.BM) {
            indCoun.title = getString(R.string.content1MY)
        }
        if (MiscSetting.BI) {
            indCoun.title = getString(R.string.content1EN)
        }
        setSupportActionBar(indCoun)
        supportActionBar
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
            view.totHourTextInd.text = "Jumlah Jam:"

        }
        if(MiscSetting.BI) {
            view.numTextInd.text = "No."
            view.dateTextInd.text = "Date"
            view.sescodeTextInd.text = "Session Code"
            view.seshourTextInd.text = "Session Hour"
            view.sesrptTextInd.text = "Session Report"
            view.avTextInd.text = "Audio/Video Record"
            view.noteTextInd.text = "Notes"
            view.totHourTextInd.text = "Total Hour:"
        }

    }

    private fun getHoursInd():ArrayList<Int> {
        totalHourInd = JSONMgr.getIntHoursInd(this@IndCounActivity)
        return totalHourInd
    }

    private fun totalHour():String{
        var numArray:ArrayList<Int> = getHoursInd()
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


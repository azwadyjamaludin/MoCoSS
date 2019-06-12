package upsi.edu.mocos.activity.PsyTest

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
import kotlinx.android.synthetic.main.activity_psy_test.*
import kotlinx.android.synthetic.main.activity_psy_test.view.*
import org.jetbrains.anko.doAsync
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.MoCoSSParentActivity
import upsi.edu.mocos.adapter.listadapter.PsyTestListAdapter
import upsi.edu.mocos.adapter.listadapter.PsyTestListAdapter2
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.JSONPsyTestData
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.MyObject.JSONMgr
import upsi.edu.mocos.model.MyObject.NumberMgr
import upsi.edu.mocos.model.PageNavigate

class PsyTestActivity : MoCoSSParentActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    var numbering:ArrayList<NumberData> = arrayListOf()
    var jsonDataPsyTest:ArrayList<JSONPsyTestData> = arrayListOf()
    var totalHourPsyTest:ArrayList<Int> = arrayListOf()
    val REQUEST_CHOOSER = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_psy_test)

        layoutManager = LinearLayoutManager(this)
        psyTestRV.layoutManager = layoutManager

        attachAdapter(psyTestCL)
        initPage(psyTestCL)
        titleRowText(psyTestCL)
    }

    override fun onBackPressed() {
        totalHourPsyTest.clear()
        goToPage(PageNavigate.ContentPage, this@PsyTestActivity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.psy_test_menu,menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val score: MenuItem = menu!!.findItem(R.id.scorePsyTest)
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
            R.id.scorePsyTest -> {
                if (MiscSetting.user == "sl") {
                    val psyTestIntent = Intent(this, PsyTestScoreActivity::class.java)
                    startActivity(psyTestIntent)
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

    private fun attachAdapter(view: View) {
        val origin = this
        doAsync {
            if (MiscSetting.user == "tc") {
                numbering = NumberMgr.increaseCached()
                jsonDataPsyTest = JSONMgr.parseJSONPsyTestData(this@PsyTestActivity)
                val listAdapter = PsyTestListAdapter(jsonDataPsyTest,origin)
                val psyTestRV = view.psyTestRV

                psyTestRV.adapter = listAdapter
            }
            if (MiscSetting.user == "gc"||MiscSetting.user == "sl") {
                numbering = NumberMgr.increaseCached()
                jsonDataPsyTest = JSONMgr.parseJSONPsyTestData(this@PsyTestActivity)
                val listAdapter = PsyTestListAdapter2(jsonDataPsyTest,origin)
                val psyTestRV = view.psyTestRV

                psyTestRV.adapter = listAdapter
            }

        }
    }

    private fun initPage(view: View) {
        val psyTestTB = view.psyTestTB
        if (MiscSetting.BM) {
            psyTestTB.title = getString(R.string.content4MY)
        }
        if (MiscSetting.BI) {
            psyTestTB.title = getString(R.string.content4EN)
        }
        setSupportActionBar(psyTestTB)
        supportActionBar
    }

    private fun titleRowText(view: View) {
        if (MiscSetting.BM) {
            view.psyTestNum.text = getString(R.string.numberMy)
            view.psyTestDate.text = getString(R.string.dateMy)
            view.psyTestHour.text = "Masa"
            view.psyTestStudCode.text = "Kod Pelajar"
            view.psyTestInvName.text = "Nama Inventori"
            view.psyTestUpFileB.text = "Laporan Inventori"
            view.psyTestNote.text = getString(R.string.notesMY)
            view.psyTestHourCal.text = getString(R.string.totalHourMY)
        }
        if (MiscSetting.BI) {
            view.psyTestNum.text = getString(R.string.numberEN)
            view.psyTestDate.text = getString(R.string.dateEN)
            view.psyTestHour.text = "Time"
            view.psyTestStudCode.text = "Student Code"
            view.psyTestInvName.text = "Inventory Name"
            view.psyTestUpFileB.text = "Inventory Report"
            view.psyTestNote.text = getString(R.string.notesEN)
            view.psyTestHourCal.text = getString(R.string.totalHourEN)
        }
        view.psyTestCalHour.text = totalHour()
    }

    private fun totalHour():String{
        var numArray:ArrayList<Int> = getHoursPsyTest()
        var sum = 0
        for (num in numArray) {
            sum += num
        }
        return sum.toString()
    }

    private fun getHoursPsyTest():ArrayList<Int> {
        totalHourPsyTest = JSONMgr.getIntHourPsyTest(this@PsyTestActivity)
        return totalHourPsyTest
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

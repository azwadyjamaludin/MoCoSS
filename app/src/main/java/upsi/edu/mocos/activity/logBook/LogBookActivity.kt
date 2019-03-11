package upsi.edu.mocos.activity.logBook

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.nshmura.recyclertablayout.RecyclerTabLayout
import kotlinx.android.synthetic.main.activity_log_book.*
import kotlinx.android.synthetic.main.activity_log_book.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.MoCoSSParentActivity
import upsi.edu.mocos.adapter.miscadapter.LogBookPagerAdapter
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyObject.LogBookMgr
import upsi.edu.mocos.model.PageNavigate

class LogBookActivity : MoCoSSParentActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: LogBookPagerAdapter
    private lateinit var lBookRTL: RecyclerTabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_book)

        val logBookMgr = LogBookMgr.weekInput()
        viewPager = logBookVP//findViewById(R.id.logBookVP)
        pagerAdapter = LogBookPagerAdapter(supportFragmentManager, logBookMgr)
        viewPager.adapter = pagerAdapter

        lBookRTL = logBookRTL//findViewById(R.id.logBookRTL)
        lBookRTL.setUpWithViewPager(viewPager)

        initPage(logBookActivity)

    }

    override fun onBackPressed() {
        goToPage(PageNavigate.ContentPage,this@LogBookActivity)
    }

    private fun initPage(view:View) {
        val logBookTB = view.logBookTB
        if (MiscSetting.BM) {
            logBookTB.title = getString(R.string.content8MY)
        }
        if (MiscSetting.BI) {
            logBookTB.title = getString(R.string.content8EN)
        }
        setSupportActionBar(logBookTB)
        supportActionBar
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.log_book_menu,menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        var score : MenuItem = menu!!.findItem(R.id.scoreLogBook)
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
            R.id.scoreLogBook -> {
                if (MiscSetting.user == "sl") {
                    val logBookIntent = Intent(this, LogBookScoreActivity::class.java)
                    startActivity(logBookIntent)
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

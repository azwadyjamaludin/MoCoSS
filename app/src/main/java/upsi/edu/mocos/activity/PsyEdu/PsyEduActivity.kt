package upsi.edu.mocos.activity.PsyEdu

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_psy_edu.*
import kotlinx.android.synthetic.main.activity_psy_edu.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.MoCoSSParentActivity
import upsi.edu.mocos.model.MiscSetting

class PsyEduActivity : MoCoSSParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_psy_edu)

        initPage(psyEduCL)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.psy_edu_menu,menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val score: MenuItem = menu!!.findItem(R.id.scorePsyEdu)
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
            R.id.scorePsyEdu -> {
                if (MiscSetting.user == "sl") {
                    val psyEduIntent = Intent(this, PsyEduScoreActivity::class.java)
                    startActivity(psyEduIntent)
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

    private fun initPage(view: View) {
        val psyEduTB = view.psyEduTB
        if (MiscSetting.BM) {
            psyEduTB.title = getString(R.string.content3MY)
        }
        if (MiscSetting.BI) {
            psyEduTB.title = getString(R.string.content3EN)
        }
        setSupportActionBar(psyEduTB)
        supportActionBar
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

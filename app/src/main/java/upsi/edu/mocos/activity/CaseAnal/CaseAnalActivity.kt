package upsi.edu.mocos.activity.CaseAnal

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import com.ipaulpro.afilechooser.utils.FileUtils
import kotlinx.android.synthetic.main.activity_case_anal.*
import kotlinx.android.synthetic.main.activity_case_anal.view.*
import upsi.edu.mocos.BuildConfig
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.MoCoSSParentActivity
import upsi.edu.mocos.model.DummyData
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.PageNavigate
import java.net.URI


class CaseAnalActivity : MoCoSSParentActivity() {
    val REQUEST_CHOOSER = 1234
    val dd: DummyData = DummyData("DummyData")
    private val FILE_SELECT_CODE = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case_anal)
        initPage(caseAnalCL)

        if (MiscSetting.user == "tc") {
            newText(caseAnalCL)
        }
        if (MiscSetting.user == "gc" || MiscSetting.user == "sl") {
            newText2(caseAnalCL)
        }
    }

    override fun onBackPressed() {
        goToPage(PageNavigate.ContentPage,this@CaseAnalActivity)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.case_anal_menu,menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val score: MenuItem = menu!!.findItem(R.id.scoreCaseAnal)
        //val share: MenuItem = menu!!.findItem(R.id.shareCase)
        if (MiscSetting.BM) {
            score.title = getString(R.string.scoreMY)
            //share.title = getString(R.string.shareMY)
        }
        if (MiscSetting.BI) {
            score.title = getString(R.string.scoreEN)
            //share.title = getString(R.string.shareEN)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.scoreCaseAnal -> {
                if (MiscSetting.user == "sl") {
                    val caseAnalIntent = Intent(this, CaseAnalScoreActivity::class.java)
                    startActivity(caseAnalIntent)
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
            /*R.id.shareCase -> {
                return true
            }*/
            else -> return super.onOptionsItemSelected(item)
        }
    }




    private fun initPage(view: View) {
        val caseAnalTB = view.caseAnalTB
        if (MiscSetting.BM) {
            caseAnalTB.title = getString(R.string.content9MY)
        }
        if (MiscSetting.BI) {
            caseAnalTB.title = getString(R.string.content9EN)
        }
        setSupportActionBar(caseAnalTB)
        supportActionBar
        ignoreExpose()
    }

    private fun ignoreExpose() {
        val builder:StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }

    private fun newText(view: View) {
        val caseAnalText = view.caseAnalText
        val caseAnal2Text = view.caseAnal2Text
        val caseAnalUpload = view.caseAnalUpload
        val caseAnalUpload2 = view.caseAnalUpload2

        if (MiscSetting.BM) {
            caseAnalText.text = "Analisis Kes 1"
            caseAnal2Text.text = "Analisis Kes 2"
            caseAnalUpload.text = getString(R.string.uploadFileMY)
            caseAnalUpload2.text = getString(R.string.uploadFileMY)
            caseAnalUpload.setOnClickListener({
                caseAnal1MY()
            })
            caseAnalUpload2.setOnClickListener({
                caseAnal2MY()
            })
        }
        if (MiscSetting.BI) {
            caseAnalText.text = "Case Analysis 1"
            caseAnal2Text.text = "Case Analysis 2"
            caseAnalUpload.text = getString(R.string.uploadFileEN)
            caseAnalUpload2.text = getString(R.string.uploadFileEN)
            caseAnalUpload.setOnClickListener({
                caseAnal1()
            })
            caseAnalUpload2.setOnClickListener({
                caseAnal2()
            })
        }
    }

    private fun newText2(view: View) {
        val caseAnalText = view.caseAnalText
        val caseAnal2Text = view.caseAnal2Text
        val caseAnalUpload = view.caseAnalUpload
        val caseAnalUpload2 = view.caseAnalUpload2
        val share2CB = view.share2CB
        share2CB.visibility = View.VISIBLE
        val share2CB2 = view.share2CB2
        share2CB2.visibility = View.VISIBLE

        if (MiscSetting.BM) {
            caseAnalText.text = "Analisis Kes 1"
            caseAnal2Text.text = "Analisis Kes 2"
            caseAnalUpload.text = getString(R.string.downloadFileMY)
            caseAnalUpload.setOnClickListener({
                case2Anal1(view)
            })
            caseAnalUpload2.text = getString(R.string.downloadFileMY)
            caseAnalUpload2.setOnClickListener({
                case2Anal2(view)
            })
            share2CB.text = getString(R.string.shareMY)
            share2CB.setOnCheckedChangeListener({ _: CompoundButton, isChecked ->
                if (share2CB.isChecked) {
                    val shareIntent = Intent(android.content.Intent.ACTION_SEND)
                    shareIntent.setDataAndType(Uri.fromFile(dd.writeCA1(this)), "application/pdf")
                    share2CB.isChecked = false
                    startActivity(Intent.createChooser(shareIntent, "Kongsi fail dengan"))
                }
            })
            share2CB2.text = getString(R.string.shareMY)
            share2CB2.setOnCheckedChangeListener({ _: CompoundButton, isChecked ->
                if (share2CB2.isChecked) {
                    val shareIntent = Intent(android.content.Intent.ACTION_SEND)
                    shareIntent.setDataAndType(Uri.fromFile(dd.writeCA2(this)), "application/pdf")
                    share2CB2.isChecked = false
                    startActivity(Intent.createChooser(shareIntent, "Kongsi fail dengan"))
                }
            })
        }
        if (MiscSetting.BI) {
            caseAnalText.text = "Case Analysis 1"
            caseAnal2Text.text = "Case Analysis 2"
            caseAnalUpload.text = getString(R.string.downloadFileEN)
            caseAnalUpload.setOnClickListener({
                case2Anal1(view)
            })
            caseAnalUpload2.text = getString(R.string.downloadFileEN)
            caseAnalUpload2.setOnClickListener({
                case2Anal2(view)
            })
            share2CB.text = getString(R.string.shareEN)
            share2CB.setOnCheckedChangeListener({ _: CompoundButton, isChecked ->
                if (share2CB.isChecked) {
                    val shareIntent = Intent(android.content.Intent.ACTION_SEND)
                    shareIntent.setDataAndType(Uri.fromFile(dd.writeCA1(this)), "application/pdf")
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    share2CB.isChecked = false
                    startActivity(Intent.createChooser(shareIntent, "Share file using"))
                }
            })
            share2CB2.text = getString(R.string.shareEN)
            share2CB2.setOnCheckedChangeListener({ _: CompoundButton, isChecked ->
                if (share2CB2.isChecked) {
                    val shareIntent = Intent(android.content.Intent.ACTION_SEND)
                    shareIntent.setDataAndType(Uri.fromFile(dd.writeCA2(this)), "application/pdf")
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    share2CB2.isChecked = false
                    startActivity(Intent.createChooser(shareIntent, "Share file using"))
                }
            })
        }
    }

    private fun caseAnal1() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE)
        } catch (ex: android.content.ActivityNotFoundException) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show()
        }

    }

    private fun caseAnal1MY() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Sila pilih fail untuk muat naik"),
                    FILE_SELECT_CODE)
        } catch (ex: android.content.ActivityNotFoundException) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Sila install 'File Manager'.",
                    Toast.LENGTH_SHORT).show()
        }

    }

    private fun caseAnal2() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE)
        } catch (ex: android.content.ActivityNotFoundException) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show()
        }
    }

    private fun caseAnal2MY() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Sila pilih fail untuk muat naik"),
                    FILE_SELECT_CODE)
        } catch (ex: android.content.ActivityNotFoundException) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Sila install 'File Manager'.",
                    Toast.LENGTH_SHORT).show()
        }
    }

    private fun case2Anal1(view: View) {
        val pdfViewIntent = Intent(view.context,CaseAnalPDFActivity::class.java)
        pdfViewIntent.putExtra("CA","CA1")
        startActivity(pdfViewIntent)
    }

    private fun case2Anal2(view: View) {
        val pdfViewIntent = Intent(view.context,CaseAnalPDFActivity::class.java)
        pdfViewIntent.putExtra("CA","CA2")
        startActivity(pdfViewIntent)
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

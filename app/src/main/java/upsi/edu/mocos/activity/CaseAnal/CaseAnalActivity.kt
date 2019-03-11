package upsi.edu.mocos.activity.CaseAnal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.ipaulpro.afilechooser.utils.FileUtils
import com.shockwave.pdfium.PdfDocument
import kotlinx.android.synthetic.main.activity_case_anal.*
import kotlinx.android.synthetic.main.activity_case_anal.view.*
import kotlinx.android.synthetic.main.activity_case_anal_2.*
import kotlinx.android.synthetic.main.activity_case_anal_2.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.MoCoSSParentActivity
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyObject.DummyMgr
import upsi.edu.mocos.model.PageNavigate


class CaseAnalActivity : MoCoSSParentActivity(), OnPageChangeListener, OnLoadCompleteListener {

    val dm:DummyMgr = DummyMgr
    //var numberList:ArrayList<NumberData> = NumberMgr.increaseCached()
    private val FILE_SELECT_CODE = 0
    var pageNumber: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (MiscSetting.user == "tc") {
            setContentView(R.layout.activity_case_anal)
            initPage(caseAnalCL)

            newText(caseAnalCL)
        }
        if (MiscSetting.user == "gc" || MiscSetting.user == "sl") {
            setContentView(R.layout.activity_case_anal_2)
            initPage2(caseAnal2CL)
            newText2(caseAnal2CL)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.case_anal_menu,menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val score: MenuItem = menu!!.findItem(R.id.scoreCaseAnal)
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
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            FILE_SELECT_CODE -> if (resultCode == Activity.RESULT_OK) {
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

    override fun onBackPressed() {
        goToPage(PageNavigate.ContentPage,this@CaseAnalActivity)
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page
    }

    override fun loadComplete(nbPages: Int) {
        var pdfView: PDFView = findViewById(R.id.caseAnalPDF)
        val meta = pdfView!!.getDocumentMeta()
        printBookmarksTree(pdfView!!.getTableOfContents(), "-")
    }

    fun printBookmarksTree(tree: List<PdfDocument.Bookmark>, sep: String) {
        for (b in tree) {
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), "$sep-")
            }
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
    }

    private fun initPage2(view: View) {
        val caseAnal2TB = view.caseAnal2TB
        if (MiscSetting.BM) {
            caseAnal2TB.title = getString(R.string.content9MY)
        }
        if (MiscSetting.BI) {
            caseAnal2TB.title = getString(R.string.content9EN)
        }
        setSupportActionBar(caseAnal2TB)
        supportActionBar
    }

    private fun newText(view: View) {
        val caseAnalText = view.caseAnalText
        val caseAnal2Text = view.caseAnal2Text
        val caseAnalUpload = view.caseAnalUpload
        val caseAnalUpload2 = view.caseAnalUpload2
        val shareButton = view.shareButton
        shareButton.visibility = View.INVISIBLE
        val shareButton2 = view.shareButton2
        shareButton2.visibility = View.INVISIBLE
        //var mShareActionProvider = ShareActionProvider(view.context)

        if (MiscSetting.BM) {
            caseAnalText.text = "Analisis Kes 1"
            caseAnal2Text.text = "Analisis Kes 2"
            caseAnalUpload.text = getString(R.string.uploadFileMY)
            caseAnalUpload2.text = getString(R.string.uploadFileMY)
            /*shareButton.text = "Kongsi"
            shareButton2.text = "Kongsi"*/
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
            /*shareButton.text = "Share"
            shareButton2.text = "Share"*/
            caseAnalUpload.setOnClickListener({
                caseAnal1()
            })
            caseAnalUpload2.setOnClickListener({
                caseAnal2()
            })
        }

        /*shareButton.setOnClickListener({
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "application/pdf"
            shareIntent.putExtra(Intent.EXTRA_STREAM, dm.writeCA1(this))
            //mShareActionProvider.setShareIntent(shareIntent)
            startActivity(Intent.createChooser(shareIntent, "Share file using"))
        })
        shareButton2.setOnClickListener({
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "application/pdf"
            shareIntent.putExtra(Intent.EXTRA_STREAM, dm.writeCA2(this))
            //mShareActionProvider.setShareIntent(shareIntent)
            startActivity(Intent.createChooser(shareIntent, "Share file using"))
        })*/
    }

    private fun newText2(view: View) {
        val caseAnal2Text1 = view.caseAnal2Text1
        val caseAnal2Text2 = view.caseAnal2Text2
        val caseAnalDownload = view.caseAnalDownload
        val caseAnalDownload2 = view.caseAnalDownload2
        val share2Button = view.share2Button
        share2Button.visibility = View.INVISIBLE
        val share2Button2 = view.share2Button2
        share2Button2.visibility = View.INVISIBLE
        //var mShareActionProvider = ShareActionProvider(view.context)

        if (MiscSetting.BM) {
            caseAnal2Text1.text = "Analisis Kes 1"
            caseAnal2Text2.text = "Analisis Kes 2"
            caseAnalDownload.text = getString(R.string.downloadFileMY)
            caseAnalDownload2.text = getString(R.string.downloadFileMY)
            //share2Button.text = "Kongsi"
            //share2Button2.text = "Kongsi"

        }
        if (MiscSetting.BI) {
            caseAnal2Text1.text = "Case Analysis 1"
            caseAnal2Text2.text = "Case Analysis 2"
            caseAnalDownload.text = getString(R.string.downloadFileEN)
            caseAnalDownload2.text = getString(R.string.downloadFileEN)
            //share2Button.text = "Share"
            //share2Button2.text = "Share"
        }

        caseAnalDownload.setOnClickListener({
            case2Anal1(view)
        })
        caseAnalDownload2.setOnClickListener({
            case2Anal2(view)
        })

        /*share2Button.setOnClickListener({
            val shareIntent = Intent(android.content.Intent.ACTION_SEND)
            shareIntent.type = "application/pdf"
            shareIntent.putExtra(android.content.Intent.EXTRA_STREAM, dm.writeCA1(this))
            mShareActionProvider.setShareIntent(shareIntent)
            startActivity(Intent.createChooser(shareIntent, "Share file using"))
        })
        share2Button2.setOnClickListener({
            val shareIntent = Intent(android.content.Intent.ACTION_SEND)
            shareIntent.type = "application/pdf"
            shareIntent.putExtra(android.content.Intent.EXTRA_STREAM, dm.writeCA2(this))
            mShareActionProvider.setShareIntent(shareIntent)
            startActivity(Intent.createChooser(shareIntent, "Share file using"))
        })*/
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

        var pdfView = view.caseAnalPDF
        pdfView.visibility = View.VISIBLE
        pdfView.fromAsset("CaseAnalysis1.pdf")
                .defaultPage(this!!.pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(DefaultScrollHandle(this))
                .load()
    }

    private fun case2Anal2(view: View) {

        var pdfView = view.caseAnalPDF
        pdfView.visibility = View.VISIBLE
        pdfView.fromAsset("CaseAnalysis2.pdf")
                .defaultPage(this!!.pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(DefaultScrollHandle(this))
                .load()

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

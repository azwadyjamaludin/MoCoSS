package upsi.edu.mocos.activity.Reflect

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
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
import kotlinx.android.synthetic.main.activity_reflect.*
import kotlinx.android.synthetic.main.activity_reflect.view.*
import org.jetbrains.anko.doAsync
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.MoCoSSParentActivity
import upsi.edu.mocos.adapter.listadapter.ReflectListAdapter
import upsi.edu.mocos.adapter.listadapter.ReflectListAdapter2
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.NumberData
import upsi.edu.mocos.model.MyObject.NumberMgr

class ReflectActivity : MoCoSSParentActivity(), OnPageChangeListener, OnLoadCompleteListener {

    private lateinit var layoutManager: LinearLayoutManager
    var numbering:ArrayList<NumberData> = arrayListOf()
    val FILE_SELECT_CODE = 0
    var pageNumber: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reflect)

        layoutManager = LinearLayoutManager(this)
        reflectRV.layoutManager = layoutManager

        initPage(reflectCL)
        attachAdapter(reflectCL)
        newText(reflectCL)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.reflect_menu,menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        var score : MenuItem = menu!!.findItem(R.id.scoreReflect)
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
            R.id.scoreReflect -> {
                if (MiscSetting.user == "sl") {
                    val reflectIntent = Intent(this, ReflectScoreActivity::class.java)
                    startActivity(reflectIntent)
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

    override fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page
    }

    override fun loadComplete(nbPages: Int) {
        var pdfView: PDFView = findViewById(R.id.reflectPDF)
        val meta = pdfView.getDocumentMeta()
        printBookmarksTree(pdfView.getTableOfContents(), "-")
    }

    fun printBookmarksTree(tree: List<PdfDocument.Bookmark>, sep: String) {
        for (b in tree) {
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), "$sep-")
            }
        }
    }

    private fun initPage(view: View) {
        val reflectTB = view.reflectTB

        if (MiscSetting.BM) {
            reflectTB.title = getString(R.string.content11MY)
        }
        if (MiscSetting.BI) {
            reflectTB.title = getString(R.string.content11EN)
        }
        setSupportActionBar(reflectTB)
        supportActionBar
    }


    private fun attachAdapter(view: View) {
        val origin = this
        doAsync {
            if (MiscSetting.user == "tc") {
                numbering = NumberMgr.numInputReflect()

                val listadapter = ReflectListAdapter(numbering,origin)
                val reflectRV = view.reflectRV

                reflectRV.adapter = listadapter
            }
            if (MiscSetting.user == "gc"||MiscSetting.user == "sl") {
                numbering = NumberMgr.numInputReflect()

                val listadapter = ReflectListAdapter2(numbering,origin)
                val reflectRV = view.reflectRV

                reflectRV.adapter = listadapter
            }
        }
    }

    private fun newText (view: View) {
        val reflectWeek = view.reflectWeek
        if (MiscSetting.BM) {
            reflectWeek.text = "Minggu"
        }
        if (MiscSetting.BI) {
            reflectWeek.text = "Week"
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

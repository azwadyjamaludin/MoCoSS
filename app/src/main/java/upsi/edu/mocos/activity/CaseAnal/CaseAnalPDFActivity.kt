package upsi.edu.mocos.activity.CaseAnal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.ipaulpro.afilechooser.utils.FileUtils
import com.shockwave.pdfium.PdfDocument
import kotlinx.android.synthetic.main.activity_case_anal_pdf.*
import kotlinx.android.synthetic.main.activity_case_anal_pdf.view.*
import upsi.edu.mocos.R

import upsi.edu.mocos.activity.MoCoSSParentActivity

class CaseAnalPDFActivity : MoCoSSParentActivity(), OnPageChangeListener, OnLoadCompleteListener {

    var pageNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case_anal_pdf)
        getIntentData(caseAnalPDFCL)
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page
        setTitle("share file")
    }

    override fun loadComplete(nbPages: Int) {
        val meta: PdfDocument.Meta = caseAnalPDF.getDocumentMeta()
        printBookmarksTree(caseAnalPDF.getTableOfContents(), "-")
    }

    fun printBookmarksTree(tree: List<PdfDocument.Bookmark>, sep: String) {
        for (b in tree) {
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), "$sep-")
            }
        }
    }

    fun getIntentData(view: View) {
        val stringData:String = intent.getStringExtra("CA")
        if (stringData.equals("CA1")) {
            CA1PDF(view)
        }
        if (stringData.equals("CA2")){
            CA2PDF(view)
        }

    }

    private fun CA1PDF(view: View) {

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

    private fun CA2PDF(view: View) {

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
}

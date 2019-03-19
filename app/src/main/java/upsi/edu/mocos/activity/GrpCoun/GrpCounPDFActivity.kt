package upsi.edu.mocos.activity.GrpCoun

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
import kotlinx.android.synthetic.main.activity_grp_coun_pdf.*
import kotlinx.android.synthetic.main.activity_grp_coun_pdf.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.MoCoSSParentActivity

class GrpCounPDFActivity : MoCoSSParentActivity(), OnPageChangeListener, OnLoadCompleteListener {
    var pageNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grp_coun_pdf)
        getIntentData(grpCounPDFCL)
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page
        setTitle("share file")
    }

    override fun loadComplete(nbPages: Int) {
        val meta: PdfDocument.Meta = grpCounPDF.documentMeta
        printBookmarksTree(grpCounPDF.getTableOfContents(), "-")
    }

    fun printBookmarksTree(tree: List<PdfDocument.Bookmark>, sep: String) {
        for (b in tree) {
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), "$sep-")
            }
        }
    }

    fun getIntentData(view: View) {
        val stringData:String = intent.getStringExtra("downGrp")
        if (stringData.equals("downMY")) {
            pdfMY(view)
        }
        if (stringData.equals("downEN")) {
            pdfEN(view)
        }

    }

    private fun pdfMY(view: View){
        var pdfView = view.grpCounPDF
        pdfView.visibility = View.VISIBLE
        pdfView.fromAsset("Reflect.pdf")
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(DefaultScrollHandle(this))
                .load()
    }

    private fun pdfEN(view: View) {
        var pdfView = view.grpCounPDF
        pdfView.visibility = View.VISIBLE
        pdfView.fromAsset("Reflect.pdf")
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(DefaultScrollHandle(this))
                .load()
    }
}

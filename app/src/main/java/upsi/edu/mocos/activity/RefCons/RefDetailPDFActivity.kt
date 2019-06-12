package upsi.edu.mocos.activity.RefCons

import android.os.Bundle
import android.view.View
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.shockwave.pdfium.PdfDocument
import kotlinx.android.synthetic.main.activity_ref_detail_pdf.*
import kotlinx.android.synthetic.main.activity_ref_detail_pdf.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.MoCoSSParentActivity

class RefDetailPDFActivity : MoCoSSParentActivity(), OnPageChangeListener, OnLoadCompleteListener {
    var pageNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ref_detail_pdf)
        getIntentData(refDetailPDFCL)
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page
        setTitle("share file")
    }

    override fun loadComplete(nbPages: Int) {
        val meta: PdfDocument.Meta = refDetailPDF.documentMeta
        printBookmarksTree(refDetailPDF.tableOfContents, "-")
    }

    fun printBookmarksTree(tree: List<PdfDocument.Bookmark>, sep: String) {
        for (b in tree) {
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), "$sep-")
            }
        }
    }

    fun getIntentData(view: View) {
        val stringData:String = intent.getStringExtra("downRefDet")
        if (stringData.equals("downMY")) {
            pdfMY(view)
        }
        if (stringData.equals("downEN")) {
            pdfEN(view)
        }

    }

    private fun pdfMY(view: View) {
        var pdfView = view.refDetailPDF
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
        var pdfView = view.refDetailPDF
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

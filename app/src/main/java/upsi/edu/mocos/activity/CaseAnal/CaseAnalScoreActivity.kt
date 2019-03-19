package upsi.edu.mocos.activity.CaseAnal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.ipaulpro.afilechooser.utils.FileUtils
import com.shockwave.pdfium.PdfDocument
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.MoCoSSParentActivity
import upsi.edu.mocos.model.PageNavigate

class CaseAnalScoreActivity : MoCoSSParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case_anal_score)
    }


}

package upsi.edu.mocos.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.activity_content.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MiscSetting

class ContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        initPage(services)
    }

    private fun initPage(view:View) {

        if (MiscSetting.BM) {
            view.intservtext.text = "Perkhidmatan Bersemuka"
            view.indCounBtn.text = "Kaunseling Individu"
            view.grpCounBtn.text = "Kaunseling Kelompok"
            view.counTaskText.text = "Tugas Professional Kaunselor"
            view.guidpsyBtn.text = "Aktiviti Bimbingan dan Psiko-Pendidikan"
            view.progExBtn.text = "Pelaksanaan Program"
            view.psyTestBtn.text = "Ujian Psikologi"
            view.profDevBtn.text = "Perkembangan Profesional"
            view.refConsBtn.text = "Konsultasi dan Rujukan"
            view.adminMgtText.text = "Pengurusan Pentadbiran"
            view.logBookBtn.text = "Buku Log"
            view.caseAnlBtn.text = "Analisis Kes"
            view.endRptBtn.text = "Laporan Akhir"
            view.reflexiBtn.text = "Refleksi"
            view.recFileBtn.text = "Fail dan Rekod"
        }
        if (MiscSetting.BI) {
            view.intservtext.text = "Face to Face"
            view.indCounBtn.text = "Individual Counseling"
            view.grpCounBtn.text = "Group Counseling"
            view.counTaskText.text = "Counselor Professional Task "
            view.guidpsyBtn.text = "Guiding and Psyco-Education"
            view.progExBtn.text = "Programme Execution"
            view.psyTestBtn.text = "Psycology Test"
            view.profDevBtn.text = "Professional Development"
            view.refConsBtn.text = "Refers and Consultation"
            view.adminMgtText.text = "Administration Management"
            view.logBookBtn.text = "Log Book"
            view.caseAnlBtn.text = "Case Analysis"
            view.endRptBtn.text = "End Report"
            view.reflexiBtn.text = "Reflexi"
            view.recFileBtn.text = "Record and Filing"
        }
        enterIndCoun(indCounBtn)
        enterGrpCoun(grpCounBtn)
        enterLogBook(logBookBtn)
    }

    private fun enterIndCoun(indCounBtn: Button) {
        indCounBtn.setOnClickListener {
            val indCounIntent = Intent(this,IndCounActivity::class.java)
            startActivity(indCounIntent)
        }
    }

    private fun enterGrpCoun(grpCounBtn: Button) {
        grpCounBtn.setOnClickListener {
            val grpCounIntent = Intent(this,GrpCounActivity::class.java)
            startActivity(grpCounIntent)
        }
    }

    private fun enterLogBook(logBookBtn: Button) {
        logBookBtn.setOnClickListener {
            val logBookIntent = Intent(this,LogBookActivity::class.java)
            startActivity(logBookIntent)
        }
    }
}

package upsi.edu.mocos.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MiscSetting

class ContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        initPage()
    }

    private fun initPage() {
        val intServText = findViewById<TextView>(R.id.intservtext)
        val indCounBtn = findViewById<Button>(R.id.indCounBtn)
        val grpCounBtn = findViewById<Button>(R.id.grpCounBtn)
        val counTaskText = findViewById<TextView>(R.id.counTaskText)
        val guidpsyBtn = findViewById<Button>(R.id.guidpsyBtn)
        val progExBtn = findViewById<Button>(R.id.progExBtn)
        val psyTestBtn = findViewById<Button>(R.id.psyTestBtn)
        val profDevBtn = findViewById<Button>(R.id.profDevBtn)
        val refConsBtn = findViewById<Button>(R.id.refConsBtn)
        val adminMgtText = findViewById<TextView>(R.id.adminMgtText)
        val logBookBtn = findViewById<Button>(R.id.logBookBtn)
        val caseAnlBtn = findViewById<Button>(R.id.caseAnlBtn)
        val endRptBtn = findViewById<Button>(R.id.endRptBtn)
        val reflexiBtn = findViewById<Button>(R.id.reflexiBtn)
        val recFileBtn = findViewById<Button>(R.id.recFileBtn)

        if (MiscSetting.BM) {
            intServText.text = "Perkhidmatan Bersemuka"
            indCounBtn.text = "Kaunseling Individu"
            grpCounBtn.text = "Kaunseling Kelompok"
            counTaskText.text = "Tugas Professional Kaunselor"
            guidpsyBtn.text = "Aktiviti Bimbingan dan Psiko-Pendidikan"
            progExBtn.text = "Pelaksanaan Program"
            psyTestBtn.text = "Ujian Psikologi"
            profDevBtn.text = "Perkembangan Profesional"
            refConsBtn.text = "Konsultasi dan Rujukan"
            adminMgtText.text = "Pengurusan Pentadbiran"
            logBookBtn.text = "Buku Log"
            caseAnlBtn.text = "Analisis Kes"
            endRptBtn.text = "Laporan Akhir"
            reflexiBtn.text = "Refleksi"
            recFileBtn.text = "Fail dan Rekod"
        }
        if (MiscSetting.BI) {
            intServText.text = "Face to Face"
            indCounBtn.text = "Individual Counseling"
            grpCounBtn.text = "Group Counseling"
            counTaskText.text = "Counselor Professional Task "
            guidpsyBtn.text = "Guiding and Psyco-Education"
            progExBtn.text = "Programme Execution"
            psyTestBtn.text = "Psycology Test"
            profDevBtn.text = "Professional Development"
            refConsBtn.text = "Refers and Consultation"
            adminMgtText.text = "Administration Management"
            logBookBtn.text = "Log Book"
            caseAnlBtn.text = "Case Analysis"
            endRptBtn.text = "End Report"
            reflexiBtn.text = "Reflexi"
            recFileBtn.text = "Record and Filing"
        }
        enterIndCoun(indCounBtn)
        enterGrpCoun(grpCounBtn)
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
}

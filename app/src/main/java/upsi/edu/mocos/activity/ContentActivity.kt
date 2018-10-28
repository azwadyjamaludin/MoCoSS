package upsi.edu.mocos.activity


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.activity_content.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.DrawerData

class ContentActivity : MocoSSParentActivity() {

    override fun createActivity(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_content)
        initPage()
    }

    private fun initPage() {
        var contentArray = arrayListOf<DrawerData>()
        if (MiscSetting.BM) {
            contentArray.add(DrawerData("Kaunseling Individu"))
            contentArray.add(DrawerData("Kaunseling Kelompok"))
            contentArray.add(DrawerData("Aktiviti Bimbingan dan Psiko-Pendidikan"))
            contentArray.add(DrawerData("Ujian Psikologi"))
            contentArray.add(DrawerData("Perkembangan Profesional"))
            contentArray.add(DrawerData("Konsultasi dan Rujukan"))
            contentArray.add(DrawerData("Pengurusan Pentadbiran"))
            contentArray.add(DrawerData("Buku Log"))
            contentArray.add(DrawerData("Analisis Kes"))
            contentArray.add(DrawerData("Laporan Akhir"))
            contentArray.add(DrawerData("Refleksi"))
            contentArray.add(DrawerData("Fail dan Rekod"))
        }
        if (MiscSetting.BI) {
            contentArray.add(DrawerData("Individual Counseling"))
            contentArray.add(DrawerData("Group Counseling"))
            contentArray.add(DrawerData("Guiding and Psyco-Education"))
            contentArray.add(DrawerData("Programme Execution"))
            contentArray.add(DrawerData("Psycology Test"))
            contentArray.add(DrawerData("Refers and Consultation"))
            contentArray.add(DrawerData("Administration Management"))
            contentArray.add(DrawerData("Log Book"))
            contentArray.add(DrawerData("Case Analysis"))
            contentArray.add(DrawerData("End Report"))
            contentArray.add(DrawerData( "Reflexi"))
            contentArray.add(DrawerData("Record and Filing"))
        }

        val drawerAdapter = ArrayAdapter(this,R.layout.custom_button_adapter,contentArray)
        val leftDrawer:ListView = findViewById(R.id.leftDrawer)
        leftDrawer.adapter = drawerAdapter
        leftDrawer.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val textItem = leftDrawer.getItemAtPosition(position) as String
                    if (textItem.equals("Kaunseling Individu") || textItem.equals("Individual Counseling")) {
                        enterIndCoun()
                    }
                if (textItem.equals("Kaunseling Kelompok") || textItem.equals("Group Counseling")) {
                    enterGrpCoun()
                }
                if (textItem.equals("Buku Log") || textItem.equals("Log Book")) {
                    enterGrpCoun()
                }
            }

        }
    }

    private fun enterIndCoun() {
        indCounBtn.setOnClickListener {
            val indCounIntent = Intent(this,IndCounActivity::class.java)
            startActivity(indCounIntent)
        }
    }

    private fun enterGrpCoun() {
        grpCounBtn.setOnClickListener {
            val grpCounIntent = Intent(this,GrpCounActivity::class.java)
            startActivity(grpCounIntent)
        }
    }

    private fun enterLogBook(logBookBtn: Button) {
        logBookBtn.setOnClickListener {
            val logBookIntent = Intent(this,LogBookActivity::class.java)
            if (MiscSetting.user == "tc") {
                startActivity(logBookIntent)
            }else {
                if (MiscSetting.BM) {
                    alertBM()
                }
                if (MiscSetting.BI) {
                    alertBI()
                }
             }
        }
    }

    private fun alertBM() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
                .setIcon(R.drawable.mocoss2)
                .setMessage("Anda tiada akses")
                .setCancelable(false)
                .setNegativeButton("Batal") {
                    dialog, which -> dialog.dismiss()
                }

        val alert = dialogBuilder.create()
        alert.setTitle("MoCoSS")
        alert.show()
    }

    private fun alertBI() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
                .setIcon(R.drawable.mocoss2)
                .setMessage("No access")
                .setCancelable(false)
                .setNegativeButton("Cancel") {
                    dialog, which ->  dialog.dismiss()
                }
        val alert = dialogBuilder.create()
        alert.setTitle("MoCoSS")
        alert.show()
    }
}

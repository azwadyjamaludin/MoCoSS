package upsi.edu.mocos.activity


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_drawer_content.*
import upsi.edu.mocos.R
import upsi.edu.mocos.adapter.pageradapter.DrawerCustomAdapter
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.DrawerData

class ContentActivity : MocoSSParentActivity()  {

    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_content)
        setSupportActionBar(adcToolbar as Toolbar)
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

        var drawerAdapter = DrawerCustomAdapter(this, contentArray)

        drawerToggle = ActionBarDrawerToggle(this,drawerLayout, adcToolbar as Toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(drawerToggle)
        drawerLayout.openDrawer(Gravity.START)
        drawerToggle.syncState()

        val drawerList:ListView = findViewById(R.id.drawerList)
        drawerList.adapter = drawerAdapter

        drawerList.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                var textItem = contentArray[position].buttonText

                if (textItem.equals("Kaunseling Individu") || textItem.equals("Individual Counseling")) {
                    //drawerLayout.closeDrawer(Gravity.START)
                    enterIndCoun()
                } else if (textItem.equals("Kaunseling Kelompok") || textItem.equals("Group Counseling")) {
                    //drawerLayout.closeDrawer(Gravity.START)
                    enterGrpCoun()
                } else if (textItem.equals("Buku Log") || textItem.equals("Log Book")) {
                    //drawerLayout.closeDrawer(Gravity.START)
                    enterLogBook()
                }
                else {
                    if (MiscSetting.BM) {
                        alertOtherBM()
                    }
                    if (MiscSetting.BI) {
                        alertOtherBI()
                    }
                }
            }

        }
    }

    private fun enterIndCoun() {
            val indCounIntent = Intent(this,IndCounActivity::class.java)
            startActivity(indCounIntent)
            }

    private fun enterGrpCoun() {
            val grpCounIntent = Intent(this,GrpCounActivity::class.java)
            startActivity(grpCounIntent)
            }


    private fun enterLogBook() {
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

    private fun alertBM() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
                .setIcon(R.drawable.mocoss_img)
                .setMessage("Tiada akses")
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
                .setIcon(R.drawable.mocoss_img)
                .setMessage("No access")
                .setCancelable(false)
                .setNegativeButton("Cancel") {
                    dialog, which ->  dialog.dismiss()
                }
        val alert = dialogBuilder.create()
        alert.setTitle("MoCoSS")
        alert.show()
    }

    private fun alertOtherBM() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
                .setIcon(R.drawable.mocoss_img)
                .setMessage("Dalam pembangunan")
                .setCancelable(false)
                .setNegativeButton("KELUAR") {
                    dialog, which -> dialog.dismiss()
                }

        val alert = dialogBuilder.create()
        alert.setTitle("MoCoSS")
        alert.show()
    }

    private fun alertOtherBI() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
                .setIcon(R.drawable.mocoss_img)
                .setMessage("Under construction")
                .setCancelable(false)
                .setNegativeButton("EXIT") {
                    dialog, which ->  dialog.dismiss()
                }
        val alert = dialogBuilder.create()
        alert.setTitle("MoCoSS")
        alert.show()
    }
}

package upsi.edu.mocos.activity


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_drawer_content.*
import upsi.edu.mocos.R
import upsi.edu.mocos.activity.AdmMgt.AdmMgtActivity
import upsi.edu.mocos.activity.CaseAnal.CaseAnalActivity
import upsi.edu.mocos.activity.GrpCoun.GrpCounActivity
import upsi.edu.mocos.activity.IndCoun.IndCounActivity
import upsi.edu.mocos.activity.ProfDev.ProfDevActivity
import upsi.edu.mocos.activity.PsyEdu.PsyEduActivity
import upsi.edu.mocos.activity.PsyTest.PsyTestActivity
import upsi.edu.mocos.activity.RefCons.RefConsActivity
import upsi.edu.mocos.activity.Reflect.ReflectActivity
import upsi.edu.mocos.activity.logBook.LogBookActivity
import upsi.edu.mocos.adapter.miscadapter.DrawerCustomAdapter
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.DrawerData
import upsi.edu.mocos.model.PageNavigate

class ContentActivity : MoCoSSParentActivity()  {

    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_content)
        setSupportActionBar(adcToolbar as Toolbar)
        initPage()
    }

    override fun onBackPressed() {

    }

    private fun initPage() {
        var contentArray = arrayListOf<DrawerData>()
        if (MiscSetting.BM) {
            contentArray.add(DrawerData(getString(R.string.content1MY)))
            contentArray.add(DrawerData(getString(R.string.content2MY)))
            contentArray.add(DrawerData(getString(R.string.content3MY)))
            contentArray.add(DrawerData(getString(R.string.content4MY)))
            contentArray.add(DrawerData(getString(R.string.content5MY)))
            contentArray.add(DrawerData(getString(R.string.content6MY)))
            contentArray.add(DrawerData(getString(R.string.content7MY)))
            contentArray.add(DrawerData(getString(R.string.content8MY)))
            contentArray.add(DrawerData(getString(R.string.content9MY)))
            //contentArray.add(DrawerData(getString(R.string.content10MY)))
            contentArray.add(DrawerData(getString(R.string.content11MY)))
            contentArray.add(DrawerData(getString(R.string.content14MY)))
            //contentArray.add(DrawerData(getString(R.string.content12MY)))
            contentArray.add(DrawerData(getString(R.string.content13MY)))
        }
        if (MiscSetting.BI) {
            contentArray.add(DrawerData(getString(R.string.content1EN)))
            contentArray.add(DrawerData(getString(R.string.content2EN)))
            contentArray.add(DrawerData(getString(R.string.content3EN)))
            contentArray.add(DrawerData(getString(R.string.content4EN)))
            contentArray.add(DrawerData(getString(R.string.content5EN)))
            contentArray.add(DrawerData(getString(R.string.content6EN)))
            contentArray.add(DrawerData(getString(R.string.content7EN)))
            contentArray.add(DrawerData(getString(R.string.content8EN)))
            contentArray.add(DrawerData(getString(R.string.content9EN)))
            //contentArray.add(DrawerData("End Report"))
            contentArray.add(DrawerData( getString(R.string.content11EN)))
            contentArray.add(DrawerData(getString(R.string.content14EN)))
            //contentArray.add(DrawerData("Record and Filing"))
            contentArray.add(DrawerData(getString(R.string.content13EN)))
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

                if (textItem.equals(getString(R.string.content1MY)) || textItem.equals(getString(R.string.content1EN))) {
                    enterIndCoun()
                } else if (textItem.equals(getString(R.string.content2MY)) || textItem.equals(getString(R.string.content2EN))) {
                    enterGrpCoun()
                } else if (textItem.equals(getString(R.string.content3MY)) || textItem.equals(getString(R.string.content3EN))) {
                    enterPsyEdu()
                } else if (textItem.equals(getString(R.string.content4MY)) || textItem.equals(getString(R.string.content4EN))) {
                    enterPsyTest()
                } else if (textItem.equals(getString(R.string.content5MY)) || (textItem.equals(getString(R.string.content5EN)))) {
                    enterProfDev()
                } else if (textItem.equals(getString(R.string.content6MY)) || (textItem.equals(getString(R.string.content6EN)))) {
                    enterRefCons()
                } else if (textItem.equals(getString(R.string.content7MY)) || (textItem.equals(getString(R.string.content7EN)))) {
                    enterAdmMgt()
                } else if (textItem.equals(getString(R.string.content8MY)) || textItem.equals(getString(R.string.content8EN))) {
                    enterLogBook()
                } else if (textItem.equals(getString(R.string.content9MY)) || (textItem.equals(getString(R.string.content9EN)))) {
                    enterCaseAnal()
                } else if (textItem.equals(getString(R.string.content11MY)) || (textItem.equals(getString(R.string.content11EN)))) {
                    enterReflect()
                } else if (textItem.equals(getString(R.string.content14MY)) || (textItem.equals(getString(R.string.content14EN)))) {
                    enterFullMark()
                }

                else if (textItem.equals(getString(R.string.content13MY))) {
                    alertLogOutBM()
                } else if (textItem.equals(getString(R.string.content13EN))) {
                    alertLogOutBI()
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
            val indCounIntent = Intent(this, IndCounActivity::class.java)
            startActivity(indCounIntent)
            }

    private fun enterGrpCoun() {
            val grpCounIntent = Intent(this, GrpCounActivity::class.java)
            startActivity(grpCounIntent)
            }

    private fun enterPsyEdu() {
            val psyEduIntent = Intent(this,PsyEduActivity::class.java)
            startActivity(psyEduIntent)
    }

    private fun enterPsyTest() {
            val psyTestIntent = Intent(this,PsyTestActivity::class.java)
            startActivity(psyTestIntent)
    }

    private fun enterProfDev() {
            val profDevIntent = Intent(this, ProfDevActivity::class.java)
            startActivity(profDevIntent)
    }

    private fun enterRefCons() {
            val refConsIntent = Intent(this,RefConsActivity::class.java)
            startActivity(refConsIntent)
    }

    private fun enterAdmMgt() {
            val admMgtIntent = Intent(this,AdmMgtActivity::class.java)
            startActivity(admMgtIntent)
    }

    private fun enterLogBook() {
            val logBookIntent = Intent(this, LogBookActivity::class.java)
                startActivity(logBookIntent)
    }

    private fun enterCaseAnal() {
            val caseAnalIntent = Intent(this,CaseAnalActivity::class.java)
            startActivity(caseAnalIntent)
    }

    private fun enterReflect() {
            val reflectIntent = Intent(this,ReflectActivity::class.java)
            startActivity(reflectIntent)
    }

    private fun enterFullMark() {
            val fullMarkIntent = Intent(this,FullMarkActivity::class.java)
            startActivity(fullMarkIntent)
    }

    private fun alertOtherBM() {
        val dialogBuilder = AlertDialog.Builder(this)
        val dismissDialog = LayoutInflater.from(this).inflate(R.layout.dialog_no_input,null)
        val wrCrText = dismissDialog.findViewById<TextView>(R.id.wrongCredText)
        val dismissBtn = dismissDialog.findViewById<Button>(R.id.dismissButton)

        dialogBuilder.setView(dismissDialog)
        val alert = dialogBuilder.create()
                wrCrText.setText("Dalam pembangunan")
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
                wrCrText.setText("Under construction")
                dismissBtn.setText("Dismiss")
                dismissBtn.setOnClickListener({
                    alert.dismiss()
                })
        alert.show()
    }

    private fun alertLogOutBM() {
        val dialogBuilder = AlertDialog.Builder(this)
        val dismissDialog = LayoutInflater.from(this).inflate(R.layout.dialog_no_input,null)
        val wrCrText = dismissDialog.findViewById<TextView>(R.id.wrongCredText)
        val dismissBtn = dismissDialog.findViewById<Button>(R.id.dismissButton)

        dialogBuilder.setView(dismissDialog)
        val alert = dialogBuilder.create()
        wrCrText.setText("Log keluar?")
        dismissBtn.setText("Ya")
        dismissBtn.setOnClickListener({
            alert.dismiss()
            goToPage(PageNavigate.LoginPage,this@ContentActivity)
        })
        alert.show()
    }

    private fun alertLogOutBI() {
        val dialogBuilder = AlertDialog.Builder(this)
        val dismissDialog = LayoutInflater.from(this).inflate(R.layout.dialog_no_input,null)
        val wrCrText = dismissDialog.findViewById<TextView>(R.id.wrongCredText)
        val dismissBtn = dismissDialog.findViewById<Button>(R.id.dismissButton)

        dialogBuilder.setView(dismissDialog)
        val alert = dialogBuilder.create()
        wrCrText.setText("Log out?")
        dismissBtn.setText("Yes")
        dismissBtn.setOnClickListener({
            alert.dismiss()
            goToPage(PageNavigate.LoginPage,this@ContentActivity)
        })
        alert.show()
    }
}

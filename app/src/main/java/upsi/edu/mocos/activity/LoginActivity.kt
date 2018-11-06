package upsi.edu.mocos.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.custom_button_layout.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.R.layout.activity_login
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.PageNavigate
import upsi.edu.mocos.ui_component.CustomButton

class LoginActivity : MocoSSParentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_login)
        initPage(loginActivity)
    }

    override fun onBackPressed() {
        goToPage(PageNavigate.TabPage,this)
    }

    private fun initPage(view: View) {
        val slUser = view.slUser
        val gcUser = view.gcUser
        val tcUser = view.tcUser

        if (MiscSetting.BM) {
            slUser.customText.text = "Pensyarah Penyelia"
            gcUser.customText.text = "Kaunselor Pembimbing"
            tcUser.customText.text = "Kaunselor Pelatih"
        }
        if (MiscSetting.BI) {
            slUser.customText.text = "Supervising Lecturer"
            gcUser.customText.text = "Guiding Counselor"
            tcUser.customText.text = "Trainer Counselor"
        }

        enterSL(slUser)
        enterGC(gcUser)
        enterTC(tcUser)
    }

    private fun enterSL(slUser: CustomButton) {
        slUser.customText.setOnClickListener() {
            val slIntent = Intent(this,ContentActivity::class.java)
            MiscSetting.user = "sl"
            startActivity(slIntent)
        }
    }

    private fun enterGC(gcUser: CustomButton) {
        gcUser.customText.setOnClickListener {
            val gcIntent = Intent(this,ContentActivity::class.java)
            MiscSetting.user = "gc"
            startActivity(gcIntent)
        }
    }

    fun enterTC(tcUser: CustomButton) {
            tcUser.customText.setOnClickListener {
            val tcIntent = Intent(this,ContentActivity::class.java)
            MiscSetting.user = "tc"
            startActivity(tcIntent)
        }
    }
}

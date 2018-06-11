package upsi.edu.mocos.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.R.layout.activity_login
import upsi.edu.mocos.model.MiscSetting

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_login)
        initPage(loginActivity)
    }

    private fun initPage(view: View) {

        if (MiscSetting.BM) {
        view.button1.text = "Pensyarah Penyelia"
        view.button2.text = "Kaunselor Pembimbing"
        view.button3.text = "Kaunselor Pelatih"
        }
        if (MiscSetting.BI) {
            view.button1.text = "Supervising Lecturer"
            view.button2.text = "Guiding Counselor"
            view.button3.text = "Trainer Counselor"
        }
        refreshLayout()
        enterSL(button1)
        enterGL(button2)
        enterTC(button3)
    }

    private fun refreshLayout() {
        val myview: ViewGroup = findViewById<ViewGroup>(R.id.loginActivity)
        myview.invalidate()
    }

    private fun enterSL(btn1: Button) {
        btn1.setOnClickListener {
            val slIntent = Intent(this,ContentActivity::class.java)
            MiscSetting.user = "sl"
            startActivity(slIntent)
        }
    }

    private fun enterGL(btn2: Button) {
        btn2.setOnClickListener {
            val gcIntent = Intent(this,ContentActivity::class.java)
            MiscSetting.user = "gc"
            startActivity(gcIntent)
        }
    }

    fun enterTC(btn3: Button) {
        btn3.setOnClickListener {
            val tcIntent = Intent(this,ContentActivity::class.java)
            MiscSetting.user = "tc"
            startActivity(tcIntent)
        }
    }
}

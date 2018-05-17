package upsi.edu.mocos.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import upsi.edu.mocos.R
import upsi.edu.mocos.R.layout.activity_login
import upsi.edu.mocos.model.MiscSetting

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_login)
        initPage()
    }

    private fun initPage() {
        val btn1 = findViewById<Button>(R.id.button1)
        val btn2 = findViewById<Button>(R.id.button2)
        val btn3 = findViewById<Button>(R.id.button3)

        if (MiscSetting.BM) {
        btn1.text = "Pensyarah Penyelia"
        btn2.text = "Kaunselor Pembimbing"
        btn3.text = "Kaunselor Pelatih"
        }
        if (MiscSetting.BI) {
            btn1.text = "Supervising Lecturer"
            btn2.text = "Guiding Counselor"
            btn3.text = "Trainer Counselor"
        }
        refreshLayout()
        enterSL(btn1)
        enterGL(btn2)
        enterTC(btn3)
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

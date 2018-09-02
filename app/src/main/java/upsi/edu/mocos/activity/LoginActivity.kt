package upsi.edu.mocos.activity

import android.content.ComponentCallbacks2
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

class LoginActivity : AppCompatActivity(), ComponentCallbacks2 {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_login)
        initPage(loginActivity)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when (level) {

            ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN -> {
            }

            ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE,
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW,
            ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL -> {

            }

            ComponentCallbacks2.TRIM_MEMORY_BACKGROUND,
            ComponentCallbacks2.TRIM_MEMORY_MODERATE,
            ComponentCallbacks2.TRIM_MEMORY_COMPLETE -> {

            }

            else -> {

            }
        }
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

        enterSL(button1)
        enterGL(button2)
        enterTC(button3)
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

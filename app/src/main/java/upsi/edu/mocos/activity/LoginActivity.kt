package upsi.edu.mocos.activity

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
        setNewText()
    }

    private fun setNewText() {
        val btn1 = findViewById<Button>(R.id.button1)
        val btn2 = findViewById<Button>(R.id.button2)
        val btn3 = findViewById<Button>(R.id.button3)

        if (MiscSetting.BM) {
        btn1.text = "Pensyarah Penyelia"
        btn2.text = "Pensyarah Pembimbing"
        btn3.text = "Kauselor Pelatih"
        }
        if (MiscSetting.BI) {
            btn1.text = "Supervisor Lecturer"
            btn2.text = "Counselor Lecturer"
            btn3.text = "Trainer Counselor"
        }
        refreshLayout()
    }

    private fun refreshLayout() {
        val myview: ViewGroup = findViewById<ViewGroup>(R.id.loginActivity)
        myview.invalidate()
    }
}

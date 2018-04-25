package upsi.edu.mocos.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Switch
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MiscSetting

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        //newLang()
    }

    fun newLang() {
        /*val BM = findViewById<CheckBox>(R.id.checkBox1)
        BM.text = "BM"

        BM.setOnClickListener{
            if (BM.isChecked) {
                var newLang = MiscSetting(this,BM.isChecked)
                newLang.BI = false
                print("BM ="+BM.isChecked)
                print("newlang ="+newLang.BM)
            }
        }*/

    }


}

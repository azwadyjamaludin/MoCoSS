package upsi.edu.mocos.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_click_to_start.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.PageNavigate
import android.content.Intent
import java.util.*


class ClickToStartActivity : MocoSSParentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_to_start)
        clickImageToStart()
        }

    override fun onBackPressed() {
        val exit = Intent(Intent.ACTION_MAIN)
        exit.addCategory(Intent.CATEGORY_HOME)
        exit.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(exit)
    }

    fun clickImageToStart() {
        val image : TextView = clickToStart
        if (MiscSetting.BI) {
            image.text = getString(R.string.tapToStartEN)
        }
        if (MiscSetting.BM) {
            image.text = getString(R.string.tapToStartMY)
        }
            image.setOnClickListener({
                goToPage(PageNavigate.TabPage,this)
            })

    }

}


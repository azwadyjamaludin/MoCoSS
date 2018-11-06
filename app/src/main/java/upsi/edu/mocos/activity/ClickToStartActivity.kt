package upsi.edu.mocos.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_click_to_start.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.PageNavigate

class ClickToStartActivity : MocoSSParentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_to_start)
        clickImageToStart()
        }

    fun clickImageToStart() {
        val image : TextView = clickToStart
        if (MiscSetting.BM) {
            image.text = "Sentuh untuk mula"
            image.setOnClickListener({
                goToPage(PageNavigate.TabPage,this)
            })
        }
        if (MiscSetting.BI) {
            image.text = "Tap to start"
            image.setOnClickListener({
                goToPage(PageNavigate.TabPage,this)
            })
        }

    }

}


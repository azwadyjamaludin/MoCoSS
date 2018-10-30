package upsi.edu.mocos.activity

import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_click_to_start.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.PageNavigate

class ClickToStartActivity : MocoSSParentActivity() {
    val pageNavigate = PageNavigate.TabPage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_to_start)
        clickImageToStart()
        }

    fun clickImageToStart() {
        val image : ImageView = clickToStart
        image.setOnClickListener({
            goToTabPage(pageNavigate,this)
        })
    }

}


package upsi.edu.mocos.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_full_mark.*
import kotlinx.android.synthetic.main.activity_full_mark.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MiscSetting

class FullMarkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_mark)
        initPage(fullMarkLL)
    }

    private fun initPage(view: View) {
        val fullMarkTB = view.fullMarkTB

        if (MiscSetting.BM) {
            fullMarkTB.title = getString(R.string.content14MY)
        }
        if (MiscSetting.BI) {
            fullMarkTB.title = getString(R.string.content14EN)
        }

    }
}

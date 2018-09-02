package upsi.edu.mocos.activity

import android.content.ComponentCallbacks2
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.nshmura.recyclertablayout.RecyclerTabLayout
import kotlinx.android.synthetic.main.activity_log_book.*
import upsi.edu.mocos.R
import upsi.edu.mocos.adapter.pageradapter.LogBookPagerAdapter
import upsi.edu.mocos.model.MyObject.LogBookMgr

class LogBookActivity : AppCompatActivity(), ComponentCallbacks2 {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: LogBookPagerAdapter
    private lateinit var lBookRTL: RecyclerTabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_book)

        val logBookMgr = LogBookMgr.weekInput()
        viewPager = logBookVP//findViewById(R.id.logBookVP)
        pagerAdapter = LogBookPagerAdapter(supportFragmentManager, logBookMgr)
        viewPager.adapter = pagerAdapter

        lBookRTL = logBookRTL//findViewById(R.id.logBookRTL)
        lBookRTL.setUpWithViewPager(viewPager)

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
}

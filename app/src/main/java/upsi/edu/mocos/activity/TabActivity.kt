package upsi.edu.mocos.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tab.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.PageAdapter

class TabActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        configureTabLayout()

    }

    private fun configureTabLayout() {
        if (MiscSetting.BM) {
            tabLayout.getTabAt(0)!!.setText("Log Masuk")
            tabLayout.getTabAt(1)!!.setText("Tetapan")
            tabLayout.getTabAt(2)!!.setText("Info")
        }
        if (MiscSetting.BI) {
            tabLayout.getTabAt(0)!!.setText("Login")
            tabLayout.getTabAt(1)!!.setText("Preference")
            tabLayout.getTabAt(2)!!.setText("Info")
        }

        val adapter = PageAdapter(supportFragmentManager,tabLayout.tabCount)
        container.adapter = adapter
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab) {
                container.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

}

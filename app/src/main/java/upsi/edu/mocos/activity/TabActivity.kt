package upsi.edu.mocos.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import kotlinx.android.synthetic.main.activity_tab.*
import upsi.edu.mocos.R

import upsi.edu.mocos.adapter.PageAdapter
import upsi.edu.mocos.model.MiscSetting

class TabActivity : MocoSSParentActivity() {


    override fun createActivity(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        configureTabLayout()

    }

    private fun configureTabLayout() {
        if (MiscSetting.BM) {
            tabLayout.getTabAt(0)!!.setText("Log Masuk")
            tabLayout.getTabAt(1)!!.setText("Info")
            tabLayout.getTabAt(2)!!.setText("Tetapan")
        }
        if (MiscSetting.BI) {
            tabLayout.getTabAt(0)!!.setText("Login")
            tabLayout.getTabAt(1)!!.setText("Info")
            tabLayout.getTabAt(2)!!.setText("Setting")
        }

        val adapter = PageAdapter(supportFragmentManager, tabLayout.tabCount)
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

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_tab, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.myhome -> {
                return true
            }
            R.id.myinfo -> {
                return true
            }
            R.id.mysetting -> {
                if (MiscSetting.BM) {
                    return true
                }
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }*/

}

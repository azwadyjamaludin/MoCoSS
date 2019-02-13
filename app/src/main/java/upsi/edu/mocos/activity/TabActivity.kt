package upsi.edu.mocos.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import kotlinx.android.synthetic.main.activity_tab.*
import upsi.edu.mocos.R
import upsi.edu.mocos.adapter.PageAdapter
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.PageNavigate

class TabActivity : MocoSSParentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        configureTabLayout()
        //initLocationService()
    }

    private fun configureTabLayout() {
        if (MiscSetting.BM) {
            tabLayout.getTabAt(0)!!.setText(getString(R.string.loginTabMY))
            tabLayout.getTabAt(1)!!.setText(getString(R.string.infoTabMY))
            tabLayout.getTabAt(2)!!.setText(getString(R.string.settingTabMY))
        }
        if (MiscSetting.BI) {
            tabLayout.getTabAt(0)!!.setText(getString(R.string.loginTabEN))
            tabLayout.getTabAt(1)!!.setText(getString(R.string.infoTabEN))
            tabLayout.getTabAt(2)!!.setText(getString(R.string.settingTabEN))
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

    override fun onBackPressed() {
        goToPage(PageNavigate.StartPage,this)
    }

}

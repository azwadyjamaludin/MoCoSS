package upsi.edu.mocos.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import upsi.edu.mocos.fragment.AboutFragment
import upsi.edu.mocos.fragment.LoginFragment
import upsi.edu.mocos.fragment.SettingFragment

/**
 * Created by azwady on 19/04/2018.
 */
class PageAdapter (fm: FragmentManager, private var tabCount: Int) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return LoginFragment()
            1 -> return AboutFragment()
            2 -> return SettingFragment()

            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}
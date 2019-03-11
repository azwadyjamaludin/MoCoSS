package upsi.edu.mocos.adapter.miscadapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import upsi.edu.mocos.fragment.logBook.LogBookFragment
import upsi.edu.mocos.model.MyData.LogBook
import kotlin.collections.ArrayList

class LogBookPagerAdapter(fm: FragmentManager, private val lbWeek: ArrayList<LogBook>) : FragmentStatePagerAdapter(fm) {

    var initString:String

    init {
        initString = "LogBookPagerAdapter"
    }

    override fun getItem(position: Int): Fragment {
        return LogBookFragment.newInstance(lbWeek[position % lbWeek.size])
    }

    override fun getCount(): Int {
        return lbWeek.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return lbWeek[position % lbWeek.size].weekTab
    }
}

package upsi.edu.mocos.activity

import android.content.ComponentCallbacks2
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import upsi.edu.mocos.model.PageNavigate

abstract class MocoSSParentActivity : AppCompatActivity(), ComponentCallbacks2 {

    fun clearPage(context: Context) {
        val intent = Intent(context, MocoSSParentActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK

    }

    fun goToPage(pageNavigate: PageNavigate,context: Context) {
        if (pageNavigate == PageNavigate.TabPage ) {
                val toTabPage = Intent(context, TabActivity::class.java)
                startActivity(toTabPage)
        }
        if (pageNavigate == PageNavigate.StartPage) {
                val toStartPage = Intent(context,ClickToStartActivity::class.java)
                startActivity(toStartPage)
        }
    }

    open fun createActivity (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
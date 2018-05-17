package upsi.edu.mocos.model

import android.app.Application

/**
 * Created by azwady on 20/04/2018.
 */

class MiscSetting: Application() {


    companion object {
        private var instance: MiscSetting? = null
        var BI: Boolean = false
        var BM: Boolean = true
        var user:String = "user"

        fun applicationContext() : MiscSetting {
            return instance!!
        }
    }

    init {
        instance = this

    }


}
package upsi.edu.mocos.model.MyObject

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Environment
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.doAsync
import java.io.*

object DummyMgr {
    private var className:String = ""


    init {
        className = "DummyMgr"

    }

    fun writeCA1(context: Context):ByteArray {

        val assetManager: AssetManager = context.assets

        val inputStream: InputStream = assetManager.open("CaseAnalysis1.pdf")
        val buff = ByteArrayOutputStream()
        val data = ByteArray(1024)
        var len: Int

        while (true) {
            len = inputStream.read(data)
            if (len >  0) {
                buff.write(data, 0, len)
            }
        }
        inputStream.close()
        return data

    }

        fun writeCA2(context: Context): ByteArray {
            val assetManager: AssetManager = context.assets

                val inputStream: InputStream = assetManager.open("CaseAnalysis2.pdf")
                val buff = ByteArrayOutputStream()
                val data = ByteArray(1024)
                var len: Int

                while (true) {
                    len = inputStream.read(data)
                    if (len > 0) {
                        buff.write(data, 0, len)
                    }
                }
                inputStream.close()
                return data
        }
    }
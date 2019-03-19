package upsi.edu.mocos.model

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

class DummyData constructor(className:String){

    fun writeCA1(context: Context):File {
        val assetManager: AssetManager = context.assets

        val inputStream: InputStream = assetManager.open("CaseAnalysis1.pdf")
        val shareFile = File.createTempFile("CA1","pdf")
        val isFileCreated = shareFile.createNewFile()
            if (isFileCreated) {
                val outputStream: OutputStream = FileOutputStream(shareFile)
                val data = ByteArray(1024)
                var len: Int

                while (true) {
                    len = inputStream.read(data)
                    if (len > 0) {
                        outputStream.write(data, 0, len)
                    }
                }
                outputStream.close()
                inputStream.close()
            }
            return shareFile

    }

        fun writeCA2(context: Context): File {
            val assetManager: AssetManager = context.assets

                val inputStream: InputStream = assetManager.open("CaseAnalysis2.pdf")
                val shareFile = File.createTempFile("CA2", "pdf")
                val isFileCreated = shareFile.createNewFile()
                    if (isFileCreated) {
                        val outputStream: OutputStream = FileOutputStream(shareFile)
                        val data = ByteArray(1024)
                        var len: Int

                        while (true) {
                            len = inputStream.read(data)
                            if (len > 0) {
                                outputStream.write(data, 0, len)
                            }
                        }
                        outputStream.close()
                        inputStream.close()
                    }
                    return shareFile

        }

        fun writeReflect(context: Context):File {

            val assetManager: AssetManager = context.assets

                val inputStream: InputStream = assetManager.open("Reflect.pdf")
                val shareFile = File.createTempFile("Share","pdf")
                 val isFileCreated = shareFile.createNewFile()
                if (isFileCreated) {
                    val outputStream: OutputStream = FileOutputStream(shareFile)
                    val data = ByteArray(1024)
                    var len: Int

                    while (true) {
                        len = inputStream.read(data)
                        if (len > 0) {
                            outputStream.write(data, 0, len)
                        }
                    }
                    outputStream.close()
                    inputStream.close()
                }
                return shareFile

        }
    }
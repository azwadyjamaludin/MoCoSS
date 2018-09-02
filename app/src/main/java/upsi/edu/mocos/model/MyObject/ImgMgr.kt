package upsi.edu.mocos.model.MyObject

import android.util.Log
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.MyData.ImgInfo


object ImgMgr {
        var initString = ""
        val internInfoBM:Array<String> = arrayOf("intern_info_bm1","intern_info_bm2")
        val internInfoBI:Array<String> = arrayOf("intern_info_bi1", "intern_info_bi2")


    init {
        initString = "ImgMgr"
        //imgInput()
    }

    fun imgInput():ArrayList<ImgInfo> {
        var pageInt: Int
        var pageNo: String
        var imgID: String
        val images :ArrayList<ImgInfo> = arrayListOf()
        if (MiscSetting.BM) {
            for (item in 0 until internInfoBM.count()) {
                imgID = internInfoBM.get(item)
                pageInt = item+1
                pageNo = "Mukasurat "+pageInt.toString()
                images.add(ImgInfo(imgID, pageNo,item))
            }
            Log.d("images", images.toString())
        }
        if (MiscSetting.BI) {
            for (item in 0 until internInfoBI.count()) {
                imgID = internInfoBI.get(item)
                pageInt = item+1
                pageNo = "Page "+pageInt.toString()
                images.add(ImgInfo(imgID, pageNo,item))
            }
            Log.d("images", images.toString())
        }
        return images
    }


}
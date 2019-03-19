package upsi.edu.mocos.activity

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.activity_user_info.view.*
import kotlinx.android.synthetic.main.custom_button_layout.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.PageNavigate

class UserInfoActivity : MoCoSSParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        if (MiscSetting.user == "tc") {
            initPageTC(userInfoActivity)
            tcInfo(userInfoActivity)
        }
        if(MiscSetting.user == "sl") {
            initPageSL(userInfoActivity)
            slInfo(userInfoActivity)
        }
        if (MiscSetting.user == "gc") {
            initPageGC(userInfoActivity)
            gcInfo(userInfoActivity)
        }

    }

    override fun onBackPressed() {

    }

    private fun initPageTC(view: View) {
        val userInfoTB = view.userInfoTB
        val fullName = view.fullName
        val idNo = view.idNo
        val internLoc = view.internLoc
        val nextPageBtn = view.nextPage
        if (MiscSetting.BM) {
            userInfoTB.title = "Butir pelatih"
            fullName.text = "Nama penuh:"
            idNo.text = "Nombor matriks:"
            internLoc.text = "Tempat internship:"
            nextPageBtn.customText.text = "Muka seterusnya"
            nextPageBtn.customText.setOnClickListener ({
                goToPage(PageNavigate.ContentPage,this)
            })
        }
        if (MiscSetting.BI) {
            userInfoTB.title = "Trainer detail"
            fullName.text = "Full name:"
            idNo.text = "Matrics number:"
            internLoc.text = "Internship location:"
            nextPageBtn.customText.text = "Next page"
            nextPageBtn.customText.setOnClickListener ({
                goToPage(PageNavigate.ContentPage,this)
            })
        }
    }

    private fun initPageGC(view: View) {
        val userInfoTB = view.userInfoTB
        val fullName = view.fullName
        val idNo = view.idNo
        val internLoc = view.internLoc
        val nextPageBtn = view.nextPage
        if (MiscSetting.BM) {
            userInfoTB.title = "Butir pembimbing"
            fullName.text = "Nama penuh:"
            idNo.text = "Nombor ID:"
            internLoc.text = "Tempat pembimbing:"
            nextPageBtn.customText.text = "Muka seterusnya"
            nextPageBtn.customText.setOnClickListener ({
                goToPage(PageNavigate.ContentPage,this)
            })
        }
        if (MiscSetting.BI) {
            userInfoTB.title = "Guider's detail"
            fullName.text = "Full name:"
            idNo.text = "ID Number:"
            internLoc.text = "Guider's location:"
            nextPageBtn.customText.text = "Next page"
            nextPageBtn.customText.setOnClickListener ({
                goToPage(PageNavigate.ContentPage,this)
            })
        }
    }

    private fun initPageSL(view: View) {
        val userInfoTB = view.userInfoTB
        val fullName = view.fullName
        val idNo = view.idNo
        val internLoc = view.internLoc
        val nextPageBtn = view.nextPage
        if (MiscSetting.BM) {
            userInfoTB.title = "Butir penyelia"
            fullName.text = "Nama penuh:"
            idNo.text = "Nombor ID:"
            internLoc.text = "Tempat penyelia:"
            nextPageBtn.customText.text = "Muka seterusnya"
            nextPageBtn.customText.setOnClickListener ({
                goToPage(PageNavigate.ContentPage,this)
            })
        }
        if (MiscSetting.BI) {
            userInfoTB.title = "Supervisor's detail"
            fullName.text = "Full name:"
            idNo.text = "ID Number:"
            internLoc.text = "Supervisor's location:"
            nextPageBtn.customText.text = "Next page"
            nextPageBtn.customText.setOnClickListener ({
                goToPage(PageNavigate.ContentPage,this)
            })
        }
    }

    private fun tcInfo(view: View) {
        val textFullName = view.textFullName
        val textIdNo = view.textIdNo
        val textInternLoc = view.textInternLoc
        textFullName.text = "tctest"
        textIdNo.text = "TC96590001"
        textInternLoc.text = "SMK Alif Ba Ta"
    }

    private fun gcInfo(view: View) {
        val textFullName = view.textFullName
        val textIdNo = view.textIdNo
        val textInternLoc = view.textInternLoc
        textFullName.text = "gctest"
        textIdNo.text = "GC-73910"
        textInternLoc.text = "IPG Jim Ha Kho"
    }

    private fun slInfo(view: View) {
        val textFullName = view.textFullName
        val textIdNo = view.textIdNo
        val textInternLoc = view.textInternLoc
        textFullName.text = "sltest"
        textIdNo.text = "SL-ABC-9512-K"
        textInternLoc.text = "Universiti Dal Zai Ro"
    }
}

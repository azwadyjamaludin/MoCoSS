package upsi.edu.mocos.activity

import android.support.v7.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.custom_button_layout.view.*
import upsi.edu.mocos.R
import upsi.edu.mocos.R.layout.activity_login
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.PageNavigate
import upsi.edu.mocos.ui_component.CustomButton

class LoginActivity : MocoSSParentActivity() {
    var userName:String = ""
    var passWord:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_login)
        initPage(loginActivity)

    }

    override fun onBackPressed() {
        goToPage(PageNavigate.TabPage,this)
    }

    private fun initPage(view: View) {
        val slUser = view.slUser
        val gcUser = view.gcUser
        val tcUser = view.tcUser

        if (MiscSetting.BM) {
            slUser.customText.text = getString(R.string.slUserMY)
            gcUser.customText.text = getString(R.string.gcUserMY)
            tcUser.customText.text = getString(R.string.tcUserMY)
        }
        if (MiscSetting.BI) {
            slUser.customText.text = getString(R.string.slUserEN)
            gcUser.customText.text = getString(R.string.gcUserEN)
            tcUser.customText.text = getString(R.string.tcUserEN)
        }

        enterSL(slUser)
        enterGC(gcUser)
        enterTC(tcUser)
    }

    private fun enterSL(slUser: CustomButton) {
        slUser.customText.setOnClickListener() {
            slUserPassDialog()
        }
    }

    private fun enterGC(gcUser: CustomButton) {
        gcUser.customText.setOnClickListener {
            gcUserPassDialog()
        }
    }

    fun enterTC(tcUser: CustomButton) {
            tcUser.customText.setOnClickListener {
            tcUserPassDialog()
        }
    }

    fun slUserPassDialog() {
        val context = this
        val builder = AlertDialog.Builder(context)
        val userPassDialog = LayoutInflater.from(this).inflate(R.layout.dialog_user_pass_input, null)
        builder.setView(userPassDialog)
        builder.setIcon(R.drawable.mocoss_img)
        builder.setTitle(getString(R.string.app_name))
        val slIntent = Intent(this,ContentActivity::class.java)
        if (MiscSetting.BI) {
            builder.setPositiveButton("Enter") {dialogInterface, i ->
                userName = userPassDialog.findViewById<EditText>(R.id.user).text.toString()
                passWord = userPassDialog.findViewById<EditText>(R.id.pass).text.toString()
                if (userName.equals("sltest") && passWord.equals("sltest")) {
                    MiscSetting.user = "sl"
                    startActivity(slIntent)
                }else {
                    dialogDismissEN()
                }
            }
        }
        if (MiscSetting.BM) {
            builder.setPositiveButton("Masuk") {dialogInterface, i ->
                userName = userPassDialog.findViewById<EditText>(R.id.user).text.toString()
                passWord = userPassDialog.findViewById<EditText>(R.id.pass).text.toString()
                if (userName.equals("sltest") && passWord.equals("sltest")) {
                    MiscSetting.user = "sl"
                    startActivity(slIntent)
                }else {
                    dialogDismissMY()
                }
            }
        }

        builder.show()
    }

    fun gcUserPassDialog() {
        val context = this
        val builder = AlertDialog.Builder(context)
        val userPassDialog = LayoutInflater.from(this).inflate(R.layout.dialog_user_pass_input, null)
        builder.setView(userPassDialog)
        builder.setIcon(R.drawable.mocoss_img)
        builder.setTitle(getString(R.string.app_name))
        val gcIntent = Intent(this,ContentActivity::class.java)
        if (MiscSetting.BI) {
            builder.setPositiveButton("Enter") {dialogInterface, i ->
                userName = userPassDialog.findViewById<EditText>(R.id.user).text.toString()
                passWord = userPassDialog.findViewById<EditText>(R.id.pass).text.toString()
                if (userName.equals("gctest") && passWord.equals("gctest")) {
                    MiscSetting.user = "gc"
                    startActivity(gcIntent)
                }else {
                    dialogDismissEN()
                }
            }
        }
        if (MiscSetting.BM) {
            builder.setPositiveButton("Masuk") {dialogInterface, i ->
                userName = userPassDialog.findViewById<EditText>(R.id.user).text.toString()
                passWord = userPassDialog.findViewById<EditText>(R.id.pass).text.toString()
                if (userName.equals("gctest") && passWord.equals("gctest")) {
                    MiscSetting.user = "gc"
                    startActivity(gcIntent)
                }else {
                    dialogDismissMY()
                }
            }
        }

        builder.show()
    }

    fun tcUserPassDialog() {
        val context = this
        val builder = AlertDialog.Builder(context)
        val userPassDialog = LayoutInflater.from(this).inflate(R.layout.dialog_user_pass_input, null)
        builder.setView(userPassDialog)
        builder.setIcon(R.drawable.mocoss_img)
        builder.setTitle(getString(R.string.app_name))
        val tcIntent = Intent(this,ContentActivity::class.java)
        if (MiscSetting.BI) {
            builder.setPositiveButton("Enter") {dialogInterface, i ->
                userName = userPassDialog.findViewById<EditText>(R.id.user).text.toString()
                passWord = userPassDialog.findViewById<EditText>(R.id.pass).text.toString()
                if (userName.equals("tctest") && passWord.equals("tctest")) {
                    MiscSetting.user = "tc"
                    startActivity(tcIntent)
                }else {
                    dialogDismissEN()
                }
            }
        }
        if (MiscSetting.BM) {
            builder.setPositiveButton("Masuk") {dialogInterface, i ->
                userName = userPassDialog.findViewById<EditText>(R.id.user).text.toString()
                passWord = userPassDialog.findViewById<EditText>(R.id.pass).text.toString()
                if (userName.equals("tctest") && passWord.equals("tctest")) {
                    MiscSetting.user = "tc"
                    startActivity(tcIntent)
                }else {
                    dialogDismissMY()
                }
            }
        }

        builder.show()
    }

    fun dialogDismissEN() {
        val context = this
        val builder = AlertDialog.Builder(context)
        val dismissDialog = LayoutInflater.from(this).inflate(R.layout.dialog_wr_cr_en,null)
        val wrCrText = dismissDialog.findViewById<TextView>(R.id.wrongCredText)
        //wrCrText.visibility = View.INVISIBLE

        builder.setView(dismissDialog)
        builder.setIcon(R.drawable.mocoss_img)
        builder.setTitle(getString(R.string.app_name))


            builder.setPositiveButton("Dismiss") {dialogInterface, i ->
                        wrCrText.visibility = View.VISIBLE
            }
        builder.show()
    }

    fun dialogDismissMY() {
        val context = this
        val builder = AlertDialog.Builder(context)
        val dismissDialog = LayoutInflater.from(this).inflate(R.layout.dialog_wr_cr_my,null)
        val wrCrText2 = dismissDialog.findViewById<TextView>(R.id.wrongCredText2)
        //wrCrText2.visibility = View.INVISIBLE

        builder.setView(dismissDialog)
        builder.setIcon(R.drawable.mocoss_img)
        builder.setTitle(getString(R.string.app_name))


        builder.setPositiveButton("Dismiss") {dialogInterface, i ->
            wrCrText2.visibility = View.VISIBLE
        }
        builder.show()
    }
}

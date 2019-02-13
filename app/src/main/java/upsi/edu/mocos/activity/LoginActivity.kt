package upsi.edu.mocos.activity

import android.support.v7.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
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
    var userString : String = ""
    var passString : String = ""

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
        val userName = userPassDialog.findViewById<EditText>(R.id.user)
        val passWord = userPassDialog.findViewById<EditText>(R.id.pass)
        val enterBtn = userPassDialog.findViewById<Button>(R.id.enterButton)

        builder.setView(userPassDialog)
        builder.setIcon(R.drawable.mocoss_img)
        builder.setTitle(getString(R.string.app_name))
        val dialog = builder.create()
        val slIntent = Intent(this,ContentActivity::class.java)

        if (MiscSetting.BI) {
                enterBtn.setText("Enter")
                enterBtn.setOnClickListener ({
                    userString = userName.text.toString()
                    passString = passWord.text.toString()
                    if (userString.equals("sltest") && passString.equals("sltest")) {
                    dialog.dismiss()
                    MiscSetting.user = "sl"
                    startActivity(slIntent)
                    }else {
                    dialogWrCr()
                    }
                })
        }
        if (MiscSetting.BM) {
                enterBtn.setText("Masuk")
                enterBtn.setOnClickListener({
                    userString = userName.text.toString()
                    passString = passWord.text.toString()
                    if (userString.equals("sltest") && passString.equals("sltest")) {
                        dialog.dismiss()
                        MiscSetting.user = "sl"
                        startActivity(slIntent)
                    }else {
                        dialogWrCr()
                    }
                })
        }
        dialog.show()
    }

    fun gcUserPassDialog() {
        val context = this
        val builder = AlertDialog.Builder(context)
        val userPassDialog = LayoutInflater.from(this).inflate(R.layout.dialog_user_pass_input, null)
        val userName = userPassDialog.findViewById<EditText>(R.id.user)
        val passWord = userPassDialog.findViewById<EditText>(R.id.pass)
        val enterBtn = userPassDialog.findViewById<Button>(R.id.enterButton)

        builder.setView(userPassDialog)
        builder.setIcon(R.drawable.mocoss_img)
        builder.setTitle(getString(R.string.app_name))
        val dialog = builder.create()
        val gcIntent = Intent(this,ContentActivity::class.java)

        if (MiscSetting.BI) {
                enterBtn.setText("Enter")
                enterBtn.setOnClickListener ({
                    userString = userName.text.toString()
                    passString = passWord.text.toString()
                    if (userString.equals("gctest") && passString.equals("gctest")) {
                        dialog.dismiss()
                        MiscSetting.user = "gc"
                        startActivity(gcIntent)
                    } else {
                        dialogWrCr()
                    }
                })
        }
        if (MiscSetting.BM) {
                enterBtn.setText("Masuk")
                enterBtn.setOnClickListener({
                    userString = userName.text.toString()
                    passString = passWord.text.toString()
                    if (userString.equals("gctest") && passString.equals("gctest")) {
                    dialog.dismiss()
                    MiscSetting.user = "gc"
                    startActivity(gcIntent)
                    }else {
                    dialogWrCr()
                    }
                })
        }
        dialog.show()
    }

    fun tcUserPassDialog() {
        val context = this
        val builder = AlertDialog.Builder(context)
        val userPassDialog = LayoutInflater.from(this).inflate(R.layout.dialog_user_pass_input, null)
        val userName = userPassDialog.findViewById<EditText>(R.id.user)
        val passWord = userPassDialog.findViewById<EditText>(R.id.pass)
        val enterBtn = userPassDialog.findViewById<Button>(R.id.enterButton)

        builder.setView(userPassDialog)
        builder.setIcon(R.drawable.mocoss_img)
        builder.setTitle(getString(R.string.app_name))
        val dialog = builder.create()
        val tcIntent = Intent(this,ContentActivity::class.java)

        if (MiscSetting.BI) {
                enterBtn.setText("Enter")
                enterBtn.setOnClickListener({
                    userString = userName.text.toString()
                    passString = passWord.text.toString()
                    if (userString.equals("tctest") && passString.equals("tctest")) {
                    dialog.dismiss()
                    MiscSetting.user = "tc"
                    startActivity(tcIntent)
                    }else {
                    dialogWrCr()
                    }
                })
        }
        if (MiscSetting.BM) {
                enterBtn.setText("Masuk")
                enterBtn.setOnClickListener({
                    userString = userName.text.toString()
                    passString = passWord.text.toString()
                    if (userString.equals("tctest") && passString.equals("tctest")) {
                    dialog.dismiss()
                    MiscSetting.user = "tc"
                    startActivity(tcIntent)
                    }else {
                    dialogWrCr()
                    }
                })
        }
        dialog.show()
    }

    fun dialogWrCr() {
        val context = this
        val builder = AlertDialog.Builder(context)
        val dismissDialog = LayoutInflater.from(this).inflate(R.layout.dialog_no_input,null)
        val wrCrText = dismissDialog.findViewById<TextView>(R.id.wrongCredText)
        val dismissBtn = dismissDialog.findViewById<Button>(R.id.dismissButton)

        builder.setView(dismissDialog)
        val dialog = builder.create()

        if (MiscSetting.BI) {
            wrCrText.setText(R.string.wrongUserPassDialogEN)
            dismissBtn.setText(R.string.dismissButtonEN)
            dismissBtn.setOnClickListener({
                dialog.dismiss()
            })
        }
        if (MiscSetting.BM) {
            wrCrText.setText(R.string.wrongUserPassDialogMY)
            dismissBtn.setText(R.string.dismissButtonMY)
            dismissBtn.setOnClickListener({
                dialog.dismiss()
            })
        }

        dialog.show()
    }
}

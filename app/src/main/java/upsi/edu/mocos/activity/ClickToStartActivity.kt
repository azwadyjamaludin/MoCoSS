package upsi.edu.mocos.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_start_app.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.LocationService
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.PageNavigate
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter


class ClickToStartActivity : MoCoSSParentActivity() {
    internal lateinit var locationService: LocationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_app)
        clickImageToStart(contentActivity)

        //initLocationService()
        }

    override fun onBackPressed() {
        val exit = Intent(Intent.ACTION_MAIN)
        exit.addCategory(Intent.CATEGORY_HOME)
        exit.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(exit)
    }

    fun clickImageToStart(view: View) {


        if (MiscSetting.BI) {
            startDialogEN(view)
        }
        if (MiscSetting.BM) {
            startDialogMY(view)
        }

    }

    protected fun initLocationService() {
        locationService = LocationService(this@ClickToStartActivity)
        if (locationService.canGetLocation()) {
            val latitude = locationService.getLatitude()
            val longitude = locationService.getLongitude()
            updateLocation(locationService, latitude, longitude)
        }
    }

    private fun updateLocation(myLocation: LocationService, latitude: Double, longitude: Double) {
        if (myLocation.canGetLocation()) {
            var result: String = "Latitude: " + latitude +
                    " Longitude: " + longitude
            Log.d("result", result)
            myLocation.getAddressFromLocation(latitude, longitude,
                    this@ClickToStartActivity, GeocoderHandler())
        } else {
            showSettingsAlert()
        }

    }

    private fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(this@ClickToStartActivity)
        alertDialog.setTitle("SETTINGS")
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?")
        alertDialog.setPositiveButton("Settings") { dialog, which ->
            val intent = Intent(
                    Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            this@ClickToStartActivity.startActivity(intent)
        }

        alertDialog.setNegativeButton("Cancel",
                object: DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which:Int) {
                        dialog.cancel()
                    }
                })
        alertDialog.show()
    }


    private inner class GeocoderHandler : Handler() {
        override fun handleMessage(message: Message) {
            val locationAddress: String?
            when (message.what) {
                1 -> {
                    val bundle = message.data
                    locationAddress = bundle.getString("address")
                    val file = "address.txt"
                    val fos: FileOutputStream

                    try {
                        fos = openFileOutput(file, Context.MODE_PRIVATE)
                        val outputWriter = OutputStreamWriter(fos)
                        outputWriter.write(locationAddress!!)
                        outputWriter.close()

                        //System.out.println("saved addressInfo"+locationAddress);
                    } catch (e: IOException) {
                        System.err.println("Failed to open address.txt file")
                        e.printStackTrace()
                    }

                }
                else -> locationAddress = null
            }
            Log.d("TAG", "The address data: " + locationAddress!!)
        }
    }



    private fun startDialogMY(view:View) {
        val wrCrTextUPSI = view.findViewById<TextView>(R.id.wrongCredTextUPSI)
        val dismissBtnUPSI = view.findViewById<Button>(R.id.dismissButtonUPSI)

        wrCrTextUPSI.setText("UNIVERSITI NO.1 PENDIDIKAN")
        dismissBtnUPSI.setText("Teruskan")
        dismissBtnUPSI.setOnClickListener({
            goToPage(PageNavigate.TabPage,this)
        })
    }

    private fun startDialogEN(view: View) {
        val wrCrTextUPSI = view.findViewById<TextView>(R.id.wrongCredTextUPSI)
        val dismissBtnUPSI = view.findViewById<Button>(R.id.dismissButtonUPSI)

        wrCrTextUPSI.setText("NO.1 EDUCATION UNIVERSITY")
        dismissBtnUPSI.setText("Proceed")
        dismissBtnUPSI.setOnClickListener({
            goToPage(PageNavigate.TabPage,this)
        })
    }

}


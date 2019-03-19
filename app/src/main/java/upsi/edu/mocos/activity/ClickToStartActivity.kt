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
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
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
        clickImageToStart()
        //initLocationService()
        }

    override fun onBackPressed() {

    }

    fun clickImageToStart() {

        if (MiscSetting.BI) {
            startDialogEN()
        }
        if (MiscSetting.BM) {
            startDialogMY()
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



    private fun startDialogMY() {
        val dialogBuilder = AlertDialog.Builder(this)
        val startDialog = LayoutInflater.from(this).inflate(R.layout.dialog_start_activity,null)
        val mottoTextUPSI = startDialog.findViewById<TextView>(R.id.mottoTextDialUPSI)
        val dismissBtnUPSI = startDialog.findViewById<Button>(R.id.dismissButtonDialUPSI)

        dialogBuilder.setView(startDialog)
        dialogBuilder.setCancelable(false)
        val alert = dialogBuilder.create()
        mottoTextUPSI.setText("UNIVERSITI NO.1 PENDIDIKAN")
        dismissBtnUPSI.setText("Teruskan")
        dismissBtnUPSI.setOnClickListener({
            alert.dismiss()
            goToPage(PageNavigate.TabPage,this)
        })
        alert.show()
    }

    private fun startDialogEN() {
        val dialogBuilder = AlertDialog.Builder(this)
        val startDialog = LayoutInflater.from(this).inflate(R.layout.dialog_start_activity,null)
        val mottoTextUPSI = startDialog.findViewById<TextView>(R.id.mottoTextDialUPSI)
        val dismissBtnUPSI = startDialog.findViewById<Button>(R.id.dismissButtonDialUPSI)

        dialogBuilder.setView(startDialog)
        dialogBuilder.setCancelable(false)
        val alert = dialogBuilder.create()
        mottoTextUPSI.setText("NO.1 EDUCATION UNIVERSITY")
        dismissBtnUPSI.setText("Proceed")
        dismissBtnUPSI.setOnClickListener({
            alert.dismiss()
            goToPage(PageNavigate.TabPage,this)
        })
        alert.show()
    }
}


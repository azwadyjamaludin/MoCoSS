package upsi.edu.mocos.activity

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_click_to_start.*
import upsi.edu.mocos.R
import upsi.edu.mocos.model.MiscSetting
import upsi.edu.mocos.model.PageNavigate
import android.content.Intent
import android.content.pm.PackageInfo
import android.os.Handler
import android.os.Message
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.util.Log
import upsi.edu.mocos.model.LocationService
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.util.*


class ClickToStartActivity : MocoSSParentActivity() {
    internal lateinit var locationService: LocationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_to_start)
        clickImageToStart()
        appVersion()
        //initLocationService()
        }

    override fun onBackPressed() {
        val exit = Intent(Intent.ACTION_MAIN)
        exit.addCategory(Intent.CATEGORY_HOME)
        exit.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(exit)
    }

    fun clickImageToStart() {
        val image : TextView = clickToStart
        val mocosslogo: ImageView = mocossLogo

        if (MiscSetting.BI) {
            image.text = getString(R.string.tapToStartEN)
        }
        if (MiscSetting.BM) {
            image.text = getString(R.string.tapToStartMY)
        }
            mocosslogo.setOnClickListener {
                goToPage(PageNavigate.TabPage,this)
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

    private fun appVersion() {

        val pInfo : PackageInfo = packageManager.getPackageInfo(packageName,0)
        if (MiscSetting.BI) {
            versionText.text = "version "+pInfo.versionName
        }
        if (MiscSetting.BM) {
            versionText.text = "versi "+pInfo.versionName
        }

    }

}


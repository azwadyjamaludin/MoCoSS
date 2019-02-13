package upsi.edu.mocos.model

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.util.Log

import java.io.IOException
import java.util.Locale

/**
 * Created by azwady on 20/01/2019.
 */
class LocationService : Service, LocationListener {
    private lateinit var mContext: Context
    internal var isGPSEnabled = false
    internal var isNetworkEnabled = false
    internal var canGetLocation = false
    protected var locationManager: LocationManager? = null
    internal var location: Location? = null
    internal var latitude: Double = 0.toDouble()
    internal var longitude: Double = 0.toDouble()
    internal var result = ""

    constructor()

    constructor(context: Context) {
        this.mContext = context
        getLocation(mContext)
    }


    fun getLocation(context: Context): Location? {
        try {
            locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager

            // getting GPS status
            isGPSEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)

            // getting network status
            isNetworkEnabled = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    if (locationManager != null) {
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            location = locationManager!!
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                            if (location != null) {
                                latitude = location!!.latitude
                                longitude = location!!.longitude
                            }
                        }
                    }
                    locationManager!!.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_FOR_UPDATE,
                            MIN_DISTANCE_FOR_UPDATE.toFloat(), this)
                    Log.d("Network", "Network")
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (locationManager != null) {
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            if (location != null) {
                                latitude = location!!.latitude
                                longitude = location!!.longitude
                            }
                        }
                    }
                    locationManager!!.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_FOR_UPDATE,
                            MIN_DISTANCE_FOR_UPDATE.toFloat(), this)
                    Log.d("GPS Enabled", "GPS Enabled")
                } else {
                    stopUsingGPS()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return location
    }

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     */
    fun stopUsingGPS() {
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(mContext!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext!!, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            locationManager!!.removeUpdates(this@LocationService)
        }
    }

    /**
     * Function to get latitude
     */
    fun getLatitude(): Double {
        if (location != null) {
            latitude = location!!.latitude
        }

        // return latitude
        return latitude
    }

    /**
     * Function to get longitude
     */
    fun getLongitude(): Double {
        if (location != null) {
            longitude = location!!.longitude
        }

        // return longitude
        return longitude
    }

    /**
     * Function to check GPS/wifi enabled
     * @return boolean
     */
    fun canGetLocation(): Boolean {
        return this.canGetLocation
    }

    fun getAddressFromLocation(latitude: Double, longitude: Double, context: Context, handler: Handler) {
        val thread = object : Thread() {
            override fun run() {
                val geocoder = Geocoder(context, Locale.getDefault())

                try {
                    val addressList = geocoder.getFromLocation(
                            latitude, longitude, 1)
                    if (addressList != null && addressList.size > 0) {
                        val address = addressList[0]
                        val sb = StringBuilder()
                        for (i in 0 until address.maxAddressLineIndex) {
                            sb.append(address.getAddressLine(i)).append("\n")
                        }
                        /*sb.append(address.getLocality()).append("\n");
                        sb.append(address.getPostalCode()).append("\n");*/
                        sb.append(address.adminArea).append("\n")
                        sb.append(address.countryName)
                        result = sb.toString()
                    }
                } catch (e: IOException) {
                    Log.e(TAG, "Unable connect to Geocoder", e)
                    geoCoderNotAvailable()
                } finally {
                    val message = Message.obtain()
                    message.target = handler
                    if (result !== "") {
                        message.what = 1
                        val bundle = Bundle()
                        result = "Latitud: " + latitude + " Longitud: " + longitude +
                                "\n\nAlamat:\n" + result
                        bundle.putString("address", result)
                        message.data = bundle

                    } else {
                        message.what = 1
                        val bundle = Bundle()
                        result = "Latitude: " + latitude + " Longitude: " + longitude +
                                "\n Unable to get address for this lat-long."
                        bundle.putString("address", result)
                        message.data = bundle
                    }
                    message.sendToTarget()
                }
            }
        }
        thread.start()
    }

    override fun onLocationChanged(location: Location) {

    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    protected fun geoCoderNotAvailable() {
        val alertDialog = AlertDialog.Builder(
                this@LocationService)
        alertDialog.setTitle("")
        alertDialog.setMessage("Tiada servis 'GeoCode'")
        alertDialog.setPositiveButton("OK"
        ) { dialog, which -> dialog.cancel() }
        alertDialog.show()
    }

    companion object {
        private val TAG = "LocationAddress"

        private val MIN_DISTANCE_FOR_UPDATE: Long = 10
        private val MIN_TIME_FOR_UPDATE = (1000 * 60 * 2).toLong()
    }
}

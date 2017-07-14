package com.distance_tracker.km

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.distance_tracker.km.AppPreferences.setLat
import com.distance_tracker.km.AppPreferences.setLng

class MainActivity : AppCompatActivity() {
    internal lateinit var startBtn: Button
    internal var on: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startBtn = findViewById(R.id.btn_onoff) as Button
    }

    fun startStop(view: View) {
        checkLocationPermission()
        val tracker = com.distance_tracker.km.GPSTracker(this)
        if ((!on!!)!!) {
            tracker.showSettingsAlert()
            setLat(this, tracker.getLatitude().toString())
            setLng(this, tracker.getLongitude().toString())
            on = true
            startBtn.text = "Stop Tracking"
        } else {
            tracker.stopUsingGPS()
            setLat(this, "")
            setLng(this, "")
            on = false
            startBtn.text = "Start Tracking"
        }

    }

    fun distance(view: View) {
        val intent = Intent(this, com.distance_tracker.km.DistanceActivity::class.java)
        startActivity(intent)
    }

    fun location(view: View) {
        val intent = Intent(this, com.distance_tracker.km.LocationActivity::class.java)
        startActivity(intent)
    }

    fun checkLocationPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder(this)
                        .setTitle("Location Permission")
                        .setMessage("Allow Tracking?")
                        .setPositiveButton("OK") { dialogInterface, i ->
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(this@MainActivity,
                                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                    MY_PERMISSIONS_REQUEST_LOCATION)
                        }
                        .create()
                        .show()


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION)
            }
            return false
        } else {
            return true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return
            }
        }
    }

    companion object {

        val MY_PERMISSIONS_REQUEST_LOCATION = 99
    }
}

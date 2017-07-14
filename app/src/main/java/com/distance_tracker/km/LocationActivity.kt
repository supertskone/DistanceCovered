package com.distance_tracker.km

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class LocationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        val tracker = GPSTracker(this)
        val location = tracker.getLocation()
        val displayLocation = findViewById(R.id.display_location) as TextView
        if (location != null) {
            displayLocation.text = "Latitude: " + location.latitude + "\n" + "Longitude: " + location?.longitude
        }
    }
}

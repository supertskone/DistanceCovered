package com.distance_tracker.km

import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class DistanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distance)
        val distanceView = findViewById(R.id.display_distance) as TextView
        try {
            val tracker = com.distance_tracker.km.GPSTracker(this)
            val currentLocation = tracker.getLocation()
            val oldLocation = Location("")
            oldLocation.latitude = java.lang.Double.parseDouble(com.distance_tracker.km.AppPreferences.getLat(this))
            oldLocation.longitude = java.lang.Double.parseDouble(com.distance_tracker.km.AppPreferences.getLng(this))
            val distanceInMeters = (currentLocation?.distanceTo(oldLocation)?.div(1000)?.times(0.62))?.toFloat() //in miles
            distanceView.text = distanceInMeters?.let { java.lang.Float.toString(it) } + " mi"
        } catch (e: Exception) {
            e.message
        }

    }
}

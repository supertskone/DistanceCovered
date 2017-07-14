package com.distance_tracker.km

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by EXLRT-Nemanja on 7/7/2017.
 */

object AppPreferences {
    fun setLat(context: Context, location: String) {
        val sharedPreferences = context.getSharedPreferences("Upwork", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("latitude", location)
        editor.commit()
    }

    fun getLat(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("Upwork", Context.MODE_PRIVATE)
        val location = sharedPreferences.getString("latitude", "")
        return location
    }

    fun setLng(context: Context, location: String) {
        val sharedPreferences = context.getSharedPreferences("Upwork", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("longitude", location)
        editor.commit()
    }

    fun getLng(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("Upwork", Context.MODE_PRIVATE)
        val location = sharedPreferences.getString("longitude", "")
        return location
    }
}

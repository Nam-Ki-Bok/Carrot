package com.example.carrot

import android.app.Activity
import android.content.Context

class Util {
    companion object {
        fun readToken(activity: Activity) : String {
            val sharedPref = activity.getSharedPreferences(activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
            return sharedPref.getString("token", "")!!
        }

        fun readToken(context: Context) : String {
            val sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
            return sharedPref.getString("token", "")!!
        }
    }
}
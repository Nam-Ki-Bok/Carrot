package com.example.carrot

import android.app.Activity
import android.content.Context
import com.example.carrot.Response.User
import com.google.gson.GsonBuilder

class Util {
    companion object {
        private val gson = GsonBuilder().create()

        //유저 정보 가져오기
        fun readUser(activity: Activity): User? {
            val sharedPref = activity.getSharedPreferences("Carrot", Context.MODE_PRIVATE)
            val strUser = sharedPref.getString("strUser", "")

            return if (strUser == "") {
                null
            } else {
                gson.fromJson(strUser, User::class.java)
            }
        }

        fun readUser(context: Context) : User? {
            val sharedPref = context.getSharedPreferences("Carrot", Context.MODE_PRIVATE)
            val strUser = sharedPref.getString("strUser", "")

            return if (strUser == "") {
                null
            } else {
                gson.fromJson(strUser, User::class.java)
            }
        }

        fun readToken(activity: Activity): String {
            val sharedPref = activity.getSharedPreferences(
                activity.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
            )
            return sharedPref.getString("token", "")!!
        }

        fun readToken(context: Context): String {
            val sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
            )
            return sharedPref.getString("token", "")!!
        }
    }
}
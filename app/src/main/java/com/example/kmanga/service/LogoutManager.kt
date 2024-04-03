package com.example.kmanga.service

import android.content.Context
import android.content.Intent
import com.example.kmanga.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LogoutManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun logOut() {
        Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK + Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(this)
        }
    }
}
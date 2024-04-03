package com.example.kmanga.service.shared_preferences

import android.content.Context
import androidx.core.content.edit
import com.example.kmanga.R
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SPImpl @Inject constructor(
    @ApplicationContext context: Context,
) : SPService {

    private val sharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preference_file_name),
        Context.MODE_PRIVATE
    )

    override fun clear() {
        sharedPreferences.edit {
            clear()
            apply()
        }
    }

    override fun storeKeyPair(key: SPKey, value: String) {
        sharedPreferences.edit {
            putString(key.value, value)
            apply()
        }
    }

    override fun retrieveValue(key: SPKey, default: String): String {
        return sharedPreferences.getString(key.value, default) ?: default
    }

    override fun storeObject(key: SPKey, obj: Any) {
        val gson = Gson()
        val data = gson.toJson(obj)
        sharedPreferences.edit {
            putString(key.value, data)
            apply()
        }
    }

    override fun <T> retrieveObject(key: SPKey, classOfT: Class<T>): T? {
        sharedPreferences.getString(key.value, "")?.let { data ->
            val gson = Gson()
            return gson.fromJson(data, classOfT)
        }
        return null
    }
}
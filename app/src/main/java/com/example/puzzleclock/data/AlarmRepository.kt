package com.example.puzzleclock.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val Context.dataStore by preferencesDataStore(name = "alarms_prefs")

class AlarmRepository(private val context: Context) {
    private val ALARM_KEY = stringPreferencesKey("alarms")

    suspend fun saveAlarms(alarms: List<Alarm>) {
        context.dataStore.edit { prefs ->
            val json = Json.encodeToString(alarms)
            prefs[ALARM_KEY] = json
        }
    }

    fun getAlarms(): Flow<List<Alarm>> {
        return context.dataStore.data.map { prefs ->
            val json = prefs[ALARM_KEY] ?: "[]"
            Json.decodeFromString(json)
        }
    }
}

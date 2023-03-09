package dev.shreyansh.dice.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

object PREFS{
    val THEME_KEY = stringPreferencesKey("THEME_KEY")
    val GAME_MODE = stringPreferencesKey("GAME_MODE")
}

class DiceDataStore private constructor(context: Context) {

    companion object {

        @Volatile
        private var INSTANCE: DiceDataStore? = null

        fun getInstance(context: Context): DiceDataStore {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = DiceDataStore(context)
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DiceDS")
    private val dataStore = context.dataStore



    //theme prefs
    suspend fun setAppTheme(theme: String) {
        dataStore.edit { pref ->
            pref[PREFS.THEME_KEY] = theme
        }
    }
    fun getAppTheme(): Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { pref ->
            val theme = pref[PREFS.THEME_KEY] ?: "System Default"
            theme
        }

    //game prefs
    suspend fun setGameMode(mode: String) {
        dataStore.edit { pref ->
            pref[PREFS.GAME_MODE] = mode
        }
    }
    fun getGameMode(): Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { pref ->
            val mode = pref[PREFS.GAME_MODE] ?: "None"
            mode
        }
}
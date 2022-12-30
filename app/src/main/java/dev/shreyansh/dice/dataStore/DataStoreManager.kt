package dev.shreyansh.dice.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreManager(context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "gameMode")
    private val dataStore = context.dataStore

    companion object {
        val GAME_MODE_KEY = stringPreferencesKey("GAME_MODE_KEY")
        val IS_GAME_MODE_SELECTION_COMPLETE = booleanPreferencesKey("IS_GAME_MODE_SELECTION_COMPLETE")

    }

    suspend fun setGameMode(gameMode: String) {
        dataStore.edit { pref ->
            pref[GAME_MODE_KEY] = gameMode
        }
    }
    suspend fun setIsGameModeSelectionComplete(complete: Boolean) {
        dataStore.edit { pref ->
            pref[IS_GAME_MODE_SELECTION_COMPLETE] = complete
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
            val gameMode = pref[GAME_MODE_KEY] ?: ""
            gameMode
        }

    fun getIsGameModeSelectionComplete(): Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { pref ->
            val gameMode = pref[IS_GAME_MODE_SELECTION_COMPLETE] ?: false
            gameMode
        }
}
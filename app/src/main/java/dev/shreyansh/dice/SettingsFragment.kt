package dev.shreyansh.dice

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceFragmentCompat
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.dice.viewModel.DiceViewModel

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() , SharedPreferences.OnSharedPreferenceChangeListener {

    private val viewModel: DiceViewModel by activityViewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        when(key){
            "THEME_KEY" -> {
                viewModel.setAppTheme(sharedPreferences.getString("THEME_KEY", "System Default") ?:  "System Default")
                when(sharedPreferences.getString("THEME_KEY", "System Default")){
                    "Light Mode" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    "Dark Mode" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    "System Default" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
            "GAME_MODE" -> {
                viewModel.setGameMode(sharedPreferences.getString("GAME_MODE_KEY", "one") ?:  "one")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }
}
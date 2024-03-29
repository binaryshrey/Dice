package dev.shreyansh.dice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyansh.dice.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val topLevelDestination = mutableSetOf<Int>()
        topLevelDestination.add(R.id.introFragment)
        topLevelDestination.add(R.id.prefsFragment)
        topLevelDestination.add(R.id.boardOneFragment)
        topLevelDestination.add(R.id.boardTwoFragment)
        topLevelDestination.add(R.id.boardThreeFragment)
        topLevelDestination.add(R.id.boardFourFragment)

        val appBarConfiguration : AppBarConfiguration = AppBarConfiguration.Builder(topLevelDestination).build()

        val navController = this.findNavController(R.id.nav_host_fragment_container)
        NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp()
    }
}
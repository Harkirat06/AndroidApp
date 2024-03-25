package dadm.hsingh.quotationshake.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import dadm.hsingh.quotationshake.R
import dadm.hsingh.quotationshake.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navigationView as NavigationBarView
        val navController = binding.fragmentContainerView.getFragment<NavHostFragment>().navController
        binding.navigationView.setupWithNavController(navController)
        setSupportActionBar(binding.materialToolbar)
        val appBarConfiguration = AppBarConfiguration.Builder(
            setOf(
                R.id.newQuotationFragment,
                R.id.favouritesFragment,
                R.id.settingsFragment
            )
        ).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}
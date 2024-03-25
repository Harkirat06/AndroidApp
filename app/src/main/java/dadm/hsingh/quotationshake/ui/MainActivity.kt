package dadm.hsingh.quotationshake.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import dadm.hsingh.quotationshake.R
import dadm.hsingh.quotationshake.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), MenuProvider {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navigationView as NavigationBarView
        navController = binding.fragmentContainerView.getFragment<NavHostFragment>().navController
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
        addMenuProvider(this)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_about, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return if (menuItem.itemId == R.id.aboutDialogFragment) {
            navController.navigate(R.id.aboutDialogFragment)
            true
        } else {
            false
        }
    }
}
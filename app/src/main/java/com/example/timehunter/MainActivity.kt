package com.example.timehunter

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity :  AppCompatActivity() {
    private lateinit var appBarConfig: AppBarConfiguration
    private  lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        val toolbar =  findViewById<Toolbar>(R.id.toolbar)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val topViews = setOf(R.id.mainFragment, R.id.groupsPageFragment)

        appBarConfig = AppBarConfiguration(topViews, drawer_layout)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController,appBarConfig)

        bottomNavigationView.setupWithNavController(navController)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{_, dest, _ ->
            if (dest.id == R.id.createGroupFragment){
                bottomNavigationView.visibility = View.GONE
            }else{
                bottomNavigationView.visibility = View.VISIBLE
            }

            toolbar.title=""
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}


package com.saidov.cookbook.modules.main.ui.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.saidov.cookbook.R
import com.saidov.cookbook.core.activity.BaseActivity
import com.saidov.cookbook.core.callback.OnToolBarChangedListener
import com.saidov.cookbook.modules.main.category.ui.view.CategoryFragment
import com.saidov.cookbook.modules.main.favorites.FavoritesFragment
import com.saidov.cookbook.modules.main.history.HistoryFragment
import com.saidov.cookbook.modules.main.settings.view.SettingsFragment

class MainActivity : BaseActivity(), SearchView.OnQueryTextListener,
    BottomNavigationView.OnNavigationItemSelectedListener, OnToolBarChangedListener {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        onNavigationItemSelected(bottomNavigationView.menu.getItem(0))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_action_bar,menu)
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.menu_action_bar, menu)
//        val menuItem: MenuItem? = menu.findItem(R.id.menu_search)
//        val view: View? = menuItem?.actionView
//        searchView = view as SearchView
//        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_category -> {
                replaceFragment(CategoryFragment.newInstance(listener = this))
            }
            R.id.menu_history -> {
                replaceFragment(HistoryFragment.newInstance(listener = this))
            }
            R.id.menu_favorite -> {
                replaceFragment(FavoritesFragment.newInstance(listener = this))
            }
            R.id.menu_settings -> {
                replaceFragment(SettingsFragment.newInstance(listener = this))
            }
        }
        return true
    }

    private fun replaceFragment(fragment: Fragment?) {
        val transaction = supportFragmentManager.beginTransaction()
        fragment?.let {
            transaction.replace(R.id.fragmentContainerView, it, javaClass.simpleName)
        }
        transaction.commit()
    }

    override fun setToolbarName(title: String) {
        supportActionBar?.title = title
    }


    override fun setToolBarBackVisibility(status: Boolean) {
        TODO("Not yet implemented")
    }

    override fun clearToolBar() {
        TODO("Not yet implemented")
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        TODO("Not yet implemented")
    }
}
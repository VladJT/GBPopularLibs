package jt.projects.gbpopularlibs

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import jt.projects.gbpopularlibs.databinding.ActivityMainBinding
import jt.projects.gbpopularlibs.ui.CounterFragment
import jt.projects.gbpopularlibs.ui.SettingsFragment
import jt.projects.gbpopularlibs.ui.utils.showFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CounterFragment.newInstance())
                .commitNow()
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            onOptionsItemSelected(item)
            // true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bottom_view_counters -> {
                showFragment(CounterFragment.newInstance())
            }
            R.id.bottom_view_settings -> {
                showFragment(SettingsFragment.newInstance())
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
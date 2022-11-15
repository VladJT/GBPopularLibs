package jt.projects.gbpopularlibs

import android.os.Bundle
import android.view.MenuItem
import jt.projects.gbpopularlibs.databinding.ActivityMainBinding

import jt.projects.gbpopularlibs.presenter.CounterPresenter
import jt.projects.gbpopularlibs.ui.interfaces.CounterView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class MainActivity : MvpAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            //    showFragment(CounterFragment.newInstance())
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            onOptionsItemSelected(item)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bottom_view_counters -> {
                //     showFragment(CounterFragment.newInstance())
            }
            R.id.bottom_view_settings -> {
                //    showFragment(SettingsFragment.newInstance())
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
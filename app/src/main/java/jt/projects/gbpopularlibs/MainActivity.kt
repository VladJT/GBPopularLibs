package jt.projects.gbpopularlibs

import android.os.Bundle
import android.view.MenuItem
import jt.projects.gbpopularlibs.databinding.ActivityMainBinding

import jt.projects.gbpopularlibs.presenter.CounterPresenter
import jt.projects.gbpopularlibs.ui.CounterView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class MainActivity : MvpAppCompatActivity(), CounterView {
    private lateinit var binding: ActivityMainBinding

    private val presenter by moxyPresenter { CounterPresenter() }

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

        binding.btnCounter1.setOnClickListener { presenter.counter1Click() }
        binding.btnCounter2.setOnClickListener { presenter.counter2Click() }
        binding.btnCounter3.setOnClickListener { presenter.counter3Click() }
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

    override fun setButton1Text(text: String) {
        binding.btnCounter1.text = text
    }

    override fun setButton2Text(text: String) {
        binding.btnCounter2.text = text
    }

    override fun setButton3Text(text: String) {
        binding.btnCounter3.text = text
    }
}
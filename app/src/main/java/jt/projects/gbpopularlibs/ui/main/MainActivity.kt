package jt.projects.gbpopularlibs.ui.main

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.R
import jt.projects.gbpopularlibs.databinding.ActivityMainBinding
import jt.projects.gbpopularlibs.presenter.main.MainPresenter
import jt.projects.gbpopularlibs.ui.cicerone.AndroidScreens
import jt.projects.gbpopularlibs.ui.counters_mvvm.CounterMVVMActivity
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    val navigator = AppNavigator(this, R.id.fragment_container)
    private val screens = AndroidScreens()
    val presenter by moxyPresenter { MainPresenter(this.supportFragmentManager, screens) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            //    showScreen(screens.users())
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            onOptionsItemSelected(item)
        }

        checkNetwork()
    }

    private fun checkNetwork() {
    }

    /**
     * Навигатор отсоединяется в onPause и присоединяется в onResumeFragments, чтобы при переходе в
    другой Activity, который тоже может иметь свой навигатор, вызовы передавались навигатору нового
    открытого Activity. Новый открытый Activity также присоединится при запуске и отсоединится в
    onPause и т. д.
    Используется именно onResumeFragments, а не onResume. Это делается потому, что в момент
    onResume некоторые фрагменты Activity могут ещё не достичь своих onResume. Иногда это
    приводит к состоянию, в котором запрещены транзакции фрагментов, а попытки навигации могут
    привести к IllegalStateException. Во время реализации onResumeFragments все фрагменты уже
    восстановлены и находятся в правильном состоянии.
     */
    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            showExitDialog()
        } else {
            supportFragmentManager.fragments.forEach {
                if (it is BackButtonListener && it.backPressed()) {
                    return
                }
            }
            presenter.backClicked()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bottom_view_users -> {
                presenter.showScreen(screens.users())
            }
            R.id.bottom_view_counters -> {
                presenter.showScreen(screens.countersMvp())
            }
            R.id.bottom_view_countersVM -> {
                val myIntent = Intent(this, CounterMVVMActivity::class.java)
                startActivity(myIntent)
            }
            R.id.bottom_view_rxjava -> {
                presenter.showScreen(screens.rxjava())
            }
            R.id.bottom_view_settings -> {
                presenter.showScreen(screens.settings())
            }
        }
        return true
    }

    private fun showExitDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Выход")
            .setMessage("Вы уверены, что хотите выйти?")
            .setPositiveButton(
                android.R.string.yes
            ) { _, _ -> finish() }//иначе Activity переходит в "спящий режим" и остается в стеке
            .setNegativeButton(android.R.string.no, null)
            .setIcon(R.drawable.ic_baseline_exit_to_app_24)
            .show()
    }
}
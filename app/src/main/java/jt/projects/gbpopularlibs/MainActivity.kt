package jt.projects.gbpopularlibs

import android.os.Bundle
import android.view.MenuItem
import com.github.terrakok.cicerone.androidx.AppNavigator
import jt.projects.gbpopularlibs.databinding.ActivityMainBinding
import jt.projects.gbpopularlibs.presenter.MainPresenter

import jt.projects.gbpopularlibs.ui.AndroidScreens
import jt.projects.gbpopularlibs.ui.interfaces.BackButtonListener
import jt.projects.gbpopularlibs.ui.interfaces.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    val navigator = AppNavigator(this, R.id.fragment_container)
    val presenter by moxyPresenter { MainPresenter(App.instance.router, AndroidScreens()) }

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
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
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
package jt.projects.gbpopularlibs.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.R
import jt.projects.gbpopularlibs.core.interfaces.BackButtonListener
import jt.projects.gbpopularlibs.core.utils.INetworkStatus
import jt.projects.gbpopularlibs.databinding.ActivityMainBinding
import jt.projects.gbpopularlibs.presenter.main.MainPresenter
import jt.projects.gbpopularlibs.ui.counters_mvvm.CounterMVVMActivity
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navHolder: NavigatorHolder

    @Inject
    lateinit var networkStatus: INetworkStatus

    private val navigator = AppNavigator(this, R.id.fragment_container)

    val presenter by moxyPresenter {
        MainPresenter(this.supportFragmentManager).apply {
            App.instance.appComponent.inject(this)
        }
    }

    //Определим переменную типа BottomSheetBehaviour. В качестве generic передаём тип контейнера
    //нашего BottomSheet. Этот instance будет управлять нашей нижней панелью.
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            onOptionsItemSelected(item)
        }

        bottomSheetBehavior =
            BottomSheetBehavior.from(findViewById<ConstraintLayout>(R.id.bottom_sheet_container))
                .apply { state = BottomSheetBehavior.STATE_COLLAPSED }

        // для ошибки с dispose (UndeliverableException)!!
        RxJavaPlugins.setErrorHandler {
            binding.tvNetworkStatus.text = "RxJavaPlugins error: ${it.message}"
        }

        App.instance.appComponent.inject(this)

        networkStatus.isOnline().subscribe() {
            binding.tvNetworkStatus.text = "⚡ Internet available: $it"
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
        navHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navHolder.removeNavigator()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            showExitDialog()
        } else {
            supportFragmentManager.fragments.forEach { f ->
                if (f is BackButtonListener && f.backPressed()) {
                    return
                }
            }
            presenter.backClicked()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bottom_view_users -> {
                presenter.showUsers()
            }
            R.id.bottom_view_counters -> {
                presenter.showCountersMvp()
            }
            R.id.bottom_view_countersVM -> {
                val myIntent = Intent(this, CounterMVVMActivity::class.java)
                startActivity(myIntent)
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
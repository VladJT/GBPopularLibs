package jt.projects.gbpopularlibs.ui.counters_mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.terrakok.cicerone.Router
import jt.projects.gbpopularlibs.core.interfaces.BackButtonListener
import jt.projects.gbpopularlibs.databinding.FragmentCountersMvpBinding
import jt.projects.gbpopularlibs.presenter.counters.CounterPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.inject

class CounterMVPFragment : MvpAppCompatFragment(), BackButtonListener, CounterView {

    private var _binding: FragmentCountersMvpBinding? = null
    private val binding get() = _binding!!

    private val router : Router by inject()

    val presenter by moxyPresenter { CounterPresenter(router) }

    companion object {
        fun newInstance() = CounterMVPFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountersMvpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCounter1.setOnClickListener { presenter.counter1Click() }
        binding.btnCounter2.setOnClickListener { presenter.counter2Click() }
        binding.btnCounter3.setOnClickListener { presenter.counter3Click() }

    }

    override fun setFirstCounterText(text: String) {
        binding.btnCounter1.text = text
    }

    override fun setSecondCounterText(text: String) {
        binding.btnCounter2.text = text
    }

    override fun setThirdCounterText(text: String) {
        binding.btnCounter3.text = text
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
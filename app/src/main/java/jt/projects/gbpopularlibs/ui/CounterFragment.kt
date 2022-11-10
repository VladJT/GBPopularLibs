package jt.projects.gbpopularlibs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jt.projects.gbpopularlibs.databinding.FragmentCountersBinding
import jt.projects.gbpopularlibs.presenter.CounterPresenter

class CounterFragment : Fragment(), CounterView {

    private var _binding: FragmentCountersBinding? = null
    private val binding get() = _binding!!
    private val presenter = CounterPresenter(this)

    companion object {
        fun newInstance() = CounterFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCounter1.setOnClickListener{presenter.counter1Click()}
        binding.btnCounter2.setOnClickListener{presenter.counter2Click()}
        binding.btnCounter3.setOnClickListener{presenter.counter3Click()}
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
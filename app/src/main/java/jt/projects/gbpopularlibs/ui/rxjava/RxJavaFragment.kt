package jt.projects.gbpopularlibs.ui.rxjava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jt.projects.gbpopularlibs.databinding.FragmentRxjavaBinding
import jt.projects.gbpopularlibs.rxjava.Operators
import jt.projects.gbpopularlibs.rxjava.Sources
import jt.projects.gbpopularlibs.viewmodel.CounterIViewModel

class RxJavaFragment : Fragment() {
    private var _binding: FragmentRxjavaBinding? = null
    private val binding get() = _binding!!

    private var vmOperators = Operators()
    private var vmSources = Sources()

    companion object {
        fun newInstance() = RxJavaFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRxjavaBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vmOperators.log.observe(viewLifecycleOwner) {
            binding.tvLog.text = it
        }

        binding.btnOperators.setOnClickListener { vmOperators.exec() }
        binding.btnSources.setOnClickListener { vmSources.exec() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
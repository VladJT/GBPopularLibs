package jt.projects.gbpopularlibs.ui.rxjava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jt.projects.gbpopularlibs.databinding.FragmentRxjavaBinding
import jt.projects.gbpopularlibs.rxjava.CompositeDisposableEx
import jt.projects.gbpopularlibs.rxjava.Flowable
import jt.projects.gbpopularlibs.rxjava.Operators
import jt.projects.gbpopularlibs.rxjava.Sources
import jt.projects.gbpopularlibs.utils.NetworkStatus

class RxJavaFragment : Fragment() {
    private var _binding: FragmentRxjavaBinding? = null
    private val binding get() = _binding!!

    private var vmOperators = Operators()
    private var vmSources = Sources()
    private var networkStatus: NetworkStatus? = null


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

        binding.btnOperators.setOnClickListener { vmOperators.exec() }
        binding.btnSources.setOnClickListener { vmSources.exec() }
        binding.btnFlowable.setOnClickListener { Flowable.BackPressure().exec() }
        binding.btnCompDisp.setOnClickListener { CompositeDisposableEx().execComposite() }

        networkStatus = NetworkStatus(requireContext())
        val subject = networkStatus!!.status()

        subject
            .subscribe({
                requireActivity().runOnUiThread { binding.tvLog.text = "Network: $it" }

            }, {
                requireActivity().runOnUiThread { binding.tvLog.text = "onError: ${it.message}" }
            })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
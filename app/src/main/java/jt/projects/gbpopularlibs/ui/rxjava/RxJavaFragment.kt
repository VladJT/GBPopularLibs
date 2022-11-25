package jt.projects.gbpopularlibs.ui.rxjava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jt.projects.gbpopularlibs.data.rxjava.Operators
import jt.projects.gbpopularlibs.databinding.FragmentRxjavaBinding

class RxJavaFragment : Fragment() {
    private var _binding: FragmentRxjavaBinding? = null
    private val binding get() = _binding!!

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
        Operators().exec()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
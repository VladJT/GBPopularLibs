package jt.projects.gbpopularlibs.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.databinding.FragmentUserCardBinding
import jt.projects.gbpopularlibs.presenter.profile.UserCardPresenter
import jt.projects.gbpopularlibs.ui.main.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserCardFragment() : MvpAppCompatFragment(), UserCardView, BackButtonListener {
    private var _binding: FragmentUserCardBinding? = null
    private val binding get() = _binding!!

    val presenter by moxyPresenter {
        UserCardPresenter(App.instance.router)
    }

    companion object {
        fun newInstance() = UserCardFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonBack.setOnClickListener { presenter.backPressed() }
    }

    override fun backPressed() = presenter.backPressed()

    override fun showLogin(text: String) {
        binding.textViewLogin.text = text
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
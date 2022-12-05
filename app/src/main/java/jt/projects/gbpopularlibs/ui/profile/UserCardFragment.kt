package jt.projects.gbpopularlibs.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import jt.projects.gbpopularlibs.databinding.FragmentUserCardBinding
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.presenter.profile.UserCardPresenter
import jt.projects.gbpopularlibs.ui.main.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserCardFragment(val currentUser : UserEntity) : MvpAppCompatFragment(), UserCardView, BackButtonListener {
    private var _binding: FragmentUserCardBinding? = null
    private val binding get() = _binding!!

    val presenter by moxyPresenter { UserCardPresenter() }

    companion object {
        fun newInstance() = UserCardFragment(currentUser : User)
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

    override fun showUserProfile(user: UserEntity) {
        binding.tvLogin.text = user.login
        binding.tvUid.text = user.id.toString()
        user.avatar_url?.let { binding.imageViewUserPhoto.load(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
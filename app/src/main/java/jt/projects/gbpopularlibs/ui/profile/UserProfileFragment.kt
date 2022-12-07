package jt.projects.gbpopularlibs.ui.profile

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import coil.load
import jt.projects.gbpopularlibs.databinding.FragmentUserProfileBinding
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.presenter.profile.UserCardPresenter
import jt.projects.gbpopularlibs.ui.main.BackButtonListener
import jt.projects.gbpopularlibs.utils.USER_ENTITY_BUNDLE_KEY
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class UserProfileFragment() : MvpAppCompatFragment(), UserProfileView,
    BackButtonListener {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    val presenter by moxyPresenter {
        UserCardPresenter(
            this.arguments?.getParcelable(
                USER_ENTITY_BUNDLE_KEY,
                UserEntity::class.java
            ) ?: UserEntity("no_name")
        )
    }

    companion object {
        fun newInstance() = UserProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
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
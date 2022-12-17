package jt.projects.gbpopularlibs.ui.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import coil.load
import com.google.android.material.snackbar.Snackbar
import jt.projects.gbpopularlibs.databinding.FragmentUserProfileBinding
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.core.interfaces.BackButtonListener
import jt.projects.gbpopularlibs.presenter.profile.UserProfilePresenter
import jt.projects.gbpopularlibs.core.utils.USER_ENTITY_BUNDLE_KEY
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UserProfileFragment : MvpAppCompatFragment(), UserProfileView,
    BackButtonListener {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    val presenter by moxyPresenter { UserProfilePresenter(getUserEntity()) }

    private fun getUserEntity(): UserEntity {
        val user = this.arguments?.getParcelable(USER_ENTITY_BUNDLE_KEY) as? UserEntity
        return user ?: UserEntity("no_name")
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

    override fun showUserRepos(text: String) {
        binding.tvRepos.text = text
    }

    override fun showLoading(isLoading: Boolean) {
        binding.uvLoadingBar.isVisible = isLoading
    }

    override fun showInfo(text: String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        presenter.clear()
    }
}
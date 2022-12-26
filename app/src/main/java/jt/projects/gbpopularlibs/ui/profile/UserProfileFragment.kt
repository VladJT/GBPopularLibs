package jt.projects.gbpopularlibs.ui.profile


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.core.interfaces.BackButtonListener
import jt.projects.gbpopularlibs.core.utils.USER_ENTITY_BUNDLE_KEY
import jt.projects.gbpopularlibs.databinding.FragmentUserProfileBinding
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.presenter.profile.UserProfilePresenter
import jt.projects.gbpopularlibs.ui.main.MainActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserProfileFragment : MvpAppCompatFragment(), UserProfileView,
    BackButtonListener {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    private var adapter: UserProfileRVAdapter? = null

    val presenter by moxyPresenter {
        UserProfilePresenter(getUserEntity()).apply {
            App.instance.appComponent.inject(this)
        }
    }

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

        (requireActivity() as MainActivity).supportActionBar?.also {
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }


    override fun onPause() {
        super.onPause()
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }


    override fun init() {
        adapter = UserProfileRVAdapter(presenter)
        binding.rvRepos.let { rv ->
            rv.layoutManager = LinearLayoutManager(context)
            rv.adapter = adapter
        }
    }

    override fun backPressed() = presenter.backPressed()

    override fun showUserProfile(user: UserEntity) {
        binding.tvLogin.text = user.login
        binding.tvUid.text = user.id.toString()
        user.avatar_url?.let { binding.imageViewUserPhoto.load(it) }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showLoading(isLoading: Boolean) {
        binding.uvLoadingBar.isVisible = isLoading
    }

    override fun showInfo(text: String) = (requireActivity() as MainActivity).printLog(text)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        presenter.clear()
    }
}
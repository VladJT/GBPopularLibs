package jt.projects.gbpopularlibs.ui.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import jt.projects.gbpopularlibs.databinding.FragmentUsersBinding
import jt.projects.gbpopularlibs.presenter.users.UsersPresenter
import jt.projects.gbpopularlibs.ui.main.BackButtonListener
import jt.projects.gbpopularlibs.utils.DURATION_ITEM_ANIMATOR
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    val presenter by moxyPresenter { UsersPresenter(AndroidSchedulers.mainThread()) }
    var adapter: UsersRVAdapter? = null

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        binding.rvUsers.let { rv ->
            rv.layoutManager = LinearLayoutManager(context)
            rv.adapter = adapter

            // Установим анимацию
            rv.itemAnimator = DefaultItemAnimator().apply {
                addDuration = DURATION_ITEM_ANIMATOR
                changeDuration = DURATION_ITEM_ANIMATOR
                removeDuration = DURATION_ITEM_ANIMATOR
                moveDuration = DURATION_ITEM_ANIMATOR
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showLoading(isLoading: Boolean) {
        binding.uvLoadingBar.isVisible = isLoading
    }

    override fun showInfo(text: String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
package jt.projects.gbpopularlibs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.databinding.FragmentUsersBinding
import jt.projects.gbpopularlibs.model.GithubUsersRepositoryLocalImpl
import jt.projects.gbpopularlibs.presenter.UsersPresenter
import jt.projects.gbpopularlibs.ui.interfaces.BackButtonListener
import jt.projects.gbpopularlibs.ui.interfaces.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    val presenter by moxyPresenter {
        UsersPresenter(
            GithubUsersRepositoryLocalImpl(),
            App.instance.router
        )
    }
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
        binding?.rvUsers?.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
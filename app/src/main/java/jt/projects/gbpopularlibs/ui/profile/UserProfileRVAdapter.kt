package jt.projects.gbpopularlibs.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jt.projects.gbpopularlibs.databinding.ItemRepoBinding
import jt.projects.gbpopularlibs.domain.entities.GhRepoEntity
import jt.projects.gbpopularlibs.presenter.profile.UserProfilePresenter

class UserProfileRVAdapter(val presenter: UserProfilePresenter) :
    RecyclerView.Adapter<UserProfileRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int = presenter.getCount()

    inner class ViewHolder(private val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root), RepoItemView {
        override var pos = -1

        override fun bind(repoEntity: GhRepoEntity) = with(binding) {
            tvRepoName.text = repoEntity.name
            tvForks.text = repoEntity.forksCount.toString()
            tvLanguage.text = repoEntity.language
        }

    }
}
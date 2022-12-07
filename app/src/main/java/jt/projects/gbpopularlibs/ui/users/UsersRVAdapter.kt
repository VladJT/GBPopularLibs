package jt.projects.gbpopularlibs.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jt.projects.gbpopularlibs.domain.entities.UserEntity
import jt.projects.gbpopularlibs.databinding.ItemUserBinding
import jt.projects.gbpopularlibs.presenter.users.IUserListPresenter

class UsersRVAdapter(val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
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

    inner class ViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root), UserItemView {
        override var pos = -1

        override fun bind(userEntity: UserEntity) {
            binding.tvLogin.text = userEntity.login
            binding.tvUid.text = userEntity.id.toString()
            userEntity.avatar_url?.let {
                //    binding.ivAvatar.load(it)
                Glide.with(binding.root.context)
                    .load(it)
                    .into(binding.ivAvatar)

            }
        }

    }
}
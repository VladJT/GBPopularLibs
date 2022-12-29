package jt.projects.gbpopularlibs.presenter.users

import jt.projects.gbpopularlibs.ui.users.IItemView

interface IListPresenter<V : IItemView, T> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
    fun getData(): MutableList<T>
}
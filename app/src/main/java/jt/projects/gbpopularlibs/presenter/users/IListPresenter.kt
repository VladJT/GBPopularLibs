package jt.projects.gbpopularlibs.presenter.users

import jt.projects.gbpopularlibs.ui.users.IItemView


interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}
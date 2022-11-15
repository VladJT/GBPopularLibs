package jt.projects.gbpopularlibs.presenter.interfaces

import jt.projects.gbpopularlibs.ui.interfaces.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}
package jt.projects.gbpopularlibs.model.interfaces

interface CommonCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(e: Throwable)
}
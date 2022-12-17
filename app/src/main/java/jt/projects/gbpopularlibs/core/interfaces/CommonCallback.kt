package jt.projects.gbpopularlibs.core.interfaces

interface CommonCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(e: Throwable)
}
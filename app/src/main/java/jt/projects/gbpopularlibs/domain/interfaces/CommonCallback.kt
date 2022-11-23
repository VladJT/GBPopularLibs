package jt.projects.gbpopularlibs.domain.interfaces

interface CommonCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(e: Throwable)
}
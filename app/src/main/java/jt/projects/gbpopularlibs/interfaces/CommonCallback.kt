package jt.projects.gbpopularlibs.interfaces

interface CommonCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(e: Throwable)
}

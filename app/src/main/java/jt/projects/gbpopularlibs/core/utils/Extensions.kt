package jt.projects.gbpopularlibs.core.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

fun <T> Single<T>.subscribeByDefault(): Single<T> {
    return this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())// для ANDROID
}


fun Disposable.disposeBy(bag: CompositeDisposable) {
    bag.add(this)
}

fun String.addTime(): String = "🗒️ ${SimpleDateFormat("hh:mm:ss").format(Date())} $this"
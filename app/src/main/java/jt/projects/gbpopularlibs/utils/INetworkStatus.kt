package jt.projects.gbpopularlibs.utils

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

public interface INetworkStatus {
    fun isOnline(): Observable<Boolean>
    fun isOnlineSingle(): Single<Boolean>
}

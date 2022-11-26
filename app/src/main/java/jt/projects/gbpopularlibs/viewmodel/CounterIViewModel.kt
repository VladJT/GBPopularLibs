package jt.projects.gbpopularlibs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import jt.projects.gbpopularlibs.App
import jt.projects.gbpopularlibs.data.counters.CountersModel

class CounterIViewModel() : IViewModel {
    val model = CountersModel()
    val countersData: LiveData<List<Int>> = MutableLiveData()
    val singleEventData: LiveData<String> = SingleEventLiveData()

    override fun onRefresh() {
        refreshData()
    }

    private fun refreshData() {
        countersData.postValue(model.getData())
    }

    fun counter1Click() {
        model.next(0)
        singleEventData.postValue("+1") // !!! single event
        refreshData()
    }

    fun counter2Click() {
        model.next(1)
        refreshData()
    }

    fun counter3Click() {
        model.next(2)
        refreshData()
    }

    fun backPressed(): Boolean {
        App.instance.router.exit()
        return true
    }

    // Live Data to MutableLiveData
    private fun <T> LiveData<T>.postValue(value: T) {
        val mutable =
            this as? MutableLiveData<T> ?: throw IllegalStateException("it is not MutableData")
        mutable.postValue(value)
    }

}
package jt.projects.gbpopularlibs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import jt.projects.gbpopularlibs.data.counters.CountersRepository

class CounterViewModel(private val router: Router) : ViewModel(), IViewModel {
    private val model = CountersRepository()
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
        router.exit()
        return true
    }

    // Live Data to MutableLiveData
    private fun <T> LiveData<T>.postValue(value: T) {
        val mutable =
            this as? MutableLiveData<T> ?: throw IllegalStateException("it is not MutableData")
        mutable.postValue(value)
    }

}
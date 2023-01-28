package jt.projects.gbpopularlibs.ui.counters_mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import jt.projects.gbpopularlibs.databinding.FragmentCountersMvvmBinding
import jt.projects.gbpopularlibs.viewmodel.CounterViewModel

class CounterMVVMActivity : AppCompatActivity() {

    private lateinit var binding: FragmentCountersMvvmBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[CounterViewModel::class.java] // переживает создание активити
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentCountersMvvmBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel.countersData.observe(this) {
            renderData(it)
        }
        viewModel.singleEventData.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }

        binding.btnCounter1.setOnClickListener { viewModel.counter1Click() }
        binding.btnCounter2.setOnClickListener { viewModel.counter2Click() }
        binding.btnCounter3.setOnClickListener { viewModel.counter3Click() }
    }

//    private fun extractViewModel(): CounterViewModel {
//        return lastCustomNonConfigurationInstance as? CounterViewModel ?: CounterViewModel()
//    }
//
//    override fun onRetainCustomNonConfigurationInstance(): CounterViewModel {
//        return viewModel
//    }

    fun renderData(data: List<Int>) {
        binding.btnCounter1.text = data[0].toString()
        binding.btnCounter2.text = data[1].toString()
        binding.btnCounter3.text = data[2].toString()
    }
}
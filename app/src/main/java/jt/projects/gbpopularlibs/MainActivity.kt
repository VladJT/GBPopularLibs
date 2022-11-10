package jt.projects.gbpopularlibs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jt.projects.gbpopularlibs.databinding.ActivityMainBinding
import jt.projects.gbpopularlibs.ui.CounterFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CounterFragment.newInstance())
                .commitNow()
        }
    }


}
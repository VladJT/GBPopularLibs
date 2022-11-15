package jt.projects.gbpopularlibs.ui.utils

import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import jt.projects.gbpopularlibs.R


fun AppCompatActivity.showFragment(fragment: Fragment) {
    this.supportFragmentManager
        .beginTransaction()
        .replace(this.findViewById<FrameLayout>(R.id.fragment_container).id, fragment)
        .commit()
}

fun AppCompatActivity.showFragmentWithBS(fragment: Fragment, fragmentTag: String) {
    val f = supportFragmentManager.findFragmentByTag(fragmentTag)
    if (f == null) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                this.findViewById<FrameLayout>(R.id.fragment_container).id,
                fragment,
                fragmentTag
            )
            .addToBackStack("")
            .commit()
    }
}
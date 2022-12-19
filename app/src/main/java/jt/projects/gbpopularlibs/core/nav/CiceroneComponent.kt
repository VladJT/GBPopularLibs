package jt.projects.gbpopularlibs.core.nav

import dagger.Component
import jt.projects.gbpopularlibs.dagger.ExClass
import jt.projects.gbpopularlibs.dagger.ExampleModule
import moxy.MvpAppCompatActivity

@Component(modules = [CiceroneModule::class])
interface CiceroneComponent {
    fun inject(activity: MvpAppCompatActivity)
}


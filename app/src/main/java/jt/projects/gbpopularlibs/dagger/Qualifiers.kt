package jt.projects.gbpopularlibs.dagger

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named


//@Module помечает класс с набором методов, которые отмечаются аннотациями @Provides.
@Module
class ExampleModule() {
    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://www.example.com/"

    @Named("appName")
    @Provides
    fun appName(): String = "myApp"
}


@Component(modules = [ExampleModule::class])
interface ExampleComponent {
    fun inject(exClass: ExClass)
}


class ExClass() {
    //@Inject указывает на метод, конструктор или поле класса, которые (или в которые) надо внедрить
    @Inject
    @field:Named("baseUrl")
    lateinit var st: String

    fun doSomethingWithDependency() {
        println(st)
    }
}


fun main() {
    val daggerComponent = DaggerExampleComponent.builder().build()
    val ex = ExClass()
    daggerComponent.inject(ex)

    ex.doSomethingWithDependency()
}
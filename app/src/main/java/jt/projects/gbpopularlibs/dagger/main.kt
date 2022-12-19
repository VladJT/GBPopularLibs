package jt.projects.gbpopularlibs.dagger

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


/** Без Dagger 2
interface IDependency{
fun doSomething()
}

class ExampleDependency : IDependency{
override fun doSomething() = println("do it")
}

class ExampleClass(private val dependency: IDependency) {
fun doSomethingWithDependency() = dependency.doSomething()
}
 */
interface IDependency {
    fun doSomething()
}

class ExampleDependency : IDependency {
    private var counter = 1
    override fun doSomething() {
        println("counter = $counter")
        counter++
    }
}

//@Module помечает класс с набором методов, которые отмечаются аннотациями @Provides.
@Module
class DependencyModule() {
    //@Provides указывает на метод, который предоставляет (возвращает) зависимость для
    //дальнейшего внедрения.
    @Singleton
    @Provides
    fun dependency(): IDependency = ExampleDependency()
}

//@Component отмечает интерфейс, который позже используется для генерации кода. В нём
//определяется, куда что-либо внедрять, а также методы для прямого доступа к зависимостям
@Singleton
@Component(modules = [DependencyModule::class])
interface MyComponent {
    fun inject(exampleClass: ExampleClass)
}


class ExampleClass() {
    //@Inject указывает на метод, конструктор или поле класса, которые (или в которые) надо внедрить
    @Inject
    lateinit var dependency: IDependency

    fun doSomethingWithDependency() {
        dependency.doSomething()
    }
}


fun main() {
    val appComponent = DaggerMyComponent.builder().build()
    val instance1 = ExampleClass()
    appComponent.inject(instance1)

    val instance2 = ExampleClass()
    appComponent.inject(instance2)

    instance1.doSomethingWithDependency()
    instance1.doSomethingWithDependency()

    instance2.doSomethingWithDependency()
}
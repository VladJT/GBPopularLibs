package jt.projects.gbpopularlibs.rxjava

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import kotlin.random.Random

class Sources {
    fun exec() {
        val observable = Producer()
        val observer = Consumer(observable)
    //    println("\n--execCompletable--")
    //    observer.execCompletable()

    //    println("\n--execSingle--")
   //     observer.execSingle()

        println("\n--execMaybe--")
        observer.execMaybe()
    }

    class Producer {
        fun randomResultOperation(): Boolean {
            Thread.sleep(Random.nextLong(1000))
            return listOf(true, false, true)[Random.nextInt(2)]
        }

        // Completable
        //Источник подходит, когда получать значения не требуется, а нас интересует сам факт завершения
        //какой-либо операции, например, запись в файл или базу данных. Для обработки этого источника
        //используется CompletableObserver, аналогичный Observer, но не имеющий onNext
        fun completable() = Completable.create { emitter ->
            randomResultOperation().let { res ->
                if (res) {
                    emitter.onComplete()
                } else {
                    emitter.onError(RuntimeException("Error"))
                    return@create
                }
            }
        }

        //Single
        //Источник аналогичен Observable, однако может выдать только одно значение. Single идеально
        //подходит для HTTP-запросов, так как всегда ожидается только один ответ от сервера. Получение
        //значения и завершение его работы — одно и то же событие. Вместо onNext и onComplete у его
        //SingleObserver есть только один метод onSuccess, и он терминальный
        fun single() = Single.fromCallable {
            return@fromCallable "Some string value"
        }


        //Maybe
        //Maybe подходит, если нас устраивает как наличие значения, так и его отсутствие.
        //Например, при обработке авторизации с возможностью гостевого доступа. В этом случае нас устроит
        //и наличие, и отсутствие авторизованного пользователя.
        //Можно сказать, что он где-то между Completable и Single. Его MaybeObserver, помимо onError и
        //onSubscribe, имеет методы onSuccess и onComplete. Оба считаются «терминальными», и,
        //соответственно, взаимоисключающими. Если значение есть, то вызывается первый, наоборот — второй
        fun maybe() = Maybe.create { emitter ->
            randomResultOperation().let {
                if (it) {
                    emitter.onSuccess("💻")
                } else {
                    emitter.onComplete()
                    return@create
                }
            }
        }

    }//Producer


    class Consumer(val producer: Producer) {
        fun exec() {
        }

        fun execCompletable() {
            producer.completable()
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                        println("onSubscribe")
                    }

                    override fun onComplete() {
                        println("onComplete")
                    }

                    override fun onError(e: Throwable) {
                        println("onError ${e.message}")
                    }
                })
        }


        fun execSingle() {
            producer.single()
                .map { it + it }
                .subscribe({ s ->
                    println("onSuccess: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        fun execMaybe() {
            producer.maybe()
                .subscribe({ s ->
                    println("onSuccess: $s")
                }, {
                    println("onError: ${it.message}")
                }, {
                    println("onComplete")
                })
        }
    }//Consumer
}

package jt.projects.gbpopularlibs


fun test(){
    val t = Thread {

        var i = 0
        while (true){
            println(i++)
            Thread.sleep(200)
        }

    }
    t.start()
}

fun main() {
    val startTime = System.currentTimeMillis()


    test()

    Thread.sleep(500)
    println("!! --COOL-- !!")
    readln()

}
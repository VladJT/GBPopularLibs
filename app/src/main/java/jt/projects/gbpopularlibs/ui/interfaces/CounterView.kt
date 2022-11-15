package jt.projects.gbpopularlibs.ui.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


/** Стратегии, которые уже есть в Moxy:
    1. AddToEndStrategy — добавит пришедшую команду в конец очереди (используется по
    умолчанию).
    2. AddToEndSingleStrategy — добавит пришедшую команду в конец очереди команд. Если
    команда такого типа уже есть в очереди, то действующая удалится. В большинстве случаев
    подходит именно эта стратегия.
    3. SingleStateStrategy — очистит всю очередь команд, после чего добавит в неё себя.
    4. OneExecutionStateStrategy — команда добавится в очередь и удалится после первого
    выполнения.
    5. SkipStrategy — команда не добавится в очередь и никак её (очередь) не изменит.
 */

@StateStrategyType(AddToEndSingleStrategy::class)
interface CounterView : MvpView
 {
    fun setButton1Text(text: String)
    fun setButton2Text(text: String)
    fun setButton3Text(text: String)
}


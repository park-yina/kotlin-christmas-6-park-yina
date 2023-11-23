package christmas

import eventViewModel.ValidInput

fun main() {
    val input=eventView.Input()
    input.readInputDate()
    input.menuErrorPrintln()
    CalculatorRun().run()
}

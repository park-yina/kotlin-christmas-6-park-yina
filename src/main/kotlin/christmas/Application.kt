package christmas

import eventViewModel.ValidInput

fun main() {
    val input=eventView.Input()
    input.dateErrorPrintln()
    input.menuErrorPrintln()
    CalculatorRun().run()
}

package christmas

import eventViewModel.ValidInput

fun main() {
    val input=eventView.Input()
    val output=eventView.Output()
    val validInput=ValidInput()
    input.dateErrorPrintln()
    input.menuErrorPrintln()

}

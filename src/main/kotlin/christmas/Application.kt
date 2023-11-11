package christmas

import eventViewModel.Calculator
import eventViewModel.ValidInput

fun main() {
    val input=eventView.Input()
    val output=eventView.Output()
    input.dateErrorPrintln()
    input.menuErrorPrintln()
    output.printlnBeforeBenefit()
    Calculator().calculateOriginal(ValidInput().menuMap)
    Calculator().printlnTotalPrice()
}

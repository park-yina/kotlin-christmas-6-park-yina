package christmas

import eventView.Output
import eventViewModel.Calculator

class CalculatorRun {
    private val calculator=Calculator()
    fun run() {
        Output().printlnBenefitPreview()
        Output().printlnInPutMenu()
        calculator.originalPrice()
        calculator.calculateGivewayBenefit()
        calculator.allBenefitCost()
        calculator.resultBenefit()
    }
}
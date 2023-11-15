package christmas

import eventView.Output
import eventViewModel.Calculator

class CalCulatorRun {
    val calulator=Calculator()
    fun run() {
        Output().printlnBenefitPreview()
        Output().printlnInPutMenu()
        calulator.originalPrice()
        calulator.calculateGivewayBenefit()
        calulator.allBenefitCost()
        calulator.resultBenefit()
    }
}
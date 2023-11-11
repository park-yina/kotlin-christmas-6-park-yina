package eventView

import eventViewModel.ValidInput

class Input {
    val validInput=ValidInput()
    private fun HelloMent(){
        println(InputMent.HELLO_USER.message)
    }
    private fun printlnVisitDate(){
        println(InputMent.READ_EXPECTED_VISIT_DATE.message)
    }
    fun dateErrorPrintln(){
        HelloMent()
        printlnVisitDate()
        try{
            validInput.date_Verification()
        }
        catch (e:IllegalArgumentException){
            println(e.message)
            return validInput.date_Verification()
        }
    }
    fun printlnOrderMenu(){
        println(InputMent.ORDER_MENU_MENT.message)
    }
    fun menuErrorPrintln(){
        try{
            validInput.checking_InputData()
        }
        catch (e:IllegalArgumentException){
            println(e.message)
            return validInput.checking_InputData()
        }
        printlnBenefitPreview()
        validInput.printlnInPutMenu()
    }

    fun printlnBenefitPreview(){
        println(InputMent.BENEFIT_PREVIEW.message)
    }

}
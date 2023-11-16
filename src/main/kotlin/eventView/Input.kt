package eventView

import eventViewModel.ValidInput

class Input {
    private val validInput=ValidInput()
    private fun helloMent(){
        println(InputMent.HELLO_USER.message)
    }
    private fun printlnVisitDate(){
        println(InputMent.READ_EXPECTED_VISIT_DATE.message)
    }
    fun dateErrorPrintln():Int{
        helloMent()
        printlnVisitDate()
        val date:Int
        try{
            date=validInput.date_Verification()
        }
        catch (e:IllegalArgumentException){
            println(e.message)
            return validInput.date_Verification()
        }
        return date
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
    }

}
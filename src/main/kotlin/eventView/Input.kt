package eventView

import camp.nextstep.edu.missionutils.Console
import eventViewModel.ValidInput

class Input {
    init{
        println(InputMent.HELLO_USER.message)
    }
    val validInput=ValidInput()
    fun printlnVisitDate(){
        println(InputMent.READ_EXPECTED_VISIT_DATE.message)
    }
    fun dateErrorPrintln(){
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
    
}
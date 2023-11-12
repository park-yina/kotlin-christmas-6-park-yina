package eventView

import eventViewModel.ValidInput

class Output {
    private val validInput=ValidInput()
    fun printlnOrderMenu(){
        println(OutputMent.ORDER_MENU.message)
    }
    fun printlnOrderMenuList(menu:MutableMap<String,Int>){
        menu.forEach{println("${it.key} ${it.value}개")}
    }
    fun printlnBeforeBenefit(){
        println(OutputMent.BEFORE_BENEFIT.message)
    }
    fun printlnTotalPrice(totalPrice:Int){
        println("${totalPrice}원")
    }
}
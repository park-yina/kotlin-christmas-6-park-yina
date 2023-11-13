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
    private fun printlnGivewayMenu(){
        println(OutputMent.GIVEWAY_MENU.message)
    }
    fun printlnGiveWayResult(totalPrice: Int):Boolean{
        printlnGivewayMenu()
        if(totalPrice>=120000){
            println("샴페인 1개")
            return true
        }
        println("없음")
        return false
    }
   private fun printlnBenefitMent(){
        println(OutputMent.BENEFIT_LIST.message)
    }
    fun startDays(date:Int,starDays:List<Int>):Boolean{
        if(starDays.contains(date)){
            return true
        }
        return false
    }
    fun checkingDayType(date:Int,weekEnd:List<Int>):Boolean{
        if(weekEnd.contains(date))
            return true
        return false
    }
    fun printlnBenefitList(benefitList:List<String>):Boolean{
        printlnBenefitMent()
        if(benefitList.isNullOrEmpty()){
            println("없음")
            return false
        }
        benefitList.forEach { println(it)}
        return true
    }
    fun printlnAllBenefitCost(benefitCost:Int){
        println(OutputMent.BENEFIT_COST.message)
        println(String.format("-%,d원", Math.abs(benefitCost)))
    }
}
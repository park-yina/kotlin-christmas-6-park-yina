package eventView

import eventViewModel.ValidInput
import eventViewModel.ValidInput.Companion.benefitPrice
import eventViewModel.ValidInput.Companion.menu
import eventViewModel.ValidInput.Companion.result
import eventViewModel.ValidInput.Companion.totalPrice

class Output {
    val validInput=ValidInput()
    companion object {
        val star="별"
        val tree="트리"
        val santa="산타"
    }
    fun printlnOrderMenu(){
        println(OutputMent.ORDER_MENU.message)
    }
    fun printlnOrderMenuList(){
        menu.forEach{println("${it.key} ${it.value}개")}
    }
    fun printlnBeforeBenefit(){
        println(OutputMent.BEFORE_BENEFIT.message)
    }
    fun printlnTotalPrice(){
        println(OutputMent.WON.message.format(totalPrice))
    }
    private fun printlnGivewayMenu(){
        println(OutputMent.GIVEWAY_MENU.message)
    }
    fun printlnGiveWayResult():Boolean{
        printlnGivewayMenu()
        if(totalPrice>=DiscountPrice.CHANPAGNE_FREE.price){
            println(OutputMent.SERVICE_CHANPAGNE.message)
            return true
        }
        println(OutputMent.NO_ONE.message)
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
            println(OutputMent.NO_ONE.message)
            return false
        }
        benefitList.forEach { println(it)}
        return true
    }
    fun printlnAllBenefitCost(){
        println(OutputMent.BENEFIT_COST.message)
        println(OutputMent.MINUS_WON.message.format(benefitPrice))
    }
    private fun printlnDiscountMent(){
        println(OutputMent.FINAL_AMOUNT.message)
    }
    fun printlnResult(){
        printlnDiscountMent()
        println(OutputMent.WON.message.format(result))
    }
    private fun printlnBadgeMent(){
        println(OutputMent.BADGE.message)
    }
    fun printlnBadge(){
        printlnBadgeMent()
        when{
            benefitPrice in DiscountPrice.STAR_START.price..DiscountPrice.START_END.price ->println(star)
            benefitPrice in DiscountPrice.TREE_START.price..DiscountPrice.TREE_END.price->println(tree)
            benefitPrice>=DiscountPrice.SANTA_START.price->println(santa)
            else->println(OutputMent.NO_ONE.message)
        }
    }
}
package eventView

import eventViewModel.ValidInput.Companion.benefitPrice
import eventViewModel.ValidInput.Companion.menu
import eventViewModel.ValidInput.Companion.result
import eventViewModel.ValidInput.Companion.totalPrice

class Output {
    companion object {
        const val STAR="별"
        const val TREE="트리"
        const val SANTA="산타"
    }
   private fun printlnOrderMenu(){
        println(OutputMent.ORDER_MENU.message)
    }
    private fun printlnOrderMenuList(){
        menu.forEach{println("${it.key} ${it.value}개")}
    }
    fun printlnInPutMenu(){
        Output().printlnOrderMenu()
        Output().printlnOrderMenuList()
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
        if(benefitList.isEmpty()){
            println(OutputMent.NO_ONE.message)
            return false
        }
        benefitList.forEach { println(it)}
        return true
    }
    fun printlnBenefitPreview(){
        println(InputMent.BENEFIT_PREVIEW.message)
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
            benefitPrice in DiscountPrice.STAR_START.price..DiscountPrice.START_END.price ->println(STAR)
            benefitPrice in DiscountPrice.TREE_START.price..DiscountPrice.TREE_END.price->println(TREE)
            benefitPrice>=DiscountPrice.SANTA_START.price->println(SANTA)
            else->println(OutputMent.NO_ONE.message)
        }
    }
}
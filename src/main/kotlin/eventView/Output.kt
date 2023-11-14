package eventView

import eventViewModel.ValidInput

class Output {
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
        println(OutputMent.WON.message.format(totalPrice))
    }
    private fun printlnGivewayMenu(){
        println(OutputMent.GIVEWAY_MENU.message)
    }
    fun printlnGiveWayResult(totalPrice: Int):Boolean{
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
    fun printlnAllBenefitCost(benefitCost:Int){
        println(OutputMent.BENEFIT_COST.message)
        println(String.format("-%,d원", Math.abs(benefitCost)))
    }
    private fun printlnDiscountMent(){
        println(OutputMent.FINAL_AMOUNT.message)
    }
    fun printlnResult(resultCost:Int){
        printlnDiscountMent()
        println(OutputMent.WON.message.format(resultCost))
    }
    private fun printlnBadgeMent(){
        println(OutputMent.BADGE.message)
    }
    fun printlnBadge(benefitCost:Int){
        printlnBadgeMent()
        when{
            benefitCost in DiscountPrice.STAR_START.price..DiscountPrice.START_END.price ->{println("별")}
            benefitCost in DiscountPrice.TREE_START.price..DiscountPrice.TREE_END.price->println("트리")
            benefitCost>=DiscountPrice.SANTA_START.price->{println("산타")}
            else->println(OutputMent.NO_ONE.message)
        }
    }
}
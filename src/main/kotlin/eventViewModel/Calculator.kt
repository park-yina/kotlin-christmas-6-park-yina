package eventViewModel

import eventView.DiscountPrice
import eventView.Output
import eventView.OutputMent
import eventViewModel.ValidInput.Companion.benefitList
import eventViewModel.ValidInput.Companion.benefitPrice
import eventViewModel.ValidInput.Companion.d_DayDiscount
import eventViewModel.ValidInput.Companion.dayType
import eventViewModel.ValidInput.Companion.giveway
import eventViewModel.ValidInput.Companion.inputDay
import eventViewModel.ValidInput.Companion.result
import eventViewModel.ValidInput.Companion.totalPrice
import eventViewModel.ValidInput.Companion.weekEndDiscount
import eventViewModel.ValidInput.Companion.workdayDiscount

class Calculator {
    companion object{
        const val CHRIST_MAS=25
        const val ZERO=0
    }
    fun originalPrice(){
        ValidInput.menuMap.forEach { (menuItem,quantity)->
            val item= ValidInput.menu.entries.find{ (_,items)->items.any{it.first==menuItem}}
            if(item!=null){
                val price=item.value.find{it.first==menuItem}?.second
                if(price!=null){
                    totalPrice +=price*quantity
                }
            }
        }
        Output().printlnBeforeBenefit()
        Output().printlnTotalPrice()
    }

    private fun calculateWeekEndDiscount(){
        ValidInput.menuMap.forEach { (menuItem, quantity) ->
            run {
                val item = ValidInput.menu.entries.find { (_, items) -> items.any { it.first == menuItem } }
                if (item?.key == SerachingKey.MAIN.key) {
                    benefitPrice += DiscountPrice.DAY_DISCOUNT.price * quantity
                    weekEndDiscount += DiscountPrice.DAY_DISCOUNT.price *quantity
                }
            }
        }
    }
    private fun calculateWorkDayDiscount(){
        ValidInput.menuMap.forEach { (menuItem, quantity) ->
            run {
                val item = ValidInput.menu.entries.find { (_, items) -> items.any { it.first == menuItem } }
                if (item?.key == SerachingKey.DESSERT.key) {
                    benefitPrice += DiscountPrice.DAY_DISCOUNT.price * quantity
                    workdayDiscount += DiscountPrice.DAY_DISCOUNT.price *quantity
                }
            }
        }
    }
    private fun giveWayChampagne(){
        giveway = Output().printlnGiveWayResult()
        if(giveway){
            benefitList.add(OutputMent.GIVEWAY_MENT.message)
            benefitPrice +=DiscountPrice.GIVE_WAY.price
        }
    }
    private fun starDay(){
        val getStar=Output().startDays(inputDay,SpecialDay.STAR_DAY.specialDay)
        if(getStar){
            benefitList.add(OutputMent.SPECIAL_DISCOUNT_MENT.message)
            benefitPrice+=DiscountPrice.STAR_DAY.price
        }
    }
    private fun processDayType(){
        dayType=Output().checkingDayType(inputDay,SpecialDay.WEEK_END.specialDay)
        when{
            dayType->{
                calculateWeekEndDiscount()
                if(weekEndDiscount!=ZERO){
                    val weekEndMessage=OutputMent.WEEKEND_DISCOUNT.message.format(weekEndDiscount)
                    benefitList.add(weekEndMessage)
                }
            }
            else->{
                calculateWorkDayDiscount()
                if(workdayDiscount !=ZERO){
                    val workDayMessage=OutputMent.WORKDAY_DISCOUNT.message.format(workdayDiscount)
                    benefitList.add(workDayMessage)
                }
            }
        }
    }
    fun calculateGivewayBenefit(){
        giveWayChampagne()
        starDay()
        processDayType()
        if(inputDay <= CHRIST_MAS){
            christmasDiscount()
            val christmasMessage=OutputMent.CHRISTMAS_DISCOUNT.message.format(d_DayDiscount)
            benefitList.add(christmasMessage)
        }
        Output().printlnBenefitList(benefitList)
    }
    private fun christmasDiscount(){
        val startPay=DiscountPrice.CHRISTMAS_START.price
        val dayCount= CHRIST_MAS-inputDay
        if(dayCount==ZERO)
            d_DayDiscount=startPay
        if(dayCount!= ZERO)
            d_DayDiscount =startPay-(dayCount*DiscountPrice.PER_DISCOUNT.price)
        benefitPrice += d_DayDiscount
    }
    fun allBenefitCost(){
        Output().printlnAllBenefitCost()
    }
    fun resultBenefit(){
        result = totalPrice - benefitPrice
        if(benefitList.contains(OutputMent.GIVEWAY_MENT.message)) {
            result +=DiscountPrice.GIVE_WAY.price
        }
        Output().printlnResult()
        Output().printlnBadge()
    }
}
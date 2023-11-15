package eventViewModel

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
                    benefitPrice += DiscountAmount.DAY_DISCOUNT.amount * quantity
                    weekEndDiscount += DiscountAmount.DAY_DISCOUNT.amount *quantity
                }
            }
        }
    }
    private fun calculateWorkDayDiscount(){
        ValidInput.menuMap.forEach { (menuItem, quantity) ->
            run {
                val item = ValidInput.menu.entries.find { (_, items) -> items.any { it.first == menuItem } }
                if (item?.key == SerachingKey.DESSERT.key) {
                    benefitPrice += DiscountAmount.DAY_DISCOUNT.amount * quantity
                    workdayDiscount += DiscountAmount.DAY_DISCOUNT.amount *quantity
                }
            }
        }
    }
    private fun giveWayChampagne(){
        giveway = Output().printlnGiveWayResult()
        if(giveway){
            benefitList.add(OutputMent.GIVEWAY_MENT.message)
            benefitPrice +=DiscountAmount.GIVE_WAY.amount
        }
    }
    private fun starDay(){
        val getStar=Output().startDays(inputDay,SpecialDay.STAR_DAY.specialDay)
        if(getStar){
            benefitList.add(OutputMent.SPECIAL_DISCOUNT_MENT.message)
            benefitPrice+=DiscountAmount.STAR_DAY.amount
        }
    }
    private fun processDayType(){
        dayType=Output().checkingDayType(inputDay,SpecialDay.WEEK_END.specialDay)
        when{
            dayType->{
                calculateWeekEndDiscount()
                if(weekEndDiscount!=0){
                    val weekEndMessage=OutputMent.WEEKEND_DISCOUNT.message.format(weekEndDiscount)
                    benefitList.add(weekEndMessage)
                }
            }
            else->{
                calculateWorkDayDiscount()
                if(workdayDiscount !=0){
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
        if(inputDay <=25){
            christmasDiscount()
            val christmasMessage=OutputMent.CHRISTMAS_DISCOUNT.message.format(d_DayDiscount)
            benefitList.add(christmasMessage)
        }
        Output().printlnBenefitList(benefitList)
    }
    private fun christmasDiscount(){
        val startPay=DiscountAmount.CHRISTMAS_START.amount
        val dayCount=25-inputDay
        if(dayCount==0)
            d_DayDiscount=startPay
        if(dayCount!=0)
            d_DayDiscount =startPay-(dayCount*DiscountAmount.PER_DISCOUNT.amount)
        benefitPrice += d_DayDiscount
    }
    fun allBenefitCost(){
        Output().printlnAllBenefitCost()
    }
    fun resultBenefit(){
        result = totalPrice - benefitPrice
        if(benefitList.contains(OutputMent.GIVEWAY_MENT.message)) {
            result +=DiscountAmount.GIVE_WAY.amount
        }
        Output().printlnResult()
        Output().printlnBadge()
    }
}
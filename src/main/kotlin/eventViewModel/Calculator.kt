package eventViewModel

import eventView.Output
import eventViewModel.ValidInput.Companion.benefitList
import eventViewModel.ValidInput.Companion.benefitPrice
import eventViewModel.ValidInput.Companion.d_DayDiscount
import eventViewModel.ValidInput.Companion.inputDay
import eventViewModel.ValidInput.Companion.result
import eventViewModel.ValidInput.Companion.totalPrice

class Calculator {
    fun originalPrice():Int{
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
        return totalPrice
    }

    fun calculateWeekEndDiscount(){
        ValidInput.menuMap.forEach { (menuItem, quantity) ->
            run {
                val item = ValidInput.menu.entries.find { (_, items) -> items.any { it.first == menuItem } }
                if (item?.key == "메인") {
                    benefitPrice += ValidInput.dayDiscount * quantity
                    ValidInput.weekEndDiscount += ValidInput.dayDiscount *quantity
                }
            }
        }
    }
    fun calculateWorkDayDiscount(){
        ValidInput.menuMap.forEach { (menuItem, quantity) ->
            run {
                val item = ValidInput.menu.entries.find { (_, items) -> items.any { it.first == menuItem } }
                if (item?.key == "디저트") {
                    benefitPrice += ValidInput.dayDiscount * quantity
                    ValidInput.workdayDisount += ValidInput.dayDiscount *quantity
                }
            }
        }
    }
    fun calculateGivewayBenefit(){
        ValidInput.giveway = Output().printlnGiveWayResult()
        if(ValidInput.giveway){
            benefitList.add("증정 이벤트: -25,000원")
            benefitPrice +=25000
        }
        val getStar= Output().startDays(inputDay, ValidInput.starDays)
        if(getStar){
            benefitList.add("특별 할인: -1,000원")
            benefitPrice +=1000
        }
        ValidInput.dayType = Output().checkingDayType(inputDay, ValidInput.weekEnd)
        if(ValidInput.dayType){
            calculateWeekEndDiscount()
            if(ValidInput.weekEndDiscount !=0){
                val formattedDiscount = String.format("주말 할인 : -%,d원", Math.abs(ValidInput.weekEndDiscount))
                benefitList.add(formattedDiscount)
            }
        }
        if(!ValidInput.dayType){
            calculateWorkDayDiscount()
            if(ValidInput.workdayDisount !=0){
                val formattedDiscount = String.format("평일 할인 : -%,d원", Math.abs(ValidInput.workdayDisount))
                benefitList.add(formattedDiscount)
            }
        }
        if(inputDay <=25){
            christmasDiscount()
            val formattedDiscount = String.format("크리스마스 디데이 할인: -%,d원", Math.abs(d_DayDiscount))
            benefitList.add(formattedDiscount)
        }
        Output().printlnBenefitList(benefitList)
    }
    fun christmasDiscount(){
        val startPay=3400
        var dayCount=25- inputDay
        if(dayCount==0)
            d_DayDiscount =startPay
        if(dayCount!=0)
            d_DayDiscount =startPay-(dayCount*100)
        benefitPrice += d_DayDiscount
    }
    fun allBenefitCost(){
        Output().printlnAllBenefitCost()
    }
    fun resultBenefit(){
        result = totalPrice - benefitPrice
        if(benefitList.contains("증정 이벤트: -25,000원")) {
            result +=25000
        }
        Output().printlnResult()
        Output().printlnBadge()
    }
}
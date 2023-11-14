package eventViewModel

import camp.nextstep.edu.missionutils.Console
import eventView.ErrorMessage
import eventView.Input
import eventView.Output


class ValidInput {
    private val menuMap= mutableMapOf<String,Int>()
    private var totalPrice=0
    private var benefitPrice=0
    var giveway=false
    private var dayDiscount=2023
    var inputDay=0
    var dayType=false
    var weekEndDiscount=0
    var workdayDisount=0
    private var benefitList= mutableListOf<String>()
    private var d_DayDiscount=0

    companion object {
        val menu = mapOf(
            "애피타이저" to listOf(Pair("양송이 수프", 6000), Pair("티파스", 5000), Pair("시저샐러드", 8000)),
            "메인" to listOf(
                Pair("티본스테이크", 55000),
                Pair("바비큐립", 54000),
                Pair("해산물파스타", 35000),
                Pair("크리스마스파스타", 25000)
            ),
            "디저트" to listOf(Pair("초코케이크", 15000), Pair("아이스크림", 5000)),
            "음료" to listOf(Pair("제로콜라", 3000), Pair("레드와인", 60000), Pair("샴페인", 25000))
        )
        val starDays= listOf<Int>(3,10,17,24,25,31)
        val weekEnd=listOf(1,2,8,9,15,16,22,23,29,30)

    }

    fun date_Verification():Int{
        val date = Console.readLine()
        when {
            date.toIntOrNull() == null -> {
                throw IllegalArgumentException(ErrorMessage.INVALID_DATE.message)
            }

            date.toInt() < 1 || date.toInt() > 31 -> {
                throw IllegalArgumentException(ErrorMessage.INVALID_DATE.message)
            }
        }
        Input().printlnOrderMenu()
         inputDay=date.toInt()
        return inputDay
    }
    fun pharsing_InputData(): List<String> {
        val inputMenu = Console.readLine()
        return inputMenu.split(",")
    }

    fun checking_InputData(){
        val input = pharsing_InputData()
        input.forEach {
            if (!it.contains("-")) {
                throw IllegalArgumentException(ErrorMessage.INVALID_MENU_FORM.message)
            }
            val (key, value) = it.split("-")

            if (input.count { item -> item.split("-")[0].equals(key) } > 1) {
                throw IllegalArgumentException(ErrorMessage.DUPLICATE_MENU.message)
            }
            if (menu.none { (_, items) -> items.any { it.first == key } }) {
                throw IllegalArgumentException(ErrorMessage.INVALID_MENU_FORM.message)
            }

            if (value.toIntOrNull() == null) {
                throw IllegalArgumentException(ErrorMessage.INVALID_MENU_FORM.message)
            }
            if (input.all { it.split("-")[0] in menu["음료"]!!.map { it.first } }) {
                throw IllegalArgumentException(ErrorMessage.INVALID_MENU_FORM.message)
            }
            if (input.sumBy { it.split("-")[1].toInt() } > 20) {
                throw IllegalArgumentException(ErrorMessage.OVER_MENU.message)
            }
            menuMap[key] = value.toInt()
        }
    }
    fun printlnInPutMenu(){
        Output().printlnOrderMenu()
        Output().printlnOrderMenuList(menuMap)
    }
    fun originalPrice():Int{
        menuMap.forEach { (menuItem,quantity)->
            val item=menu.entries.find{(_,items)->items.any{it.first==menuItem}}
            if(item!=null){
                val price=item.value.find{it.first==menuItem}?.second
                if(price!=null){
                   totalPrice+=price*quantity
                }
            }
        }
        Output().printlnBeforeBenefit()
        Output().printlnTotalPrice(totalPrice)
        return totalPrice
    }

    fun calculateWeekEndDiscount(){
        menuMap.forEach { (menuItem, quantity) ->
            run {
                val item = menu.entries.find { (_, items) -> items.any { it.first == menuItem } }
                if (item?.key == "메인") {
                    benefitPrice += dayDiscount * quantity
                    weekEndDiscount+=dayDiscount*quantity
                }
            }
        }
    }
    fun calculateWorkDayDiscount(){
        menuMap.forEach { (menuItem, quantity) ->
            run {
                val item = menu.entries.find { (_, items) -> items.any { it.first == menuItem } }
                if (item?.key == "디저트") {
                    benefitPrice += dayDiscount * quantity
                   workdayDisount+=dayDiscount*quantity
                }
            }
        }
    }
    fun calculateGivewayBenefit(){
        giveway=Output().printlnGiveWayResult(totalPrice)
        if(giveway){
            benefitList.add("증정 이벤트: -25,000원")
            benefitPrice+=25000
        }
        val getStar=Output().startDays(inputDay, starDays)
        if(getStar){
            benefitList.add("특별 할인: -1,000원")
            benefitPrice+=1000
        }
        dayType=Output().checkingDayType(inputDay, weekEnd)
            if(dayType){
                calculateWeekEndDiscount()
                if(weekEndDiscount!=0){
                    val formattedDiscount = String.format("주말 할인 : -%,d원", Math.abs(weekEndDiscount))
                    benefitList.add(formattedDiscount)
                }
            }
        if(!dayType){
            calculateWorkDayDiscount()
            if(workdayDisount!=0){
                val formattedDiscount = String.format("평일 할인 : -%,d원", Math.abs(workdayDisount))
                benefitList.add(formattedDiscount)
            }
        }
        if(inputDay<=25){
            christmasDiscount()
            val formattedDiscount = String.format("크리스마스 디데이 할인: -%,d원", Math.abs(d_DayDiscount))
            benefitList.add(formattedDiscount)
        }
        Output().printlnBenefitList(benefitList)
    }
    fun christmasDiscount(){
        val startPay=3400
        var dayCount=25-inputDay
        if(dayCount==0)
            d_DayDiscount=startPay
        if(dayCount!=0)
            d_DayDiscount=startPay-(dayCount*100)
        benefitPrice+=d_DayDiscount
    }
    fun allBenefitCost(){
        Output().printlnAllBenefitCost(benefitPrice)
    }
    fun resultBenefit(){
        var result=totalPrice-benefitPrice
        if(benefitList.contains("증정 이벤트: -25,000원")) {
            result+=25000
        }
        Output().printlnResult(result)
        Output().printlnBadge(benefitPrice)
    }
}
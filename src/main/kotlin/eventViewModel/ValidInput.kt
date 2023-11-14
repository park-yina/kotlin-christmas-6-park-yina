package eventViewModel

import camp.nextstep.edu.missionutils.Console
import eventView.DiscountPrice
import eventView.ErrorMessage
import eventView.Input
import eventView.Output
import eventView.ValidInputDatas


class ValidInput {

    companion object {
        val menu = mapOf(
            "애피타이저" to listOf(Pair("양송이 수프", 6000), Pair("타파스", 5000), Pair("시저샐러드", 8000)),
            "메인" to listOf(
                Pair("티본스테이크", 55000),
                Pair("바비큐립", 54000),
                Pair("해산물파스타", 35000),
                Pair("크리스마스파스타", 25000)
            ),
            "디저트" to listOf(Pair("초코케이크", 15000), Pair("아이스크림", 5000)),
            "음료" to listOf(Pair("제로콜라", 3000), Pair("레드와인", 60000), Pair("샴페인", 25000))
        )
        val starDays= listOf(3,10,17,24,25,31)
        val weekEnd=listOf(1,2,8,9,15,16,22,23,29,30)
        val menuMap= mutableMapOf<String,Int>()
        var totalPrice=0
        var benefitPrice=0
        var giveway=false
        var dayDiscount=2023
        var inputDay=0
        var dayType=false
        var weekEndDiscount=0
        var workdayDisount=0
        var benefitList= mutableListOf<String>()
        var d_DayDiscount=0
        var result=0
    }

    fun date_Verification():Int{
        val date = Console.readLine()
        when {
            date.toIntOrNull() == null -> {
                throw IllegalArgumentException(ErrorMessage.INVALID_DATE.message)
            }

            date.toInt() < ValidInputDatas.VALID_DATE_START.data || date.toInt() > ValidInputDatas.VALID_DATE_END.data -> {
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
        Output().printlnOrderMenuList()
    }
}
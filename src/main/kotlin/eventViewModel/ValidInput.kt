package eventViewModel

import camp.nextstep.edu.missionutils.Console
import eventView.ErrorMessage
import eventView.Input
import eventView.ValidInputDatas
import eventViewModel.ValidInput.Parsing.BAR
import eventViewModel.ValidInput.Parsing.COMMA


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
        val menuMap = mutableMapOf<String, Int>()
        var totalPrice = 0
        var benefitPrice = 0
        var giveway = false
        var inputDay = 0
        var dayType = false
        var weekEndDiscount = 0
        var workdayDiscount = 0
        var benefitList = mutableListOf<String>()
        var d_DayDiscount = 0
        var result = 0
    }

    fun date_Verification(): Int {
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
        inputDay = date.toInt()
        return inputDay
    }

    fun pharsing_InputData(): List<String> {
        val inputMenu = Console.readLine()
        return inputMenu.split(COMMA)
    }

    fun checking_InputData() {
        val input = pharsing_InputData()
        input.forEach {
            findBar(it)
            val (key, value) = it.split(BAR)
            if (input.count { item -> item.split(BAR)[0] == key } > NumberLimit.DUPLICATE.value) {
                throw IllegalArgumentException(ErrorMessage.DUPLICATE_MENU.message)
            }
            if (menu.none { (_, items) -> items.any { it.first == key } }) {
                throw IllegalArgumentException(ErrorMessage.INVALID_MENU_FORM.message)
            }
            convertInt(value)
            if (input.all { it.split("-")[0] in menu[SerachingKey.DRINKING.key]!!.map { it.first } }) {
                throw IllegalArgumentException(ErrorMessage.INVALID_MENU_FORM.message)
            }
            if (input.sumOf { it.split("-")[1].toInt() } > NumberLimit.NUMBER_LIMIT.value) {
                throw IllegalArgumentException(ErrorMessage.OVER_MENU.message)
            }
            menuMap[key] = value.toInt()
        }
    }

    private fun convertInt(value: String) {
        if (value.toIntOrNull() == null) {
            throw IllegalArgumentException(ErrorMessage.INVALID_MENU_FORM.message)
        }
    }

    private fun findBar(it: String) {
        if (!it.contains(BAR)) {
            throw IllegalArgumentException(ErrorMessage.INVALID_MENU_FORM.message)
        }
    }

    private object Parsing {
        const val BAR = "-"
        const val COMMA = ","

    }
}
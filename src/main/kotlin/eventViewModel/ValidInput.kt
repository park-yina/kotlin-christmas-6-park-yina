package eventViewModel

import camp.nextstep.edu.missionutils.Console
import eventView.ErrorMessage
import net.bytebuddy.pool.TypePool.Resolution.Illegal

class ValidInput {
    companion object {
        private val menu = mapOf(
            "애피타이저" to listOf(Pair("양송이 수프", 6000), Pair("티파스", 5000), Pair("시저샐러드", 8000)),
            "메인" to listOf(
                Pair("티본스테이크", 55000),
                Pair("바비큐립", 54000),
                Pair("해물파스타", 35000),
                Pair("크리스마스파스타", 25000)
            ),
            "디저트" to listOf(Pair("초코케이크", 15000), Pair("아이스크림", 5000)),
            "음료" to listOf(Pair("제로콜라", 3000), Pair("레드와인", 60000), Pair("샴페인", 25000))
        )

    }

    fun date_Verification() {
        val date = Console.readLine()
        when {
            date.toIntOrNull() == null -> {
                throw IllegalArgumentException(ErrorMessage.INVALID_DATE.message)
            }

            date.toInt() < 1 || date.toInt() > 31 -> {
                throw IllegalArgumentException(ErrorMessage.INVALID_DATE.message)
            }
        }
        eventView.Input().printlnOrderMenu()
    }

    fun pharsing_InputData(): List<String> {
        val inputMenu = Console.readLine()
        return inputMenu.split(",")
    }

    fun checking_InputData() {
        val input = pharsing_InputData()
        input.forEach {
            if (!it.contains("-")) {
                throw IllegalArgumentException(ErrorMessage.INVALID_MENU_FORM.message)
            }
            val (key, value) = it.split("-")

            if (input.count { item -> item.split("-")[0].equals(key) } > 1) {
                throw IllegalArgumentException(ErrorMessage.DUPLICATE_MENU.message)
            }
            if(menu.none{(_,items)->items.any{it.first==key}}){
                throw IllegalArgumentException(ErrorMessage.INVALID_MENU_FORM.message)
            }

            if (value.toIntOrNull() == null) {
                throw IllegalArgumentException(ErrorMessage.INVALID_MENU_FORM.message)
            }
        }
    }
}
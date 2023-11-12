package eventView

enum class OutputMent(val message: String) {
    ORDER_MENU("<주문 메뉴>"),
    BEFORE_BENEFIT("<할인 전 총주문 금액>"),
    GIVEWAY_MENU("<증정메뉴>"),
    BENEFIT_LIST("<혜택 내역>"),
}
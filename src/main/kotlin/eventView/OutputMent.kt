package eventView

enum class OutputMent(val message: String) {
    ORDER_MENU("<주문 메뉴>"),
    BEFORE_BENEFIT("<할인 전 총주문 금액>"),
    GIVEWAY_MENU("<증정메뉴>"),
    BENEFIT_LIST("<혜택 내역>"),
    BENEFIT_COST("<총혜택 금액>"),
    FINAL_AMOUNT("<할인 후 예상 결제 금액>"),
    BADGE("<12월 이벤트 배지>"),
}
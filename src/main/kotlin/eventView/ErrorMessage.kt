package eventView

enum class ErrorMessage(val message: String) {
   INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
   DUPLICATE_MENU("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
   INVALID_MENU_FORM("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
   OVER_MENU("[ERROR]메뉴는 한번에 20개까지만 주문 가능합니다.")
}
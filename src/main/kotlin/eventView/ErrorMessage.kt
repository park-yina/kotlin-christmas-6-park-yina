package eventView

enum class ErrorMessage(val message: String) {
   INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
   DUPLICATE_MENU("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
   INVALID_MENU_FORM("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
}
package eventViewModel

enum class DiscountAmount(val amount:Int) {
    DAY_DISCOUNT(2023),
    GIVE_WAY(25000),
    STAR_DAY(1000),
    CHRISTMAS_START(3400),
    PER_DISCOUNT(100),

}
enum class NumberLimit(val value:Int){
    NUMBER_LIMIT(20),
    DUPLICATE(1),
}
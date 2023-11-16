package eventView

enum class DiscountPrice(val price:Int){
    CHAMPAGNE_FREE(120000),
    STAR_START(5000),
    START_END(9999),
    TREE_START(10000),
    TREE_END(19999),
    SANTA_START(20000),
    DAY_DISCOUNT(2023),
    GIVE_WAY(25000),
    STAR_DAY(1000),
    CHRISTMAS_START(3400),
    PER_DISCOUNT(100),
}
enum class ValidInputDatas(val data:Int){
    VALID_DATE_START(1),
    VALID_DATE_END(31),
}

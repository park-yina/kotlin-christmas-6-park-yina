package eventViewModel

enum class SpecialDay {
    STAR_DAY {
        override val specialDay = listOf(3, 10, 17, 24, 25, 31)
    },
    WEEK_END {
        override val specialDay = listOf(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)
    };

    abstract val specialDay: List<Int>
}

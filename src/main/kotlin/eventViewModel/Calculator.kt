package eventViewModel

class Calculator {
    val validInput=ValidInput()
    val menuMap=validInput.menuMap
    var totalPrice=0
    val menu=ValidInput.Companion.menu
     fun calculateOriginal(menuMap:Map<String,Int>):Int{
        for((category,item)in menu){
            for((menuItem,price)in item){
                menuMap.containsKey(menuItem)
                totalPrice+=price*menuMap[menuItem]!!
            }
        }
        return totalPrice
    }
    fun printlnTotalPrice(){
        println("${totalPrice}원")
    }
}
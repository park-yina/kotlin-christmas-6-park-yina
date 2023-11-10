package eventViewModel

import camp.nextstep.edu.missionutils.Console
import eventView.ErrorMessage
import net.bytebuddy.pool.TypePool.Resolution.Illegal

class ValidInput {
    fun date_Verification(){
        val date= Console.readLine()
        when{
            date.toIntOrNull()==null->{
                throw IllegalArgumentException(ErrorMessage.INVALID_DATE.message)
            }
            date.toInt()<1||date.toInt()>31-> {
                throw IllegalArgumentException(ErrorMessage.INVALID_DATE.message)
            }
        }
        eventView.Input().printlnOrderMenu()
    }
}
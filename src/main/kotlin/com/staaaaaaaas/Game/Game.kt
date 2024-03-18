import com.staaaaaaaas.Config.Config
import com.staaaaaaaas.Tower.Tower
import com.staaaaaaaas.Utils.getUserMove
import com.staaaaaaaas.Visuals.drawTowers

class Game {
    private val towers: List<Tower> = List(Config.numberOfTowers)
    { ind -> Tower(ind + 1).also { if(ind == 0) it.fill() } }
    fun loop() {
        while(!towers.last().solved()) {
            drawTowers(towers)
            getUserMove(towers)
        }
        println("Congratulations! You solved the puzzle!")
        drawTowers(towers)
    }
}
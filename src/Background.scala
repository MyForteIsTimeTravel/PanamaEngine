import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image
import javafx.scene.paint.Color

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Background class
 *  
 *  background holds a default image that is rendered 
 *  across the entire screen. 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
class Background (screenWidth: Int, screenHeight: Int) extends GraphicsObject {
    x        = 0
    y        = 0
    width    = screenWidth
    height   = screenHeight

    var texture = new Image("assets/background.jpg", 100, 100, false, true)

    def render (context: GraphicsContext) = {
        var i = 0
        var j = 0

        for (i <- 0 to screenHeight / 100) {
            for (j <- 0 to screenWidth / 100) {
                context.drawImage(texture, j * 100, i * 100)
            }
        }
    }
}
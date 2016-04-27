import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  DialogueWindow class
 *  
 *  This class renders a GUI element in the bottom left
 *  corner of the screen. It is envisioned that this
 *  will provide space for in-game chat
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
class DialogueWindow (screenWidth: Int, screenHeight: Int) extends GraphicsObject {
    width  = 400
    height = 180
    x = screenWidth - width - 20
    y = screenHeight - height - 20

    val color  = Color.web("rgba(0,0,0,0.7)")

    def render (context: GraphicsContext) = {
        // Window
        context.setFill  (color)
        context.fillRect (getX, getY, getWidth, getHeight)

        // Border
        context.setStroke  (Color.LIGHTGREY)
        context.strokeRect (getX + 4, getY + 4, getWidth - 8, getHeight - 8)
    }
}
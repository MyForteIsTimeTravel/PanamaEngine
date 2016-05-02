package world.worldbuilder

import javafx.scene.paint.Color
import javafx.scene.canvas._

class WorldView extends Canvas {
	val context = this.getGraphicsContext2D
	val color   = Color.web("#9E9E9E")

    context.setFill  (color)
    context.fillRect (0, 0, this.getWidth, this.getHeight)
}
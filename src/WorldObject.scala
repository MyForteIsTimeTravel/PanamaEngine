import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image

class WorldObject (posX: Int, posY: Int, skin: Image) extends GraphicsObject {
	x = posX
	y = posY
	width = skin.getWidth.asInstanceOf[Int]
	height = skin.getHeight.asInstanceOf[Int]

	val texture = skin

	def render (context: GraphicsContext) = {
		context.drawImage(texture, x, y)
	}
}
package world.worlds

import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image

import graphics.GraphicsObject

/** * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  SpriteTest class
 *  
 *  Holds an image representing a world object, has the
 *  ability to render this image based on current state
 *
 *  @author Ryan Needham
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
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
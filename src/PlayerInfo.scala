import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *	PlayerInfo class
 *
 *	This class will hold the players health, energy, 
 *	WhateverTheFuckElse as well as the name and portrait
 *	of the player. 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
class PlayerInfo (player: Sprite) extends GraphicsObject {
	x      = 20
	y      = 20
	width  = 256
	height = 175

	val color  = Color.web("rgba(0,0,0,0.7)")
	val health = new Image("assets/icons/health.png", 16, 14, true, true)
	val energy = new Image("assets/icons/energy.png", 16, 14, true, true)

	/** 
	 *	render
	 *
	 *	@param GraphicsContext
	 *
	 *	Fills a transparent rectangle in the top left with
	 *	a thin white border. Inside the rectangle the players
	 *	portrait and state is outputted. More can easily be
	 *	added when needed
	 */
	def render (context: GraphicsContext) = {
		context.setFill    (color)
		context.fillRect   (getX, getY, getWidth, getHeight)

		context.setStroke  (Color.LIGHTGREY)
		context.strokeRect (getX + 4, getY + 4, getWidth - 8, getHeight - 8)

		context.drawImage  (player.face, x + 16, y + 18)
		context.setFill    (Color.LIGHTGREY)

		context.fillText   ("Health", x + 60, y + 22)
		for (x <- 1 to player.health) {
			context.drawImage (health, x + 60 + (18 * x), y + 26)
		}

		context.fillText   ("Energy", x + 60, y + 54)
		for (x <- 1 to player.energy) {
			context.drawImage (energy, x + 60 + (18 * x), y + 58)
		}
	}
}
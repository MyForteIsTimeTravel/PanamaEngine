import javafx.application.Application
import javafx.animation.AnimationTimer
import javafx.scene.canvas.Canvas
import javafx.scene.layout.StackPane
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.scene.media.MediaPlayer
import javafx.scene.media.Media
import javafx.scene.image.Image
import javafx.scene.ImageCursor
import javafx.scene.paint.Color

import javafx.scene.input.KeyEvent
import javafx.scene.Scene
import javafx.event.EventHandler
import javafx.scene.paint.Color
import java.util.ArrayList

import java.awt.Toolkit
import java.awt.Dimension

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *	SpriteTest class
 *	
 *	This class sets up the canvas, initialises the 
 *	game world and runs the game loop in a similar
 *	manner to the launcher, but this holds the player
 *	in place on a smaller window and outputs debug
 *	information about the sprite.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
class SpriteTest extends Application {

 	/**
 	 *	start
	 *
	 *	@param Stage
	 *
	 *	Determines the host screen size and sets up the canvas
	 *	and scene based on these dimensions. The method then
	 *	initialises the player objects and inserts them into 
	 *	the worldObjects array. The H.U.D is also initialised 
	 *	in a seperate array. When the game objects are set up
	 *	the game loop is started and the stage shown.
	 *	
	 */
	override def start (stage: Stage) = {
		val screenSize   = Toolkit.getDefaultToolkit.getScreenSize
		val SCREENWIDTH  = 256
		val SCREENHEIGHT = 256
		val canvas 		 = new Canvas     (SCREENWIDTH, SCREENHEIGHT)
		val context  	 = canvas.getGraphicsContext2D
		val root   		 = new StackPane
		val scene  		 = new Scene      (root, SCREENWIDTH, SCREENHEIGHT)
		val sprite   	 = new Sprite     (SCREENWIDTH, SCREENHEIGHT, "spriteTestTwo")

		root.getChildren.addAll(canvas)

		/** 
		 *	this defines the start positions for each player.
		 *	this should be dealt with more elegantly.
		 */
		sprite.setX(SCREENWIDTH / 4)
		sprite.setY(SCREENWIDTH / 4)

		val input = new InputHandler (sprite, scene)

		val loop = new AnimationTimer() {
			override def handle(currentNanoTime: Long) = {

				// background
				context.setFill   (Color.web("#9E9E9E"))
				context.fillRect  (0, 0, SCREENWIDTH, SCREENHEIGHT)

				// sprite
				input.checkForInput
				sprite.update     (currentNanoTime)
				sprite.testRender (context)

				// debug
				context.setFill   (Color.WHITE)
				context.fillText  ("time:             " + currentNanoTime, 12, 12)
				context.fillText  ("frame:   	     " + sprite.activeFrame, 12, 24)
				context.fillText  ("attacking:     " + sprite.attacking, 12, 36)
				context.fillText  ("facing:          " + sprite.getActiveSkin, 12, 48)

			}
		}.start

		stage.setTitle		("Panama Engine | Sprite Test")
		stage.setResizable  (false)
		stage.setScene      (scene)
		stage.show
	}
}

object SpriteTest {
	def main(args: Array[String]) = {
		Application.launch (classOf[SpriteTest], args: _*)
	}
}
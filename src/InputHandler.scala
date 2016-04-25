import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.Scene
import javafx.event.EventHandler
import java.util.ArrayList

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *	InputHandler Class
 *	
 *	This class deals with user input from the keyboard
 *	and mouse. Player One exhibits WASD controls and 
 *	Player Two demonstrates "point and click" sprite
 *	manipulation.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
class InputHandler (world: Array[GraphicsObject], scene: Scene) {
	var inputBuffer = new ArrayList[String]
	var players     = Array (
		world(0).asInstanceOf[Sprite],
		world(1).asInstanceOf[Sprite]
	)
	
	/** 
	 *	When a key is pressed it is added to the input 
	 *	buffer via a keycode. No duplicates allowed.
	 */
	scene.setOnKeyPressed(new EventHandler[KeyEvent] {
		override def handle (e: KeyEvent) = {
			val keyCode = e.getCode().toString()

			if (!inputBuffer.contains(keyCode))
				inputBuffer.add (keyCode)
		}
	})

	/** 
	 *	When a key is released its keycode is removed
	 *	from the input buffer.
	 */
	scene.setOnKeyReleased(new EventHandler[KeyEvent] {
		override def handle (e: KeyEvent) = {
			val keyCode = e.getCode().toString()
			inputBuffer.remove(keyCode)
		}
	})

	/** 
	 *	When the mouse is clicked its x, y position
	 *	is set as Player Two's travel target.
	 */
	scene.setOnMouseClicked(new EventHandler[MouseEvent] {
		override def handle (e: MouseEvent) = {
			var newX = e.getX.asInstanceOf[Int]
			var newY = e.getY.asInstanceOf[Int]

			players(1).setTarget(newX, newY)
		}
	})

	/** 
	 *	checkForInput
	 *
	 *	This function manipulates the player based
	 *	on the state of the input buffer.
	 */
	def checkForInput = {
		// PLAYER ONE
		if (inputBuffer.contains("W")){
			if (inputBuffer.contains("SHIFT"))
				players(0).sprintUp	
			else 
				players(0).moveUp		
		}

		if (inputBuffer.contains("A")){
			if (inputBuffer.contains("SHIFT"))
				players(0).sprintLeft
			else 
				players(0).moveLeft			
		}

		if (inputBuffer.contains("S")){
			if (inputBuffer.contains("SHIFT"))
				players(0).sprintDown
			else 
				players(0).moveDown	
		}

		if (inputBuffer.contains("D")){
			if (inputBuffer.contains("SHIFT"))
				players(0).sprintRight	
			else 
				players(0).moveRight
		}

		if (inputBuffer.contains("B"))
			players(0).startBlock
		else 
			players(0).stopBlock

		if (inputBuffer.contains("SPACE"))
			players(0).startAttack

		if (inputBuffer.contains("R"))
			players(0).reset
//		if (inputBuffer.contains("SPACE"))
//			players(0).takeDamage (1)
		if (inputBuffer.contains("H"))
			players(0).heal (1)
//		if (inputBuffer.contains("Q"))
			// pause the game

		/* PLAYER TWO
		if (inputBuffer.contains("UP"))
			players(1).moveUp			
		if (inputBuffer.contains("LEFT"))
			players(1).moveLeft				
		if (inputBuffer.contains("DOWN"))
			players(1).moveDown			
		if (inputBuffer.contains("RIGHT"))
			players(1).moveRight
		if (inputBuffer.contains("R"))
			players(1).reset
		*/
	}
}
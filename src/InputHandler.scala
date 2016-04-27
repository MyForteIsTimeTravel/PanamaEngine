import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.Scene
import javafx.event.EventHandler
import java.util.ArrayList

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  InputHandler Class
 *  
 *  This class deals with user input from the keyboard
 *  and mouse. Player One exhibits WASD controls and 
 *  Player Two demonstrates "point and click" sprite
 *  manipulation.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
class InputHandler (player: Sprite, scene: Scene) {
    var inputBuffer = new ArrayList[String]
    
    /** 
     *  When a key is pressed it is added to the input 
     *  buffer via a keycode. No duplicates allowed.
     */
    scene.setOnKeyPressed(new EventHandler[KeyEvent] {
        override def handle (e: KeyEvent) = {
            val keyCode = e.getCode().toString()

            if (!inputBuffer.contains(keyCode))
                inputBuffer.add (keyCode)
        }
    })

    /** 
     *  When a key is released its keycode is removed
     *  from the input buffer.
     */
    scene.setOnKeyReleased(new EventHandler[KeyEvent] {
        override def handle (e: KeyEvent) = {
            val keyCode = e.getCode().toString()
            inputBuffer.remove(keyCode)
        }
    })

    /** 
     *  When the mouse is clicked its x, y position
     *  is set as Player Two's travel target.
     */
    scene.setOnMouseClicked(new EventHandler[MouseEvent] {
        override def handle (e: MouseEvent) = {
            var newX = e.getX.asInstanceOf[Int]
            var newY = e.getY.asInstanceOf[Int]

            player.setTarget(newX, newY)
        }
    })

    /** 
     *  checkForInput
     *
     *  This function manipulates the player based
     *  on the state of the input buffer.
     */
    def checkForInput = {
        // PLAYER ONE
        if (inputBuffer.contains("W")){
            if (inputBuffer.contains("SHIFT"))
                player.sprintUp 
            else 
                player.moveUp       
        }

        if (inputBuffer.contains("A")){
            if (inputBuffer.contains("SHIFT"))
                player.sprintLeft
            else 
                player.moveLeft         
        }

        if (inputBuffer.contains("S")){
            if (inputBuffer.contains("SHIFT"))
                player.sprintDown
            else 
                player.moveDown 
        }

        if (inputBuffer.contains("D")){
            if (inputBuffer.contains("SHIFT"))
                player.sprintRight  
            else 
                player.moveRight
        }

        if (inputBuffer.contains("B"))
            player.startBlock
        else 
            player.stopBlock

        if (inputBuffer.contains("SPACE"))
            player.startAttack

        if (inputBuffer.contains("R"))
            player.reset

        if (inputBuffer.contains("H"))
            player.heal (1)
    }
}
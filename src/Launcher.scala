import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import javafx.scene.layout.StackPane
import javafx.scene.media.MediaPlayer
import javafx.scene.media.Media
import javafx.scene.Scene
import javafx.stage.Stage
import java.awt.Toolkit
import java.awt.Dimension

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Launcher class
 *
 *  notes       - change to "Game class"?
 *  
 *  This class sets up the canvas, initialises the 
 *  game world and runs the game loop. 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
class Launcher extends Application {

    /** 
     *  start
     *
     *  @param Stage
     *
     *  Determines the host screen size and sets up the canvas
     *  and scene based on these dimensions. The method then
     *  initialises the player objects and inserts them into 
     *  the worldObjects array. The H.U.D is also initialised 
     *  in a seperate array. When the game objects are set up
     *  the game loop is started and the stage shown.
     *  
     */
    override def start (stage: Stage) = {
        val screenSize   = Toolkit.getDefaultToolkit.getScreenSize
        val SCREENWIDTH  = screenSize.getWidth.asInstanceOf[Int]
        val SCREENHEIGHT = screenSize.getHeight.asInstanceOf[Int]
        val canvas       = new Canvas     (SCREENWIDTH, SCREENHEIGHT)
        val context      = canvas.getGraphicsContext2D
        val root         = new StackPane
        val scene        = new Scene      (root, SCREENWIDTH, SCREENHEIGHT)
        val playerOne    = new Sprite     (SCREENWIDTH, SCREENHEIGHT, "monkDude")
        val playerTwo    = new Sprite     (SCREENWIDTH, SCREENHEIGHT, "monkDude")
        val background   = new Background (SCREENWIDTH, SCREENHEIGHT)

        root.getChildren.addAll(canvas)

        /** 
         *  this defines the start positions for each player.
         *  this should be dealt with more elegantly.
         */
        playerOne.setX (SCREENWIDTH / 4)
        playerOne.setY (SCREENHEIGHT / 4)
        playerTwo.setX ((SCREENWIDTH / 4) * 3)
        playerTwo.setY ((SCREENHEIGHT / 4) * 3)

        /** 
         * worldObjects holds all the game objects to be 
         * rendered in the game world.
         */
        var worldObjects = Array[GraphicsObject] (
            playerOne,
            playerTwo,
            new WorldObject (
                SCREENWIDTH / 2, 
                SCREENHEIGHT / 2, 
                new Image("assets/world/StoneStructures/stoneWell_1.png")
            )
        )

        // maybe get rid of this...
        var activePlayer = worldObjects(0)

        /** 
         *  hudObjects is an array that holds elements such as
         *  the minimap, chat window and player information
         *  pane.
         */
        val hudObjects = Array(
            new PlayerInfo     (playerOne),
            new MiniMap        (playerOne, SCREENWIDTH, SCREENHEIGHT),
            new DialogueWindow (SCREENWIDTH, SCREENHEIGHT)
        )

        val input = new InputHandler (playerOne, scene)

        val loop = new AnimationTimer() {
            override def handle(currentNanoTime: Long) = {
                background.render(context)

                /**
                 *  worldObjects' elements are to be rendered based on
                 *  the lowest point of the object. This allows for a
                 *  slightly nice 2.5D effect that renders with the 
                 *  illusion of depth.
                 */
                worldObjects = worldObjects.sortBy(o => o.getLowestPoint)

                input.checkForInput
                worldObjects.foreach (o => o.update(currentNanoTime))
                worldObjects.foreach (o => o.render(context))
                hudObjects.foreach   (o => o.render(context))
            }
        }.start

        stage.setTitle      ("Panama Engine | pre-alpha")
        stage.setFullScreen (true)
        stage.setScene      (scene)
        stage.show
    }
}

object Launcher {

    /** 
     *  Companion object allows the javaFX application
     *  to be launched from a static context.
     */
    def main(args: Array[String]) = {
        Application.launch (
            classOf[Launcher], 
            args: _*
        )
    }
}
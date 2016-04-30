package world

import javafx.application.Application
import javafx.scene.canvas.Canvas
import javafx.scene.layout.BorderPane
import javafx.scene.Scene
import javafx.stage.Stage
import java.awt.Toolkit
import java.awt.Dimension

/** * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  LevelBuilder class
 *
 *  This class makes up a JavaFX Application that will
 *  allow levels to be constructed, saved, loaded and
 *  editted. 
 *
 *  The UI will be made up of a border pane with the
 *  top holding a ToolBar, the left holding an asset
 *  explorer, the right holding selected item properties
 *  and the bottom holding console errors for debugging.
 *
 *  The center will hold a view of the world in which
 *  objects can be dragged around and edited. A movable
 *  camera will be required to zoom/pan the level.
 *
 *  @author Ryan Needham
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
class LevelBuilder extends Application {

    /** 
     */
    override def start (stage: Stage) = {
        val screenSize   = Toolkit.getDefaultToolkit.getScreenSize
        val SCREENWIDTH  = screenSize.getWidth.asInstanceOf[Int]
        val SCREENHEIGHT = screenSize.getHeight.asInstanceOf[Int]
        val canvas       = new Canvas     (SCREENWIDTH, SCREENHEIGHT)
        val context      = canvas.getGraphicsContext2D
        val root         = new BorderPane
        val scene        = new Scene      (root, SCREENWIDTH, SCREENHEIGHT)

        root.getChildren.addAll(canvas)

        stage.setTitle      ("Panama Engine | Level Builder")
//      stage.setFullScreen (true)
        stage.setScene      (scene)
        stage.show
    }
}

object LevelBuilder {

    /** 
     *  Companion object allows the javaFX application
     *  to be launched from a static context.
     */
    def main(args: Array[String]) = {
        Application.launch (
            classOf[LevelBuilder], 
            args: _*
        )
    }
}
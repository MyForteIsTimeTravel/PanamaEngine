package hud

import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image

import graphics.GraphicsObject
import graphics.Sprite

/** * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  MiniMap class
 *  
 *  Renders a hud element in the top right hand corner
 *  of the screen. Intended to hold the map of the world
 *  but currently just outputs player debug info.
 *
 *  @author Ryan Needham
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
class MiniMap (player: Sprite, screenWidth: Int, screenHeight: Int) extends GraphicsObject {
    width  = 260
    height = 180
    x      = screenWidth - width - 20
    y      = 20

    val color  = Color.web("rgba(0,0,0,0.9)")

    /** 
     *  render
     *
     *  @param GraphicsContext
     *
     *  Fills a transparent rectangle in the top right with
     *  a thin white border. Inside the rectangle player
     *  info is outputted.
     */
    def render (context: GraphicsContext) = {
        context.setFill  (color)
        context.fillRect (getX, getY, getWidth, getHeight)

        context.setStroke  (Color.LIGHTGREY)
        context.setFill    (Color.LIGHTGREY)

        context.fillText   ("posX: "   + player.getX,               getX + 8, getY + 18)
        context.fillText   ("posY: "   + player.getY,               getX + 8, getY + 30)
        context.fillText   ("targetX: "+ player.getTargetX,         getX + 8, getY + 50)
        context.fillText   ("targetY: "+ player.getTargetY,         getX + 8, getY + 62)
        context.fillText   ("health: " + player.health,             getX + 8, getY + 80)
        context.fillText   ("energy: " + player.energy,             getX + 8, getY + 92)
        context.fillText   ("frame:  " + player.activeFrame,        getX + 8, getY + 104)
        context.fillText   ("facing: " + player.getActiveSkin,      getX + 8, getY + 116)

        context.strokeRect (getX + 4, getY + 4, getWidth - 8, getHeight - 8)
    }
}
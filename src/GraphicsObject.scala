import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Stop
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.Color
import java.util.ArrayList

/** * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  GraphicsObject trait
 *  
 *  This trait provides coordinates and size fields and
 *  methods as well as a defalut update and renderShadow
 *  method. The render method must be specified by each
 *  overriding object
 *
 *  @author Ryan Needham
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
trait GraphicsObject {
    var x:      Int = _
    var y:      Int = _
    var width:  Int = _
    var height: Int = _

    val stops = new ArrayList[Stop]
    stops.add(new Stop (0, Color.web("#424242")))
    stops.add(new Stop (1, Color.TRANSPARENT))
    val shadow = new LinearGradient (
        getWidth / 2, 
        getY + getHeight,
        getWidth / 2,
        getY + getHeight + 8, 
        false, 
        CycleMethod.NO_CYCLE, 
        stops
    )


    /** 
     *  get methods
     *
     *  @return Int
     *  
     *  These methods mostly return the state
     *  of the object "as is". The bottom
     *  to methods involve some mild computation
     *  to arrive at points of interest on
     *  the object
     */
    def getX:           Int = x
    def getY:           Int = y
    def getWidth:       Int = width
    def getHeight:      Int = height
    def getLowestPoint: Int = getY + getHeight
    def getCentralX:    Int = getX + getWidth / 2

    /** 
     *  set methods
     *
     *  @param Int
     *  
     *  These methods are used to set the x and y
     *  fields to allow movement and the setHeight
     *  and setWidth are (in theory) to allow the
     *  objects to grow and shrink (untested).
     */
    def setX      (newX:      Int) = x = newX 
    def setY      (newY:      Int) = y = newY 
    def setWidth  (newWidth:  Int) = width  = newWidth 
    def setHeight (newHeight: Int) = height = newHeight 
    
    /** 
     *  update
     *
     *  @param Long
     *
     *  This method takes the current time in nano
     *  seconds. The default implementation does 
     *  nothing and is intended to be overwritten.
     */
    def update (currentNanoTime: Long) = {}
    
    /** 
     *  render
     *
     *  @param GraphicsContext
     *
     *  This method is to be overwritten by the extending
     *  class. It will outline how the object is drawn when
     *  the game renders it.
     */
    def render (context: GraphicsContext)

    /** 
     *  renderShadow
     *
     *  @param GraphicsContext
     *
     *  This method draws a default shadow under the object.
     *  Not very effective but doesn't hurt to have it here, 
     *  works well on rectangles
     */
    def renderShadow (context: GraphicsContext) = {
        context.setFill  (shadow)
        context.fillRect (
            getX,
            getY + getHeight,
            getWidth,
            getY + getHeight + 16
        )
    }
}
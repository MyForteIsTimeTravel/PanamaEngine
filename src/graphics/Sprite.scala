package graphics

import javafx.scene.canvas.GraphicsContext
import javafx.animation.AnimationTimer
import javafx.scene.image.Image

/** * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  Sprite class
 *
 *  This classes encapsulates player data, functionality
 *  and animation code. Assets are taken from the folder
 *  baring the instances name e.g. "testSpriteTwo"
 *
 *  @author Ryan Needham
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
class Sprite (screenWidth: Int, screenHeight: Int, id: String) extends GraphicsObject {
    x             = screenWidth / 2
    y             = screenHeight / 2
    width         = 64
    height        = 64
    val name      = id
    var health    = 10
    var energy    = 10
    var blocking  = false
    var attacking = false
    val face      = new Image("assets/" + name + "/face.png", 32, 32, true, true)
    var lastUpdate: Long = 0 // experimenting with delta timing

    /** 
     *  This series of arrays hold the image assets for different
     *  animation chains.
     */
    var walkingUp = Array.tabulate (9) { f =>
        new Image (
            "assets/"+name+"/walkingUp_"+f+".png", 
            width, 
            height, 
            true, 
            true
        )
    }

    var walkingDown = Array.tabulate (9) { f =>
        new Image (
            "assets/"+name+"/walkingDown_"+f+".png", 
            width, 
            height, 
            true, 
            true
        )
    }

    var walkingLeft = Array.tabulate (9) { f =>
        new Image (
            "assets/"+name+"/walkingLeft_"+f+".png", 
            width, 
            height, 
            true,
            true
        )
    } 

    var walkingRight = Array.tabulate (9) { f =>
        new Image (
            "assets/"+name+"/walkingRight_"+f+".png", 
            width, 
            height, 
            true,
            true
        )
    } 

    var attackingUp = Array.tabulate (9) { f =>
        new Image (
            "assets/"+name+"/walkingRight_"+f+".png", 
            width, 
            height, 
            true,
            true
        )
    } 

    var attackingDown = Array.tabulate (9) { f =>
        new Image (
            "assets/"+name+"/attackingDown_"+f+".png", 
            width, 
            height, 
            true,
            true
        )
    }

    var attackingLeft = Array.tabulate (9) { f =>
        new Image (
            "assets/"+name+"/attackingLeft_"+f+".png", 
            width, 
            height, 
            true,
            true
        )
    } 

    var attackingRight = Array.tabulate (9) { f =>
        new Image (
            "assets/"+name+"/attackingRight_"+f+".png", 
            width, 
            height, 
            true,
            true
        )
    } 

    var activeSkin  = walkingDown
    var activeFrame = 0

    /** 
     *  momentum[0] - no momentum
     *  momentum[1] - left momentum
     *  momentum[2] - up momentum
     *  momentum[3] - right momentum
     *  momentum[4] - down momentum
     */
    val friction      = 3
    val momentum      = new Array[Int] (5)
    var dominantForce = 4

    var targetX  = getX
    var targetY  = getY

    momentum.foreach (x => 0)

    /** 
     *  reset
     *
     *  puts the player in the 
     *  middle of the screen
     */
    def reset = { 
        x = ((screenWidth - width / 2) / 2)
        y = ((screenHeight - height / 2) / 2)
    }

    /** 
     *  setTarget
     *
     *  @param Int
     *  @param Int
     *
     *  Sets the players targetX and targetY
     *  fields based on the input (usually
     *  taken from a MouseEvent)
     */
    def setTarget (x: Int, y: Int) = {
        targetX = x
        targetY = y
    }

    /** 
     *  getTargetX & getTargetY
     *
     *  @return Int
     *
     *  These methods return the current state
     *  of the sprites target fields
     */
    def getTargetX: Int = targetX
    def getTargetY: Int = targetY

    /** 
     *  moveLeft, moveRight, moveUp, moveDown
     *
     *  These methods increase the players momentum
     *  in the given direction by adding 2 to the 
     *  relevant element in the momentum array
     */
    def moveLeft  = momentum(1) += 2
    def moveUp    = momentum(2) += 2
    def moveRight = momentum(3) += 2
    def moveDown  = momentum(4) += 2

    /** 
     *  sprintLeft, sprintRight, sprintUp, sprintDown
     *
     *  These methods increase the players momentum
     *  in the given direction by adding 3 to the 
     *  relevant element in the momentum array
     */
    def sprintLeft  = momentum(1) += 3
    def sprintUp    = momentum(2) += 3
    def sprintRight = momentum(3) += 3
    def sprintDown  = momentum(4) += 3

    /** 
     *  moveToTarget
     *
     *  Moves the player towards their current target
     */
    def moveToTarget = {
        if (targetX > getX)
            moveRight
        else if (targetX < getX)
            moveLeft

        if (targetY > getY)
            moveDown
        else if (targetY < getY)
            moveUp
    }

    /** 
     *  startBlock & stopBlock
     *
     *  These methods start and stop the player 
     *  blocking by setting the boolean "blocking"
     *  field.
     */
    def startBlock  = blocking = true
    def stopBlock   = blocking = false

    /** 
     *  startAttack & stopAttack
     *
     *  These methods start and stop the player 
     *  attacking by setting the boolean "attacking"
     *  field.
     */
    def startAttack = {
        if (!attacking) {
            setActiveFrame(0)
            attacking = true
        }
    }

    def stopAttack = attacking = false

    /** 
     *  takeDamage
     *
     *  @param Int
     *
     *  reduces the players health field by
     *  the given amount.
     */
    def takeDamage (dam: Int) = health -= dam

    /** 
     *  heal
     *
     *  @param Int
     *
     *  increases the players health, enforcing the
     *  limit of 10. (probably make limit a variable)
     */
    def heal (hp: Int) = {
        if (health + hp <= 10)
            health += hp
    }

    /** 
     *  setActiveSkin
     *
     *  @param Array[Image]
     *
     *  Sets the active skin to the given array
     *  of images.
     */
    def setActiveSkin (texture: Array[Image]) = {
        activeSkin = texture
    }

    /** 
     *  getActiveSkin
     *
     *  @return String
     *
     *  returns a string representation of the current skin. Will
     *  return "up" if the player is facing up, "down" if they are
     *  facing down and so on. "ERROR" is returned if the players
     *  current facing is not one of the animation chains.
     */
    def getActiveSkin: String = {
        if (activeSkin == walkingUp || activeSkin == attackingUp )
            return "up"
        else if (activeSkin == walkingDown || activeSkin == attackingDown )
            return "down"
        else if (activeSkin == walkingLeft || activeSkin == attackingLeft )
            return "left"
        else if (activeSkin == walkingRight || activeSkin == attackingRight )
            return "right"
        else 
            return "ERROR"
    }

    /** 
     *  setActiveFrame
     *
     *  @param Int
     *
     *  Sets the current frame (index in the animation array) to
     *  the given value. 
     *
     *  Could make this "incrementActiveFrame" instead and enforce
     *  the boundry here instead of in the update method. 
     */
    def setActiveFrame (frameIndex: Int) = {
        activeFrame = frameIndex
    }

    /** 
     *  updateSkin
     *
     *  Sets what chain of animation the sprite should be
     *  in based on their current state.
     */
    def updateSkin = {
        if (attacking) {
            if      (dominantForce == 0) setActiveSkin(attackingDown)
            else if (dominantForce == 1) setActiveSkin(attackingLeft)
            else if (dominantForce == 2) setActiveSkin(attackingUp)
            else if (dominantForce == 3) setActiveSkin(attackingRight)
            else if (dominantForce == 4) setActiveSkin(attackingDown)
            /** 
             *  when the player starts their attack, they're set
             *  to frame 0. When they get to 8 they should stop
             *  attacking.
             */
            if (activeFrame == 8) stopAttack
        }

        else {
            if      (dominantForce == 1) setActiveSkin(walkingLeft)
            else if (dominantForce == 2) setActiveSkin(walkingUp)
            else if (dominantForce == 3) setActiveSkin(walkingRight)
            else if (dominantForce == 4) setActiveSkin(walkingDown)
        }
    }

    /** 
     *  withinRangeOfTargetX
     *
     *  @return Boolean
     *
     *  Returns true if the sprites X value is within an 
     *  acceptable range of the targets X value.
     */
    def withinRangeOfTargetX: Boolean = {
        if (getX > targetX - 6) {
            if (getX < targetX + 6) {
                setTarget(getX, getY)
                return true
            }
            else
                return false
        }
        else
            return false
    }

    /** 
     *  withinRangeOfTargetY
     *
     *  @return Boolean
     *
     *  Returns true if the sprites Y value is within an 
     *  acceptable range of the targets Y value.
     */
    def withinRangeOfTargetY: Boolean = {
        if (getY < targetY + 6) {
            if (getY > targetY - 6)
                return true
            else 
                return false
        }
        else 
            return false
    }

    /** 
     *  update
     *
     *  @param Long
     *
     *  This function deals with updating the sprites
     *  state on each game tick. Delta timing is implemented
     *  by recording the currentNanoTime in "lastUpdate" after
     *  the body of the function has finished execution.
     */
    override def update (currentNanoTime: Long) = {
        dominantForce = momentum.indexOf(momentum.max)
        
        setX(getX + momentum(3) - momentum(1))
        setY(getY - momentum(2) + momentum(4))

            /** 
     *  momentum[0] - no momentum
     *  momentum[1] - left momentum
     *  momentum[2] - up momentum
     *  momentum[3] - right momentum
     *  momentum[4] - down momentum
     */

        momentum(1) = momentum(1) / friction
        momentum(2) = momentum(2) / friction
        momentum(3) = momentum(3) / friction
        momentum(4) = momentum(4) / friction

        if (!withinRangeOfTargetX || !withinRangeOfTargetY)
            if (!attacking) moveToTarget

        updateSkin

        if (dominantForce != 0 || attacking) {
            if (currentNanoTime > lastUpdate + 16999999)
                setActiveFrame((activeFrame + 1) % 9)
        } else {
            setActiveFrame(0)
        }

        lastUpdate = currentNanoTime
    }

    /** 
     *  render
     *
     *  @param GraphicsContext
     *
     *  Renders the active frame of the active animation chain
     *  at the sprites x/y coordinates
     */
    def render (context: GraphicsContext) = {
        context.drawImage(activeSkin(activeFrame), x, y)
    }

    /** 
     *  testRender
     *
     *  @param GraphicsContext
     *
     *  Renders the sprite in a constant position for analysis
     *  in the "SpriteTest" program
     */
    def testRender (context: GraphicsContext) = {
        context.drawImage(
            activeSkin(activeFrame), 
            (screenWidth / 2) - (width / 2), 
            (screenHeight / 2) - (height / 2)
        )
    }
}

#!/bin/bash

scalac src/graphics/GraphicsObject.scala -classpath bin -sourcepath src -d bin
scalac src/graphics/Background.scala     -classpath bin -sourcepath src -d bin
scalac src/graphics/Sprite.scala         -classpath bin -sourcepath src -d bin

# hud
scalac src/hud/PlayerInfo.scala 	 -classpath bin -sourcepath src -d bin
scalac src/hud/MiniMap.scala 		 -classpath bin -sourcepath src -d bin
scalac src/hud/DialogueWindow.scala  -classpath bin -sourcepath src -d bin

# world stuff
scalac src/world/WorldObject.scala   -classpath bin -sourcepath src -d bin
scalac src/world/Level.scala 		 -classpath bin -sourcepath src -d bin
scalac src/world/LevelBuilder.scala  -classpath bin -sourcepath src -d bin

# input
scalac src/input/InputHandler.scala        -classpath bin -sourcepath src -d bin

# game
scalac src/test/SpriteTest.scala          -classpath bin -sourcepath src -d bin
scalac src/game/Launcher.scala 	         -classpath bin -sourcepath src -d bin
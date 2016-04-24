#!/bin/bash

scalac src/GraphicsObject.scala -classpath bin -sourcepath src -d bin
scalac src/Sprite.scala 		-classpath bin -sourcepath src -d bin
scalac src/Background.scala 	-classpath bin -sourcepath src -d bin

# hud
scalac src/PlayerInfo.scala 	-classpath bin -sourcepath src -d bin
scalac src/MiniMap.scala 		-classpath bin -sourcepath src -d bin
scalac src/DialogueWindow.scala -classpath bin -sourcepath src -d bin

# world stuff
scalac src/WorldObject.scala    -classpath bin -sourcepath src -d bin
scalac src/Level.scala 			-classpath bin -sourcepath src -d bin

# input and main
scalac src/SpriteTest.scala     -classpath bin -sourcepath src -d bin
scalac src/InputHandler.scala   -classpath bin -sourcepath src -d bin
scalac src/Launcher.scala 		-classpath bin -sourcepath src -d bin

cd bin
scala SpriteTest
cd ../
#!/bin/bash

# graphics
scalac src/graphics/GraphicsObject.scala -classpath bin -sourcepath src -d bin
scalac src/graphics/Background.scala     -classpath bin -sourcepath src -d bin
scalac src/graphics/Sprite.scala         -classpath bin -sourcepath src -d bin

# hud
scalac src/hud/PlayerInfo.scala 	     -classpath bin -sourcepath src -d bin
scalac src/hud/MiniMap.scala 		     -classpath bin -sourcepath src -d bin
scalac src/hud/DialogueWindow.scala      -classpath bin -sourcepath src -d bin

# world stuff
scalac src/world/worlds/WorldObject.scala       -classpath bin -sourcepath src -d bin
scalac src/world/worlds/Level.scala 		     -classpath bin -sourcepath src -d bin

scalac src/world/worldbuilder/Toolbar.scala       -classpath bin -sourcepath src
scalac src/world/worldbuilder/ConsoleWindow.scala -classpath bin -sourcepath src
scalac src/world/worldbuilder/FileExplorer.scala   -classpath bin -sourcepath src
scalac src/world/worldbuilder/ObjectInspector.scala -classpath bin -sourcepath src
scalac src/world/worldbuilder/WorldView.scala       -classpath bin -sourcepath src

scalac src/world/worldbuilder/LevelBuilder.scala      -classpath bin -sourcepath src -d bin

# input
scalac src/input/InputHandler.scala      -classpath bin -sourcepath src -d bin

# test
scalac src/test/SpriteTest.scala         -classpath bin -sourcepath src -d bin

# game
scalac src/game/Launcher.scala 	         -classpath bin -sourcepath src -d bin
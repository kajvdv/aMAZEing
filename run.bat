@echo off
javac src/MazeGui.java src/maze/*.java -d bin
java -cp bin MazeGUI
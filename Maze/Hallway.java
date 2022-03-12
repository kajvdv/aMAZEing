package Maze;

public class Hallway {

    public boolean northWall = false; // true: the is wall open. false: the wall is closed
    public boolean eastWall = false;
    public boolean southWall = false;
    public boolean westWall = false;

    public boolean isVisited() {
        return northWall == true || eastWall == true || southWall == true || westWall == true;
    }
}
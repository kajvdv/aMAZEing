package Maze;

public class Player {
    private Maze maze;
    private int X;
    private int Y;

    public Player(Maze maze, int X, int Y) {
        this.maze = maze;
        this.X = X;
        this.Y = Y;
    }

    // Moves the player through the maze. Returns True on succes
    public boolean move(Direction dir) {
        Hallway currentHallway = maze.getHallway(X, Y);
        Hallway destination;
        switch(dir) {
            case NORTH: 
                destination = maze.getHallway(X, Y - 1);
                if (currentHallway.northWall == true && destination.southWall == true) {
                    Y -= 1;
                    return true;
                }
                else {
                    return false;
                }
            case EAST:
                destination = maze.getHallway(X + 1, Y);
                if (currentHallway.eastWall == true && destination.westWall == true) {
                    X += 1;
                    return true;
                }
                else {
                    return false;
                }
            case SOUTH:
                destination = maze.getHallway(X, Y + 1);
                if (currentHallway.southWall == true && destination.northWall == true) {
                    Y += 1;
                    return true;
                }
                else {
                    return false;
                }
            case WEST:
                destination = maze.getHallway(X - 1, Y);
                if (currentHallway.westWall == true && destination.eastWall == true) {
                    X -= 1;
                    return true;
                }
                else {
                    return false;
                }
            default:
                return false;
        }
    }

    public int getX() { return X; }
    public int getY() { return Y; }

}

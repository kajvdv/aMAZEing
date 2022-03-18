package Maze;

public class Player {
    private Maze maze;
    private Location loc;

    public Player(Maze maze, Location loc) {
        this.maze = maze;
        this.loc = loc;
    }

    // Moves the player through the maze. Returns True on succes
    public boolean move(Direction dir) {
        Hallway currentHallway = maze.getHallway(loc.x, loc.y);
        Hallway destination;
        switch(dir) {
            case NORTH: 
                destination = maze.getHallway(loc.x, loc.y - 1);
                if (currentHallway.northWall == true && destination.southWall == true) {
                    loc.y -= 1;
                    return true;
                }
                else {
                    return false;
                }
            case EAST:
                destination = maze.getHallway(loc.x + 1, loc.y);
                if (currentHallway.eastWall == true && destination.westWall == true) {
                    loc.x += 1;
                    return true;
                }
                else {
                    return false;
                }
            case SOUTH:
                destination = maze.getHallway(loc.x, loc.y + 1);
                if (currentHallway.southWall == true && destination.northWall == true) {
                    loc.y += 1;
                    return true;
                }
                else {
                    return false;
                }
            case WEST:
                destination = maze.getHallway(loc.x - 1, loc.y);
                if (currentHallway.westWall == true && destination.eastWall == true) {
                    loc.x -= 1;
                    return true;
                }
                else {
                    return false;
                }
            default:
                return false;
        }
    }

    public Location getLocation() {
        return loc;
    }

}

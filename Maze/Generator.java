package Maze;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class Generator {
    private Maze maze;
    private Random rand = new Random();
    Location currentPosition = new Location(0, 0);
    Stack<Location> track = new Stack<Location>(); 
    Direction[] moveableDirections = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.SOUTH};

    
    public Generator(int width, int height) {
        this.maze = new Maze(width, height);
        track.push(new Location(currentPosition));
    }

    public void carve() {
        if (track.size() == 0) return;
        ArrayList<Direction> possibleDirections = new ArrayList<Direction>();
        for (Direction dir : moveableDirections) {
            if (isPossible(dir)) {
                possibleDirections.add(dir);
            }
        }
        if (possibleDirections.size() == 0) {
            currentPosition = track.pop();
            return;
        }
        track.push(new Location(currentPosition));
        int newDirection = rand.nextInt(possibleDirections.size());
        switch(possibleDirections.get(newDirection)) {
            case NORTH:
                maze.getHallway(currentPosition.x, currentPosition.y).northWall = true;
                currentPosition.y -= 1;
                maze.getHallway(currentPosition.x, currentPosition.y).southWall = true;
                break;
            case EAST:
                maze.getHallway(currentPosition.x, currentPosition.y).eastWall = true;
                currentPosition.x += 1;
                maze.getHallway(currentPosition.x, currentPosition.y).westWall = true;
                break;
            case SOUTH:
                maze.getHallway(currentPosition.x, currentPosition.y).southWall = true;
                currentPosition.y += 1;
                maze.getHallway(currentPosition.x, currentPosition.y).northWall = true;
                break;
            case WEST:
                maze.getHallway(currentPosition.x, currentPosition.y).westWall = true;
                currentPosition.x -= 1;
                maze.getHallway(currentPosition.x, currentPosition.y).eastWall = true;
                break;
        }
    }

    private boolean isPossible(Direction dir) {
        Hallway nextHallway;
        switch (dir) {
            case NORTH:
                nextHallway = maze.getHallway(currentPosition.x, currentPosition.y - 1);
                break;
            case EAST:
                nextHallway = maze.getHallway(currentPosition.x + 1, currentPosition.y);
                break;
            case SOUTH:
                nextHallway = maze.getHallway(currentPosition.x, currentPosition.y + 1);
                break;
            case WEST:
                nextHallway = maze.getHallway(currentPosition.x - 1, currentPosition.y);
                break;
            default:
                return false;
        }
        if (nextHallway == null) return false;
        else return nextHallway.isVisited() == false;
    }

    public Maze getMaze() {
        return maze;
    }

    public Location getPosition() {
        return currentPosition;
    }

    public boolean isGenerated() {
        return track.size() == 0;
    }
}

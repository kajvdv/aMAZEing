package Maze;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;


public class Generator {
    private Maze maze;
    private Random rand = new Random();
    int[] currentPosition = {0, 0};
    Stack<int[]> track = new Stack<int[]>(); 
    Direction[] moveableDirections = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

    
    public Generator(int width, int height) {
        this.maze = new Maze(width, height);
        track.push(Arrays.copyOf(currentPosition, 2));
    }

    public void carve() {
        if (track.size() == 0) return;
        ArrayList<Direction> possibleDirections = new ArrayList<Direction>();
        for (Direction dir : moveableDirections) {
            if (isPossible(dir)) {
                System.out.print(dir.toString() + " ");
                possibleDirections.add(dir);
            }
        }
        System.out.println("------");
        if (possibleDirections.size() == 0) {
            currentPosition = track.pop();
            System.out.println("pop");
            return;
        }
        track.push(Arrays.copyOf(currentPosition, 2));
        int newDirection = rand.nextInt(possibleDirections.size());
        switch(possibleDirections.get(newDirection)) {
            case NORTH:
                maze.getHallway(currentPosition[0], currentPosition[1]).northWall = true;
                currentPosition[1] -= 1;
                maze.getHallway(currentPosition[0], currentPosition[1]).southWall = true;
                break;
            case EAST:
                maze.getHallway(currentPosition[0], currentPosition[1]).eastWall = true;
                currentPosition[0] += 1;
                maze.getHallway(currentPosition[0], currentPosition[1]).westWall = true;
                break;
            case SOUTH:
                maze.getHallway(currentPosition[0], currentPosition[1]).southWall = true;
                currentPosition[1] += 1;
                maze.getHallway(currentPosition[0], currentPosition[1]).northWall = true;
                break;
            case WEST:
                maze.getHallway(currentPosition[0], currentPosition[1]).westWall = true;
                currentPosition[0] -= 1;
                maze.getHallway(currentPosition[0], currentPosition[1]).eastWall = true;
                break;
        }
    }

    private boolean isPossible(Direction dir) {
        Hallway nextHallway;
        switch (dir) {
            case NORTH:
                nextHallway = maze.getHallway(currentPosition[0], currentPosition[1] - 1);
                break;
            case EAST:
                nextHallway = maze.getHallway(currentPosition[0] + 1, currentPosition[1]);
                break;
            case SOUTH:
                nextHallway = maze.getHallway(currentPosition[0], currentPosition[1] + 1);
                break;
            case WEST:
                nextHallway = maze.getHallway(currentPosition[0] - 1, currentPosition[1]);
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

    public int[] getPosition() {
        return currentPosition;
    }

    public boolean isGenerated() {
        return track.size() == 0;
    }
}

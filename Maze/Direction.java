package Maze;

import java.util.Random;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static Direction getRandomDirection() {
        Random rand = new Random();
        int dir = rand.nextInt() % 4;
        switch(dir) {
            case 0:
                return NORTH;
            case 1:
                return EAST;
            case 2:
                return SOUTH;
            case 3:
                return WEST;
        }
        return NORTH; // default direction :s
    }
}

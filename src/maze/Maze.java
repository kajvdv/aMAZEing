package maze;

public class Maze {

    private int width;
    private int height;
    private Hallway[] maze;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new Hallway[width * height];
        // init maze
        for (int i = 0; i < this.maze.length; i++) {
            maze[i] = new Hallway();
        }
    }

    public Hallway getHallway(int x, int y) {
        if (x >= width || x < 0 || y >= height || y < 0) return null;
        else return maze[y*width + x];
    }
    
    public Hallway[] getMazeHallways() {
        return maze;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
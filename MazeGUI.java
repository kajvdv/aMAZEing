import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import Maze.Direction;
import Maze.Generator;
import Maze.Hallway;
import Maze.Location;
import Maze.Maze;
import Maze.Player;

public class MazeGUI extends JPanel {
    private Generator gen;
    private Player player;
    
    public MazeGUI(int mazeWidth, int mazeHeight) {
        this.gen = new Generator(mazeWidth, mazeHeight);
        this.player = new Player(gen.getMaze(), new Location(mazeWidth / 2, mazeHeight / 2));
        Maze maze = gen.getMaze();
        Dimension preferredSize = new Dimension(maze.getWidth() * 40, maze.getHeight() * 40);
        setPreferredSize(preferredSize);
        setFocusable(true);
        setOpaque(true);
        setForeground(Color.BLACK);

        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if (gen.isGenerated() == false) {
                    gen.carve();
                }
                else {
                    switch(e.getKeyCode()) {
                        case KeyEvent.VK_UP: player.move(Direction.NORTH); break;
                        case KeyEvent.VK_RIGHT: player.move(Direction.EAST); break;
                        case KeyEvent.VK_DOWN: player.move(Direction.SOUTH); break;
                        case KeyEvent.VK_LEFT: player.move(Direction.WEST); break;
                    }
                }
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Maze maze = gen.getMaze();
        // draw corners
        for (int j = 0; j < maze.getHeight(); j++) {
            for (int i = 0; i < maze.getWidth(); i++) {
                int x = i * 40;
                int y = j * 40;
                g.fillRect(x, y, 5, 5);
                g.fillRect(x+35, y, 5, 5);
                g.fillRect(x, y+35, 5, 5);
                g.fillRect(x+35, y+35, 5, 5);
            }
        }
        // draw walls
        for (int j = 0; j < maze.getHeight(); j++) {
            for (int i = 0; i < maze.getWidth(); i++) {
                int x = i * 40;
                int y = j * 40;
                Hallway hallway = maze.getHallway(i, j);
                if (!hallway.westWall) g.fillRect(x, y + 5, 5, 40 - 2 * 5);
                if (!hallway.northWall) g.fillRect(x + 5, y, 40 - 2 * 5, 5);
                if (!hallway.eastWall) g.fillRect(x + 40 - 5, y + 5, 5, 40 - 2 * 5);
                if (!hallway.southWall) g.fillRect(x + 5, y + 40 - 5, 30, 5);            
            }
        }
        // draw position of generator
        if (gen.isGenerated() == false) { 
            g.setColor(Color.RED);
            Location pos = gen.getPosition();
            g.fillRect(pos.x * 40 + 5, pos.y * 40 + 5, 30, 30);
        }
        else {
            g.setColor(Color.BLUE);
            Location loc = player.getLocation();
            g.fillRect(loc.x * 40 + 5, loc.y * 40 + 5, 30, 30);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Maze");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setContentPane(new MazeGUI(30, 20));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        
    }
}

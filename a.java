import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class a extends JFrame {
    private static final int TILE_SIZE = 32;
    private static final int ROWS = 15;
    private static final int COLS = 20;
    private int[][] world = new int[ROWS][COLS];
    private int playerX = COLS / 2, playerY = ROWS / 2;

    public a() {
        setTitle("Simple Minecraft");
        setSize(COLS * TILE_SIZE, ROWS * TILE_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializa el mundo con césped (1) y algo de piedra (2)
        setFocusable(true);
        requestFocusInWindow();
        for (int y = 0; y < ROWS; y++)
            for (int x = 0; x < COLS; x++)
                world[y][x] = (Math.random() < 0.1) ? 2 : 1;

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int dx = 0, dy = 0;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W: dy = -1; break;
                    case KeyEvent.VK_S: dy = 1; break;
                    case KeyEvent.VK_A: dx = -1; break;
                    case KeyEvent.VK_D: dx = 1; break;
                    case KeyEvent.VK_SPACE: // Remove block
                        world[playerY][playerX] = 0;
                        repaint();
                        return;
                    case KeyEvent.VK_E: // Place block
                        world[playerY][playerX] = 2;
                        repaint();
                        return;
                }
                int nx = playerX + dx, ny = playerY + dy;
                if (nx >= 0 && nx < COLS && ny >= 0 && ny < ROWS) {
                    playerX = nx;
                    playerY = ny;
                    repaint();
                }
            }
        });

        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                switch (world[y][x]) {
                    case 1: g.setColor(new Color(34, 139, 34)); break; // Grass
                    case 2: g.setColor(Color.GRAY); break; // Stone
                    default: g.setColor(new Color(135, 206, 235)); // Air
                }
                g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
        // Draw player
        g.setColor(Color.ORANGE);
        g.fillRect(playerX * TILE_SIZE + 8, playerY * TILE_SIZE + 8, TILE_SIZE - 16, TILE_SIZE - 16);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new a().setVisible(true)); 
    }
}
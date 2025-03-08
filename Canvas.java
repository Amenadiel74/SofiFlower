package shapes;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Canvas is a class to allow for simple graphical drawing.
 * It acts as a singleton: only one Canvas instance can exist
 * at a time. Each shape is drawn onto this single Canvas.
 */
public class Canvas extends JPanel {
    private static Canvas canvasSingleton;

    private final ArrayList<Object> objects; // shapes to be drawn
    private final ArrayList<Color> colors;   // color for each shape
    private JFrame frame;

    /**
     * Private constructor to enforce the singleton pattern.
     * @param title the title of the canvas window
     * @param width the width of the window
     * @param height the height of the window
     */
    private Canvas(String title, int width, int height) {
        frame = new JFrame();
        frame.setContentPane(this);
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null); // center on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        objects = new ArrayList<>();
        colors = new ArrayList<>();
    }

    /**
     * Returns the single instance of Canvas.
     * If not created yet, creates one with default size.
     * @return the Canvas instance
     */
    public static Canvas getCanvas() {
        if (canvasSingleton == null) {
            canvasSingleton = new Canvas("Canvas", 600, 500);
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }

    /**
     * Sets the canvas visibility and brings it to the front of the screen.
     * @param visible true to show the canvas, false to hide it
     */
    public void setVisible(boolean visible) {
        if (frame != null) {
            frame.setVisible(visible);
        }
    }

    /**
     * Adds the given shape to the canvas with the given color.
     * @param shape an AWT Shape (e.g. Ellipse2D, Rectangle2D, Polygon)
     * @param color the Color in which to draw the shape
     */
    public void add(Object shape, Color color) {
        objects.add(shape);
        colors.add(color);
        repaint();
    }

    /**
     * Removes the given shape from the canvas.
     * @param shape the shape to remove
     */
    public void remove(Object shape) {
        int index = objects.indexOf(shape);
        if (index != -1) {
            objects.remove(index);
            colors.remove(index);
            repaint();
        }
    }

    /**
     * Paint method called by the AWT framework.
     * Draws each shape in objects[] with the corresponding color in colors[].
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < objects.size(); i++) {
            g2.setColor(colors.get(i));
            Object shape = objects.get(i);
            if (shape instanceof Shape) {
                g2.fill((Shape) shape);
            }
        }
    }

    /**
     * Wait for a specified number of milliseconds (simple utility method).
     * @param milliseconds length of time to pause
     */
    public void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            // ignoring exception at the moment
        }
    }
}



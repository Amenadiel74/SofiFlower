package shapes;

import java.awt.*;
import java.awt.geom.*;

/**
 * A square that can be manipulated and draws itself on a canvas.
 */
public class Square {
    private int xPosition;
    private int yPosition;
    private int size;
    private String color;
    private boolean isVisible;

    /**
     * Create a new square at default position with default color.
     */
    public Square() {
        xPosition = 0;
        yPosition = 0;
        size = 40;
        color = "red";
        isVisible = false;
    }

    /**
     * Make this square visible.
     */
    public void makeVisible() {
        isVisible = true;
        draw();
    }

    /**
     * Make this square invisible.
     */
    public void makeInvisible() {
        erase();
        isVisible = false;
    }

    /**
     * Move the square a few pixels to the right.
     */
    public void moveRight() {
        moveHorizontal(20);
    }

    /**
     * Move the square a few pixels to the left.
     */
    public void moveLeft() {
        moveHorizontal(-20);
    }

    /**
     * Move the square a few pixels up.
     */
    public void moveUp() {
        moveVertical(-20);
    }

    /**
     * Move the square a few pixels down.
     */
    public void moveDown() {
        moveVertical(20);
    }

    /**
     * Move the square horizontally by 'distance' pixels.
     * @param distance positive (right) or negative (left)
     */
    public void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the square vertically by 'distance' pixels.
     * @param distance positive (down) or negative (up)
     */
    public void moveVertical(int distance) {
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the square horizontally by 'distance' pixels.
     */
    public void slowMoveHorizontal(int distance) {
        int delta = distance < 0 ? -1 : 1;
        for(int i = 0; i < Math.abs(distance); i++) {
            moveHorizontal(delta);
            Canvas.getCanvas().wait(10);
        }
    }

    /**
     * Slowly move the square vertically by 'distance' pixels.
     */
    public void slowMoveVertical(int distance) {
        int delta = distance < 0 ? -1 : 1;
        for(int i = 0; i < Math.abs(distance); i++) {
            moveVertical(delta);
            Canvas.getCanvas().wait(10);
        }
    }

    /**
     * Change the size to the new size in pixels.
     * Size must be >= 0.
     * @param newSize new side length of the square
     */
    public void changeSize(int newSize) {
        erase();
        size = newSize;
        draw();
    }

    /**
     * Change the color. Valid colors: "red", "yellow", "blue",
     * "green", "magenta", "black".
     * @param newColor string name of the color
     */
    public void changeColor(String newColor) {
        color = newColor;
        draw();
    }

    /**
     * Draw the square with current specifications on screen.
     */
    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.add(createShape(), convertColor(color));
        }
    }

    /**
     * Erase the square on screen.
     */
    private void erase() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.remove(createShape());
        }
    }

    /**
     * Create the shape representation for the square.
     */
    private Shape createShape() {
        return new Rectangle2D.Double(xPosition, yPosition, size, size);
    }

    /**
     * Convert a color string into a Java Color object.
     */
    private Color convertColor(String colorString) {
        switch(colorString) {
            case "red":     return Color.red;
            case "yellow":  return Color.yellow;
            case "blue":    return Color.blue;
            case "green":   return Color.green;
            case "magenta": return Color.magenta;
            case "black":   return Color.black;
            default:        return Color.red; // default
        }
    }
}


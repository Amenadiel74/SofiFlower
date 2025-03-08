package shapes;

import java.awt.*;
import java.awt.geom.*;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 */
public class Triangle {
    private int xPosition;
    private int yPosition;
    private int height;
    private int width;
    private String color;
    private boolean isVisible;

    /**
     * Create a new triangle at default position with default color.
     */
    public Triangle() {
        xPosition = 50;
        yPosition = 15;
        height = 30;
        width = 40;
        color = "green";
        isVisible = false;
    }

    /**
     * Make this triangle visible.
     */
    public void makeVisible() {
        isVisible = true;
        draw();
    }

    /**
     * Make this triangle invisible.
     */
    public void makeInvisible() {
        erase();
        isVisible = false;
    }

    /**
     * Move the triangle a few pixels to the right.
     */
    public void moveRight() {
        moveHorizontal(20);
    }

    /**
     * Move the triangle a few pixels to the left.
     */
    public void moveLeft() {
        moveHorizontal(-20);
    }

    /**
     * Move the triangle a few pixels up.
     */
    public void moveUp() {
        moveVertical(-20);
    }

    /**
     * Move the triangle a few pixels down.
     */
    public void moveDown() {
        moveVertical(20);
    }

    /**
     * Move the triangle horizontally by 'distance' pixels.
     * @param distance positive (right) or negative (left)
     */
    public void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the triangle vertically by 'distance' pixels.
     * @param distance positive (down) or negative (up)
     */
    public void moveVertical(int distance) {
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the triangle horizontally by 'distance' pixels.
     */
    public void slowMoveHorizontal(int distance) {
        int delta = distance < 0 ? -1 : 1;
        for(int i = 0; i < Math.abs(distance); i++) {
            moveHorizontal(delta);
            Canvas.getCanvas().wait(10);
        }
    }

    /**
     * Slowly move the triangle vertically by 'distance' pixels.
     */
    public void slowMoveVertical(int distance) {
        int delta = distance < 0 ? -1 : 1;
        for(int i = 0; i < Math.abs(distance); i++) {
            moveVertical(delta);
            Canvas.getCanvas().wait(10);
        }
    }

    /**
     * Change the size to the new height and width (in pixels).
     * @param newHeight the new height
     * @param newWidth the new width
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }

    /**
     * Change the color. Valid colors are "red", "yellow", "blue",
     * "green", "magenta" and "black".
     * @param newColor the color name
     */
    public void changeColor(String newColor) {
        color = newColor;
        draw();
    }

    /**
     * Draw the triangle with current specifications on screen.
     */
    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.add(createShape(), convertColor(color));
        }
    }

    /**
     * Erase the triangle on screen.
     */
    private void erase() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.remove(createShape());
        }
    }

    /**
     * Create the Polygon shape for this triangle.
     */
    private Shape createShape() {
        Polygon triangle = new Polygon();
        // We'll define the triangle as an isosceles pointing downward
        triangle.addPoint(xPosition, yPosition);
        triangle.addPoint(xPosition + width/2, yPosition + height);
        triangle.addPoint(xPosition - width/2, yPosition + height);
        return triangle;
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
            default:        return Color.green; // default
        }
    }
}


package shapes;

import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 */
public class Circle {
    private int xPosition;
    private int yPosition;
    private int diameter;
    private String color;
    private boolean isVisible;

    /**
     * Create a new circle at default position with default color.
     */
    public Circle() {
        xPosition = 0;
        yPosition = 0;
        diameter = 30;
        color = "blue";
        isVisible = false;
    }

    /**
     * Make this circle visible. If it was already visible, do nothing.
     */
    public void makeVisible() {
        isVisible = true;
        draw();
    }

    /**
     * Make this circle invisible.
     */
    public void makeInvisible() {
        erase();
        isVisible = false;
    }

    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight() {
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft() {
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp() {
        moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown() {
        moveVertical(20);
    }

    /**
     * Move the circle horizontally by 'distance' pixels.
     * @param distance positive to move right, negative to move left
     */
    public void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically by 'distance' pixels.
     * @param distance positive to move down, negative to move up
     */
    public void moveVertical(int distance) {
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the circle horizontally by 'distance' pixels.
     */
    public void slowMoveHorizontal(int distance) {
        int delta = distance < 0 ? -1 : 1;
        for(int i = 0; i < Math.abs(distance); i++) {
            moveHorizontal(delta);
            Canvas.getCanvas().wait(10);
        }
    }

    /**
     * Slowly move the circle vertically by 'distance' pixels.
     */
    public void slowMoveVertical(int distance) {
        int delta = distance < 0 ? -1 : 1;
        for(int i = 0; i < Math.abs(distance); i++) {
            moveVertical(delta);
            Canvas.getCanvas().wait(10);
        }
    }

    /**
     * Change the size to the new diameter in pixels.
     * Diameter must be >= 0.
     * @param newDiameter the new diameter
     */
    public void changeSize(int newDiameter) {
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Change the color. Valid colors are "red", "yellow", "blue",
     * "green", "magenta" and "black".
     * @param newColor the new color name
     */
    public void changeColor(String newColor) {
        color = newColor;
        draw();
    }

    /**
     * Draw the circle with current specifications on screen.
     */
    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.add(createShape(), convertColor(color));
        }
    }

    /**
     * Erase the circle on screen.
     */
    private void erase() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.remove(createShape());
        }
    }

    /**
     * Create the circle shape for drawing.
     */
    private Shape createShape() {
        return new Ellipse2D.Double(xPosition, yPosition, diameter, diameter);
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
            default:        return Color.blue; // default
        }
    }
}


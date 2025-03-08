package Maxwell;

import shapes.Circle;

/**
 * Representa una partícula que usa un Circle
 * del paquete shapes para su representación.
 */
public class Particle {
    private Circle shape;
    private int x, y;
    private int dx, dy;    // direcciones
    private String colorBase;

    /**
     * Crea una partícula en (x,y) con color base dado,
     * y la hace visible si el contenedor está visible.
     */
    public Particle(int x, int y, String colorBase, boolean visible) {
        this.x = x;
        this.y = y;
        this.dx = 1;
        this.dy = 1;
        this.colorBase = colorBase;

        shape = new Circle();
        shape.changeColor(colorBase);
        shape.moveHorizontal(x);
        shape.moveVertical(y);

        if (visible) {
            shape.makeVisible();
        }
    }

    /**
     * Mueve la partícula según dx, dy.
     */
    public void move() {
        x += dx;
        y += dy;
        shape.moveHorizontal(dx);
        shape.moveVertical(dy);
    }

    /**
     * Invierte la dirección horizontal.
     */
    public void reverseX() {
        dx = -dx;
    }

    /**
     * Invierte la dirección vertical.
     */
    public void reverseY() {
        dy = -dy;
    }

    /**
     * Hace visible la partícula.
     */
    public void makeVisible() {
        shape.makeVisible();
    }

    /**
     * Hace invisible la partícula.
     */
    public void makeInvisible() {
        shape.makeInvisible();
    }

    /**
     * @return coordenada X actual
     */
    public int getX() {
        return x;
    }

    /**
     * @return coordenada Y actual
     */
    public int getY() {
        return y;
    }
}

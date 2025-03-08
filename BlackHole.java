package Maxwell;

import shapes.Circle;

/**
 * Representa un agujero negro que puede "tragar"
 * una cierta cantidad de partículas.
 */
public class BlackHole {
    private Circle shape;
    private int x, y;
    private int capacity;

    /**
     * Crea un agujero negro en (x,y) con la capacidad dada.
     * Si visible es true, se muestra.
     */
    public BlackHole(int x, int y, int capacity, boolean visible) {
        this.x = x;
        this.y = y;
        this.capacity = capacity;

        shape = new Circle();
        shape.changeColor("black");
        shape.moveHorizontal(x);
        shape.moveVertical(y);
        shape.changeSize(40);

        if (visible) {
            shape.makeVisible();
        }
    }

    /**
     * Consume una partícula, disminuyendo la capacidad.
     * @return true si consumió (si todavía había capacidad)
     */
    public boolean consumeParticle() {
        if (capacity > 0) {
            capacity--;
            return true;
        }
        return false;
    }

    /**
     * Hace visible el agujero negro.
     */
    public void makeVisible() {
        shape.makeVisible();
    }

    /**
     * Hace invisible el agujero negro.
     */
    public void makeInvisible() {
        shape.makeInvisible();
    }

    /**
     * @return coordenada X
     */
    public int getX() {
        return x;
    }

    /**
     * @return coordenada Y
     */
    public int getY() {
        return y;
    }
}

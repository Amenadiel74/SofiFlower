package Maxwell;

import shapes.Triangle;

/**
 * Representa un Demonio que usa un Triangle
 * del paquete shapes para su representación.
 */
public class Demon {
    private Triangle shape;
    private int x, y;

    /**
     * Crea un demonio en (x,y). Si visible es true, 
     * se muestra en pantalla.
     */
    public Demon(int x, int y, boolean visible) {
        this.x = x;
        this.y = y;
        shape = new Triangle();
        shape.changeSize(40, 40);
        shape.changeColor("black");
        shape.moveHorizontal(x);
        shape.moveVertical(y);

        if (visible) {
            shape.makeVisible();
        }
    }

    /**
     * Hace visible el demonio.
     */
    public void makeVisible() {
        shape.makeVisible();
    }

    /**
     * Hace invisible el demonio.
     */
    public void makeInvisible() {
        shape.makeInvisible();
    }
}

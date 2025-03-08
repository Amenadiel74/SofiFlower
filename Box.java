package Maxwell;

import shapes.Square;

/**
 * Representa un 'box' de fondo para dibujar el contenedor.
 * Se basa en la clase Square del paquete shapes.
 */
public class Box {
    private Square shape;
    private boolean visible;

    /**
     * Crea un box en la esquina superior izquierda (x=0, y=0)
     * con el tamaño especificado. 
     */
    public Box(int x, int y, int size, boolean visible) {
        shape = new Square();
        shape.moveHorizontal(x);
        shape.moveVertical(y);
        shape.changeSize(size);
        shape.changeColor("black");  // Cambia si deseas otro color
        this.visible = false;

        if (visible) {
            makeVisible();
        }
    }

    public void makeVisible() {
        if (!visible) {
            visible = true;
            shape.makeVisible();
        }
    }

    public void makeInvisible() {
        if (visible) {
            visible = false;
            shape.makeInvisible();
        }
    }
}


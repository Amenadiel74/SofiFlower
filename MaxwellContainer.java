package Maxwell;

import shapes.*;
import java.util.ArrayList;

/**
 * Clase principal del simulador "Maxwell's Demon".
 * Maneja un contenedor en el que se ubican demonios, partículas y agujeros negros.
 */
public class MaxwellContainer {
    private int width;
    private int height;
    private boolean visible;
    private boolean ok;

    // Si no necesitas la caja de fondo, puedes eliminar esta línea
    private Box containerBox;

    private ArrayList<Demon> demons;
    private ArrayList<Particle> particles;
    private ArrayList<BlackHole> holes;

    /**
     * Construye un contenedor con dimensiones por defecto (400x300).
     */
    public MaxwellContainer() {
        width = 400;
        height = 300;
        visible = false;
        ok = true;

        // Inicializa arrays
        demons = new ArrayList<>();
        particles = new ArrayList<>();
        holes = new ArrayList<>();

        // Crea el "box" (cuadrado de fondo) del tamaño actual
        // (Si no te interesa, comenta o quita estas líneas)
        containerBox = new Box(0, 0, height, false);
    }

    /**
     * Crea o reinicia el contenedor con dimensiones w x h.
     * Limpia listas y vuelve a crear la "caja" contenedora.
     * @param w ancho
     * @param h alto
     */
    public void create(int w, int h) {
        width = w;
        height = h;
        demons.clear();
        particles.clear();
        holes.clear();

        // Re-creamos un box adaptado a la altura o anchura (elige según prefieras)
        // Para que sea un cuadrado, puedes usar min(w, h).
        containerBox = new Box(0, 0, Math.min(width, height), visible);

        ok = true;
    }

    /**
     * Agrega un demonio en (x, y).
     * @param x coord X
     * @param y coord Y
     */
    public void addDemon(int x, int y) {
        Demon d = new Demon(x, y, visible);
        demons.add(d);
        ok = true;
    }

    /**
     * Elimina un demonio si existe.
     * @param d referencia al demonio a eliminar
     */
    public void deleteDemon(Demon d) {
        if (demons.contains(d)) {
            demons.remove(d);
            d.makeInvisible();
            ok = true;
        } else {
            ok = false;
            showError("Demon no encontrado.");
        }
    }

    /**
     * Agrega una partícula en (x, y) con un color base.
     * @param x coord X
     * @param y coord Y
     * @param colorBase color (red, blue, green, etc.)
     */
    public void addParticle(int x, int y, String colorBase) {
        Particle p = new Particle(x, y, colorBase, visible);
        particles.add(p);
        ok = true;
    }

    /**
     * Elimina una partícula si existe en el contenedor.
     * @param p referencia a la partícula
     */
    public void deleteParticle(Particle p) {
        if (particles.contains(p)) {
            particles.remove(p);
            p.makeInvisible();
            ok = true;
        } else {
            ok = false;
            showError("Partícula no encontrada.");
        }
    }

    /**
     * Agrega un agujero negro con cierta capacidad.
     * @param x coord X
     * @param y coord Y
     * @param capacity número de partículas que puede consumir
     */
    public void addHole(int x, int y, int capacity) {
        BlackHole hole = new BlackHole(x, y, capacity, visible);
        holes.add(hole);
        ok = true;
    }

    /**
     * Mueve las partículas 'steps' veces.
     * Cada paso:
     * 1. Mueve cada partícula
     * 2. Verifica bordes
     * 3. Verifica agujeros
     * Pausa ligeramente si está en modo visible.
     * @param steps número de pasos
     */
    public void start(int steps) {
        for (int i = 0; i < steps; i++) {
            // Importante: recorrer con índice o un for-each normal, 
            // pero ten cuidado si eliminas objetos en medio del bucle.
            for (int j = 0; j < particles.size(); j++) {
                Particle p = particles.get(j);
                p.move();
                checkBoundaries(p);
                checkHoles(p);
            }

            // Pequeña pausa para ver animación
            if (visible) {
                waitSomeTime(10);
            }
        }
        ok = true;
    }

    /**
     * Retorna un texto con la cantidad de demonios, partículas y agujeros.
     * @return String con la info
     */
    public String consult() {
        ok = true;
        return "Demons: " + demons.size() +
               "\nParticles: " + particles.size() +
               "\nHoles: " + holes.size();
    }

    /**
     * Hace visible el contenedor, mostrando la caja de fondo y todos los objetos.
     */
    public void makeVisible() {
        visible = true;
        // Muestra la caja de fondo (si la usas)
        containerBox.makeVisible();

        for (Demon d : demons) {
            d.makeVisible();
        }
        for (Particle p : particles) {
            p.makeVisible();
        }
        for (BlackHole h : holes) {
            h.makeVisible();
        }
        ok = true;
    }

    /**
     * Hace invisible el contenedor y todo lo que contenga.
     */
    public void makeInvisible() {
        visible = false;
        for (Demon d : demons) {
            d.makeInvisible();
        }
        for (Particle p : particles) {
            p.makeInvisible();
        }
        for (BlackHole h : holes) {
            h.makeInvisible();
        }
        containerBox.makeInvisible();
        ok = true;
    }

    /**
     * Termina la simulación, vaciando todo.
     */
    public void finish() {
        makeInvisible();
        demons.clear();
        particles.clear();
        holes.clear();
        ok = true;
    }

    /**
     * Indica si la última acción se realizó correctamente.
     * @return true si fue ok, false en caso contrario
     */
    public boolean ok() {
        return ok;
    }

    /**
     * Método main para probar rápidamente la clase.
     */
    public static void main(String[] args) {
        MaxwellContainer container = new MaxwellContainer();
        container.create(400, 300);

        container.makeVisible();

        container.addDemon(50, 50);
        container.addParticle(70, 70, "blue");
        container.addParticle(100, 100, "red");

        // Opcional: añadir un agujero negro
        container.addHole(200, 150, 2);

        // Mueve las partículas 200 pasos
        container.start(200);

        // Muestra en consola la info
        System.out.println(container.consult());

        // Termina
        container.finish();
    }

    // ----------------
    // Métodos privados
    // ----------------

    /**
     * Verifica si la partícula salió de los bordes y la hace rebotar.
     */
    private void checkBoundaries(Particle p) {
        if (p.getX() < 0 || p.getX() > (width - 10)) {
            p.reverseX();
        }
        if (p.getY() < 0 || p.getY() > (height - 10)) {
            p.reverseY();
        }
    }

    /**
     * Verifica colisiones con agujeros.
     */
    private void checkHoles(Particle p) {
        for (int i = 0; i < holes.size(); i++) {
            BlackHole h = holes.get(i);
            int dx = p.getX() - h.getX();
            int dy = p.getY() - h.getY();
            double dist = Math.sqrt(dx*dx + dy*dy);
            if (dist < 20) {
                // si el agujero tiene capacidad, consume
                if (h.consumeParticle()) {
                    p.makeInvisible();
                    particles.remove(p);
                }
                break; 
            }
        }
    }

    /**
     * Pausa la ejecución por ms milisegundos (para animación).
     */
    private void waitSomeTime(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // ignorar
        }
    }

    /**
     * Muestra error si está en modo visible.
     */
    private void showError(String msg) {
        if (visible) {
            System.out.println("ERROR: " + msg);
        }
    }
}





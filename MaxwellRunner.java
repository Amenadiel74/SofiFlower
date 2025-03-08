package Maxwell;

/**
 * Clase principal para ejecutar la simulación de Maxwell's Demon.
 */
public class MaxwellRunner {
    public static void main(String[] args) {
        // Creamos el contenedor
        MaxwellContainer container = new MaxwellContainer();
        
        // Asignamos dimensiones
        container.create(400, 300);

        // Hacemos visible
        container.makeVisible();

        // Agregamos un demonio
        container.addDemon(50, 50);

        // Agregamos varias partículas
        container.addParticle(70, 70, "blue");
        container.addParticle(100, 100, "red");

        // Agregamos un agujero negro (opcional)
        container.addHole(200, 150, 2);

        // Movemos las partículas 200 pasos
        container.start(200);

        // Mostramos en consola el estado final
        System.out.println(container.consult());

        // Finalizamos
        container.finish();
    }
}

package Maxwell;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Pruebas de unidad para la clase MaxwellContainer.
 * Verificamos funcionalidad básica en modo invisible.
 */
public class MaxwellContainerTest {
    private MaxwellContainer container;

    @Before
    public void setUp() {
        container = new MaxwellContainer();
        // Creamos con dimensiones 300x200
        container.create(300, 200);
        // Aseguramos que todo inicie invisible para las pruebas
        container.makeInvisible();
    }

    @After
    public void tearDown() {
        // Finalizamos para limpiar (opcional)
        container.finish();
    }

    @Test
    public void testAddParticleOk() {
        container.addParticle(50, 50, "blue");
        // Debe haber sido exitoso (ok = true)
        assertTrue(container.ok());
        // Consultamos y verificamos que hay 1 partícula
        String info = container.consult();
        assertTrue(info.contains("Particles: 1"));
    }

    @Test
    public void testDeleteParticleFail() {
        // Creamos una partícula "por fuera"
        Particle p = new Particle(10, 10, "red", false);
        // Intentamos borrarla del contenedor (no existe dentro)
        container.deleteParticle(p);
        // Debe fallar => ok = false
        assertFalse(container.ok());
    }

    @Test
    public void testAddDemonOk() {
        container.addDemon(30, 30);
        assertTrue(container.ok());
        String info = container.consult();
        assertTrue(info.contains("Demons: 1"));
    }

    @Test
    public void testMovementInvisible() {
        // Agregar y mover partículas en modo invisible
        container.addParticle(50, 50, "blue");
        container.addParticle(60, 60, "red");
        container.start(10); // Mueve 10 pasos
        // No esperamos errores
        assertTrue(container.ok());
    }

    @Test
    public void testAddHoleAndConsumeParticle() {
        // Agregar un agujero con capacidad 1
        container.addHole(50, 50, 1);
        container.addParticle(50, 50, "blue");
        // Movemos 1 paso => colisión inmediata
        container.start(1);
        // Se consumió la partícula => 0
        String info = container.consult();
        assertTrue(info.contains("Particles: 0"));
        assertTrue(container.ok());
    }
}


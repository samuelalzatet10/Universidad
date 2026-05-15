package co.edu.unquindio.poo.test;

import static org.junit.jupiter.api.Assertions.*;
import co.edu.unquindio.poo.model.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Clase para probar el cálculo de tarifas de peaje según tipo de vehículo
 * @author Samuel Alzate Trujillo
 * @since 2026-05
 *
 * Licencia GNU/GPL V3.0
 */
public class EmpresaTest {

    /**
     * Instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger( EmpresaTest.class.getName());

    /**
     * Prueba: Verificar que el cálculo de tarifa sea correcto para los 3 tipos de vehículos
     */
    @Test
    public void testCalcularTarifaPorTipoVehiculo() {
        LOG.info("Inicio de prueba testCalcularTarifaPorTipoVehiculo...");

        Empresa empresa = new Empresa("Peajes del Quindío");
        Conductor conductor = new Conductor(
                "Juan", "Pérez", "123456",
                LocalDate.of(1990, 1, 1),
                new ArrayList<>(),
                empresa
        );

        Carro carro = new Carro("ABC-123", 0, conductor, false, true);
        Moto moto = new Moto("DEF-001", 0, conductor, 250);
        Camion camion = new Camion("GHI-001", conductor, 0, 12, 3);

        double tarifaCarro = carro.calcularTarifa();
        double tarifaMoto = moto.calcularTarifa();
        double tarifaCamion = camion.calcularTarifa();

        assertEquals(11500.0, tarifaCarro, 0.01,
                "Carro público debe cobrar $11,500 (10,000 × 1.15)");
        assertEquals(7000.0, tarifaMoto, 0.01,
                "Moto 250cc debe cobrar $7,000 (5,000 + 2,000)");
        assertEquals(23100.0, tarifaCamion, 0.01,
                "Camión 3 ejes, 12 ton debe cobrar $23,100 ((7,000 × 3) × 1.10)");

        LOG.info("Fin de prueba testCalcularTarifaPorTipoVehiculo...");
    }
    /**
     * Prueba: Verificar que se obtengan correctamente los camiones con carga > 10 toneladas y peajes > 5
     */
    @Test
    public void testObtenerCamionesCargaMayorDiezYPeajesMayorCinco() {
        LOG.info("Inicio de prueba testObtenerCamionesCargaMayorDiezYPeajesMayorCinco...");

        Empresa empresa = new Empresa("Peajes del Quindío");

        Conductor conductor1 = new Conductor("Juan", "Pérez", "123456", LocalDate.of(1990, 1, 1), new ArrayList<>(), empresa);
        Conductor conductor2 = new Conductor("María", "López", "654321", LocalDate.of(1992, 5, 15), new ArrayList<>(), empresa);
        Conductor conductor3 = new Conductor("Carlos", "García", "789456", LocalDate.of(1988, 8, 20), new ArrayList<>(), empresa);
        Conductor conductor4 = new Conductor("Ana", "Rodríguez", "456789", LocalDate.of(1995, 3, 10), new ArrayList<>(), empresa);

        empresa.agregarConductor(conductor1);
        empresa.agregarConductor(conductor2);
        empresa.agregarConductor(conductor3);
        empresa.agregarConductor(conductor4);

        Camion camion1 = new Camion("STU-901", conductor1, 6, 12, 3);
        Camion camion2 = new Camion("VWX-234", conductor2, 7, 15, 4);
        Camion camion3 = new Camion("YZA-567", conductor3, 2, 8, 2);
        Camion camion4 = new Camion("BCD-890", conductor4, 5, 11, 3);
        Camion camion5 = new Camion("EFG-123", conductor1, 6, 10, 2);
        Camion camion6 = new Camion("HIJ-456", conductor2, 8, 14, 4);

        empresa.agregarVehiculo(camion1);
        empresa.agregarVehiculo(camion2);
        empresa.agregarVehiculo(camion3);
        empresa.agregarVehiculo(camion4);
        empresa.agregarVehiculo(camion5);
        empresa.agregarVehiculo(camion6);

        List<Camion> resultado = empresa.getCamionesCargaMayorYMasDeCincoPeajes();

        assertEquals(3, resultado.size(), "Debe retornar exactamente 3 camiones");

        List<Camion> esperados = List.of(camion1, camion2, camion6);
        assertIterableEquals(esperados, resultado,
                "Los camiones retornados deben ser STU-901, VWX-234 e HIJ-456");

        assertFalse(resultado.contains(camion3), "NO debe incluir YZA-567 (8 ≤ 10)");
        assertFalse(resultado.contains(camion4), "NO debe incluir BCD-890 (5 = 5, no > 5)");
        assertFalse(resultado.contains(camion5), "NO debe incluir EFG-123 (10 = 10, no > 10)");

        LOG.info("Fin de prueba testObtenerCamionesCargaMayorDiezYPeajesMayorCinco...");
    }
}

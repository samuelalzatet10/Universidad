
package co.edu.uniquindio.poo.test;

import static org.junit.jupiter.api.Assertions.*;
import co.edu.unquindio.poo.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.logging.Logger;

/**
 * Clase para probar el sistema de gestión de nómina de empleados
 * @author Samuel Alzate
 * @since 2026-04
 *
 * Licencia GNU/GPL V3.0
 */
public class EmpresaTest {

    /**
     * Instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(EmpresaTest.class.getName());

    /**
     * 1. Verificar que el salario bruto de un empleado de ventas se calcule correctamente
     */
    @Test
    public void testCalcularSalarioBrutoVentas() {
        LOG.info("Inicio de prueba testCalcularSalarioBrutoVentas...");

        EmpleadoVentas empleado = new EmpleadoVentas(
                "Juan", "1093", 30, CategoriaEmpleado.JUNIOR,
                4f, 4f, 1000000f, 100000f, 10f
        );

        // Esperado: salarioBase (1000000) + bonificación JUNIOR 5% (50000) + comisión 10% de 100000 (10000) = 1060000
        float esperado = 1060000f;
        float actual = empleado.calcularSalarioBruto();

        assertEquals(esperado, actual, 0.01f);
        LOG.info("Fin de prueba testCalcularSalarioBrutoVentas...");
    }

    /**
     * 2. Verificar que el salario neto de un empleado temporal no sea igual a 0
     */
    @Test
    public void testSalarioNetoTemporalNoEsCero() {
        LOG.info("Inicio de prueba testSalarioNetoTemporalNoEsCero...");

        EmpleadoTemporal empleado = new EmpleadoTemporal(
                "Ana", "2000", 25, CategoriaEmpleado.SEMI_SENIOR,
                4f, 4f, 1000000f, 20, 50000f
        );

        float salarioNeto = empleado.calcularSalarioNeto();

        assertNotEquals(0f, salarioNeto);
        assertTrue(salarioNeto > 0);
        LOG.info("Fin de prueba testSalarioNetoTemporalNoEsCero...");
    }

    /**
     * 3. Verificar que los empleados almacenados en la empresa sean los correctos
     */
    @Test
    public void testEmpleadosAlmacenadosCorrectamente() {
        LOG.info("Inicio de prueba testEmpleadosAlmacenadosCorrectamente...");

        Empresa empresa = new Empresa("Tech Corp");

        EmpleadoPlanta emp1 = new EmpleadoPlanta(
                "Carlos", "3001", 35, CategoriaEmpleado.SENIOR,
                4f, 4f, 2000000f, "Ingeniero", 10, 20000f, 100000f
        );

        EmpleadoVentas emp2 = new EmpleadoVentas(
                "Maria", "3002", 28, CategoriaEmpleado.JUNIOR,
                4f, 4f, 1500000f, 200000f, 5f
        );

        empresa.registrarEmpleado(emp1);
        empresa.registrarEmpleado(emp2);

        List<Empleado> esperados = List.of(emp1, emp2);
        List<Empleado> actuales = empresa.getListaEmpleados();

        assertIterableEquals(esperados, actuales);
        LOG.info("Fin de prueba testEmpleadosAlmacenadosCorrectamente...");
    }

    /**
     * 4. Verificar que la bonificación de un empleado JUNIOR sea mayor que 0
     */
    @Test
    public void testBonificacionJuniorMayorQueCero() {
        LOG.info("Inicio de prueba testBonificacionJuniorMayorQueCero...");

        EmpleadoPlanta empleado = new EmpleadoPlanta(
                "Pedro", "4001", 22, CategoriaEmpleado.JUNIOR,
                4f, 4f, 1000000f, "Auxiliar", 0, 0f, 0f
        );

        float bonificacion = empleado.calcularBonificacionCategoria();

        assertTrue(bonificacion > 0);
        assertEquals(50000f, bonificacion, 0.01f); // 5% de 1000000
        LOG.info("Fin de prueba testBonificacionJuniorMayorQueCero...");
    }

    /**
     * 5. Verificar que el salario neto nunca sea negativo
     */
    @Test
    public void testSalarioNetoNuncaNegativo() {
        LOG.info("Inicio de prueba testSalarioNetoNuncaNegativo...");

        EmpleadoTemporal empleado = new EmpleadoTemporal(
                "Luis", "5001", 20, CategoriaEmpleado.JUNIOR,
                4f, 4f, 500000f, 5, 10000f
        );

        float salarioNeto = empleado.calcularSalarioNeto();

        assertTrue(salarioNeto >= 0, "El salario neto no debe ser negativo");
        LOG.info("Fin de prueba testSalarioNetoNuncaNegativo...");
    }

    /**
     * 6. Verificar que la búsqueda de un empleado inexistente retorne null
     */
    @Test
    public void testBusquedaEmpleadoInexistenteRetornaNull() {
        LOG.info("Inicio de prueba testBusquedaEmpleadoInexistenteRetornaNull...");

        Empresa empresa = new Empresa("Innovatech");

        Empleado encontrado = empresa.buscarEmpleadoPorDocumento("99999");

        assertNull(encontrado);
        LOG.info("Fin de prueba testBusquedaEmpleadoInexistenteRetornaNull...");
    }

    /**
     * 7. Verificar que se lance excepción si el salario base es negativo
     */
    @Test
    public void testExcepcionSalarioBaseNegativo() {
        LOG.info("Inicio de prueba testExcepcionSalarioBaseNegativo...");

        assertThrows(IllegalArgumentException.class, () -> {
            new EmpleadoPlanta(
                    "Error", "7001", 30, CategoriaEmpleado.JUNIOR,
                    4f, 4f, -1000f, "Cargo", 0, 0f, 0f
            );
        });

        LOG.info("Fin de prueba testExcepcionSalarioBaseNegativo...");
    }

    /**
     * 8. Verificar que el método empleadosConSalarioMayorA(valor) retorne la lista correcta
     */
    @Test
    public void testEmpleadosConSalarioMayorA() {
        LOG.info("Inicio de prueba testEmpleadosConSalarioMayorA...");

        Empresa empresa = new Empresa("DataSoft");

        EmpleadoPlanta emp1 = new EmpleadoPlanta(
                "Alto", "8001", 40, CategoriaEmpleado.SENIOR,
                4f, 4f, 3000000f, "Director", 0, 0f, 200000f
        );

        EmpleadoVentas emp2 = new EmpleadoVentas(
                "Medio", "8002", 30, CategoriaEmpleado.SEMI_SENIOR,
                4f, 4f, 1500000f, 100000f, 5f
        );

        EmpleadoTemporal emp3 = new EmpleadoTemporal(
                "Bajo", "8003", 25, CategoriaEmpleado.JUNIOR,
                4f, 4f, 800000f, 10, 20000f
        );

        empresa.registrarEmpleado(emp1);
        empresa.registrarEmpleado(emp2);
        empresa.registrarEmpleado(emp3);

        List<Empleado> resultado = empresa.empleadosConSalarioMayorA(2000000f);

        assertEquals(1, resultado.size());
        assertEquals("Alto", resultado.get(0).getNombre());
        LOG.info("Fin de prueba testEmpleadosConSalarioMayorA...");
    }

    /**
     * 9. Comprobar que buscarPorDocumento() retorne null cuando se busque documento inexistente
     */
    @Test
    public void testBuscarPorDocumentoInexistente() {
        LOG.info("Inicio de prueba testBuscarPorDocumentoInexistente...");

        Empresa empresa = new Empresa("SoftwareLab");

        EmpleadoPlanta emp = new EmpleadoPlanta(
                "Existe", "9001", 30, CategoriaEmpleado.JUNIOR,
                4f, 4f, 1000000f, "Dev", 0, 0f, 0f
        );

        empresa.registrarEmpleado(emp);

        Empleado noExiste = empresa.buscarEmpleadoPorDocumento("9999");

        assertNull(noExiste);
        LOG.info("Fin de prueba testBuscarPorDocumentoInexistente...");
    }

    /**
     * 10. Validar que el sistema no permita agregar empleados duplicados
     */
    @Test
    public void testNoPermiteEmpleadosDuplicados() {
        LOG.info("Inicio de prueba testNoPermiteEmpleadosDuplicados...");

        Empresa empresa = new Empresa("UniqueStaff");

        EmpleadoPlanta emp1 = new EmpleadoPlanta(
                "Original", "1001", 30, CategoriaEmpleado.JUNIOR,
                4f, 4f, 1000000f, "Dev", 0, 0f, 0f
        );

        EmpleadoVentas emp2 = new EmpleadoVentas(
                "Duplicado", "1001", 25, CategoriaEmpleado.SENIOR,
                4f, 4f, 1500000f, 100000f, 5f
        );

        String resultado1 = empresa.registrarEmpleado(emp1);
        String resultado2 = empresa.registrarEmpleado(emp2);

        assertTrue(resultado1.contains("exitosamente"));
        assertTrue(resultado2.contains("ya existe"));
        assertEquals(1, empresa.getListaEmpleados().size());
        LOG.info("Fin de prueba testNoPermiteEmpleadosDuplicados...");
    }

    /**
     * 11. Verificar que el método que obtiene el empleado con mayor salario esté correcto
     */
    @Test
    public void testEmpleadoConMayorSalario() {
        LOG.info("Inicio de prueba testEmpleadoConMayorSalario...");

        Empresa empresa = new Empresa("TopPay");

        EmpleadoPlanta emp1 = new EmpleadoPlanta(
                "Menor", "11001", 30, CategoriaEmpleado.JUNIOR,
                4f, 4f, 1000000f, "Jr Dev", 0, 0f, 0f
        );

        EmpleadoPlanta emp2 = new EmpleadoPlanta(
                "Mayor", "11002", 45, CategoriaEmpleado.SENIOR,
                4f, 4f, 5000000f, "CTO", 20, 50000f, 300000f
        );

        EmpleadoVentas emp3 = new EmpleadoVentas(
                "Medio", "11003", 35, CategoriaEmpleado.SEMI_SENIOR,
                4f, 4f, 2000000f, 500000f, 10f
        );

        empresa.registrarEmpleado(emp1);
        empresa.registrarEmpleado(emp2);
        empresa.registrarEmpleado(emp3);

        String resultado = empresa.mostrarEmpleadoMayorSalario();

        assertTrue(resultado.contains("Mayor"));
        LOG.info("Fin de prueba testEmpleadoConMayorSalario...");
    }

    /**
     * 12. Verificar que el método que obtiene empleados temporales con más de 100 días es correcto
     */
    @Test
    public void testEmpleadosTemporalesMasDe100Dias() {
        LOG.info("Inicio de prueba testEmpleadosTemporalesMasDe100Dias...");

        Empresa empresa = new Empresa("TempWork");

        EmpleadoTemporal emp1 = new EmpleadoTemporal(
                "Corto", "12001", 25, CategoriaEmpleado.JUNIOR,
                4f, 4f, 800000f, 50, 30000f
        );

        EmpleadoTemporal emp2 = new EmpleadoTemporal(
                "Largo1", "12002", 30, CategoriaEmpleado.SEMI_SENIOR,
                4f, 4f, 900000f, 150, 35000f
        );

        EmpleadoTemporal emp3 = new EmpleadoTemporal(
                "Largo2", "12003", 28, CategoriaEmpleado.JUNIOR,
                4f, 4f, 850000f, 120, 32000f
        );

        empresa.registrarEmpleado(emp1);
        empresa.registrarEmpleado(emp2);
        empresa.registrarEmpleado(emp3);

        List<EmpleadoTemporal> resultado = empresa.empleadosTemporalesMasDe100Dias();

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(e -> e.getDiasTrabajados() > 100));
        LOG.info("Fin de prueba testEmpleadosTemporalesMasDe100Dias...");
    }

    /**
     * 13. Verificar que calcularSalarioNeto() de empleado de planta sea mayor que su salario base
     */
    @Test
    public void testSalarioNetoPlantaMayorQueBase() {
        LOG.info("Inicio de prueba testSalarioNetoPlantaMayorQueBase...");

        EmpleadoPlanta empleado = new EmpleadoPlanta(
                "Planta", "13001", 35, CategoriaEmpleado.SENIOR,
                4f, 4f, 2000000f, "Gerente", 10, 30000f, 150000f
        );

        float salarioBase = empleado.getSalarioBase();
        float salarioNeto = empleado.calcularSalarioNeto();

        assertTrue(salarioNeto > salarioBase,
                "El salario neto debe ser mayor al base debido a bonificaciones y auxilio");
        LOG.info("Fin de prueba testSalarioNetoPlantaMayorQueBase...");
    }

    /**
     * 14. Verificar que el salario neto de empleado temporal sea mayor a cero con datos válidos
     */
    @Test
    public void testSalarioNetoTemporalMayorQueCero() {
        LOG.info("Inicio de prueba testSalarioNetoTemporalMayorQueCero...");

        EmpleadoTemporal empleado = new EmpleadoTemporal(
                "Temporal", "14001", 22, CategoriaEmpleado.JUNIOR,
                4f, 4f, 800000f, 30, 40000f
        );

        float salarioNeto = empleado.calcularSalarioNeto();

        assertTrue(salarioNeto > 0, "El salario neto debe ser mayor a cero con días y valor válidos");
        assertTrue(empleado.getDiasTrabajados() > 0);
        assertTrue(empleado.getValorDia() > 0);
        LOG.info("Fin de prueba testSalarioNetoTemporalMayorQueCero...");
    }
}
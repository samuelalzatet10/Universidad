package co.edu.unquindio.poo.model;

public class EmpleadoVentas extends Empleado {
    private float totalVentas;
    private float porcentajeComision;

    public EmpleadoVentas(String nombre, String documento, int edad, CategoriaEmpleado categoria,
                          float descuentoPension, float descuentoSalud, float salarioBase,
                          float totalVentas, float porcentajeComision) {

        super(nombre, documento, edad, categoria, descuentoPension, descuentoSalud, salarioBase);
        if (porcentajeComision < 0 || porcentajeComision > 100) {
            throw new IllegalArgumentException("El porcentaje de comisión debe estar entre 0 y 100.");
        }
        this.totalVentas = totalVentas;
        this.porcentajeComision = porcentajeComision;
    }

    public float getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(float totalVentas) {
        this.totalVentas = totalVentas;
    }

    public float getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(float porcentajeComision) {
        if (porcentajeComision < 0 || porcentajeComision > 100) {
            throw new IllegalArgumentException("El porcentaje de comisión debe estar entre 0 y 100.");
        }
        this.porcentajeComision = porcentajeComision;
    }

    @Override
    public float calcularSalarioBruto() {
        float comision = totalVentas * (porcentajeComision / 100f);
        return salarioBase + calcularBonificacionCategoria() + comision;
    }

    @Override
    public String mostrarInformacion() {
        String info = super.mostrarInformacion();
        info += "Total Ventas: $" + totalVentas + "\n";
        info += "Porcentaje Comisión: " + porcentajeComision + "%\n";
        return info;
    }
}
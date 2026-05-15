package co.edu.unquindio.poo.model;

public class EmpleadoTemporal extends Empleado {
    private int diasTrabajados;
    private float valorDia;

    public EmpleadoTemporal(String nombre, String documento, int edad, CategoriaEmpleado categoria,
                            float descuentoPension, float descuentoSalud, float salarioBase,
                            int diasTrabajados, float valorDia) {

        super(nombre, documento, edad, categoria, descuentoPension, descuentoSalud, salarioBase);

        if (diasTrabajados < 0) {
            throw new IllegalArgumentException("Los días trabajados no pueden ser negativos.");
        }
        if (valorDia < 0) {
            throw new IllegalArgumentException("El valor del día no puede ser negativo.");
        }
        this.diasTrabajados = diasTrabajados;
        this.valorDia = valorDia;
    }
        public int getDiasTrabajados() {
            return diasTrabajados;
        }

        public void setDiasTrabajados(int diasTrabajados) {
            if (diasTrabajados < 0) {
                throw new IllegalArgumentException("Los días trabajados no pueden ser negativos.");
            }
            this.diasTrabajados = diasTrabajados;
        }

        public float getValorDia() {
            return valorDia;
        }

        public void setValorDia(float valorDia) {
            if (valorDia < 0) {
                throw new IllegalArgumentException("El valor del día no puede ser negativo.");
            }
            this.valorDia = valorDia;
        }

    public float calcularSalarioBruto() {
        // Regla: (días * valor del día) + bonificación por categoría
        return (diasTrabajados * valorDia) + calcularBonificacionCategoria();
    }

    @Override
    public String mostrarInformacion() {
        String info = super.mostrarInformacion();
        info += "Días Trabajados: " + diasTrabajados + "\n";
        info += "Valor por Día: $" + valorDia + "\n";
        return info;
    }

}

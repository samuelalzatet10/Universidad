package co.edu.unquindio.poo.model;

public abstract class Empleado {

    protected String nombre;
    protected String documento;
    protected int edad;
    protected float salarioBase;
    protected CategoriaEmpleado categoria;
    protected float descuentoSalud;
    protected float descuentoPension;

    public Empleado(String nombre, String documento,
                    int edad, CategoriaEmpleado categoria,
                    float descuentoPension, float descuentoSalud,
                    float salarioBase) {

        if (salarioBase < 0) throw new IllegalArgumentException("El salario base no puede ser negativo.");
        if (descuentoSalud < 0 || descuentoSalud > 100)
            throw new IllegalArgumentException("El porcentaje de descuento de salud debe estar entre 0 y 100.");
        if (descuentoPension < 0 || descuentoPension > 100)
            throw new IllegalArgumentException("El porcentaje de descuento de pensión debe estar entre 0 y 100.");

        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.categoria = categoria;
        this.descuentoPension = descuentoPension;
        this.descuentoSalud = descuentoSalud;
        this.salarioBase = salarioBase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(float salarioBase) {
        if (salarioBase < 0)
            throw new IllegalArgumentException("El salario base no puede ser negativo.");
        this.salarioBase = salarioBase;
    }

    public CategoriaEmpleado getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEmpleado categoria) {
        this.categoria = categoria;
    }

    public float getDescuentoSalud() {
        return descuentoSalud;
    }

    public void setDescuentoSalud(float descuentoSalud) {
        if (descuentoSalud < 0 || descuentoSalud > 100)
            throw new IllegalArgumentException("El porcentaje de descuento de salud debe estar entre 0 y 100.");
        this.descuentoSalud = descuentoSalud;
    }

    public float getDescuentoPension() {
        return descuentoPension;
    }

    public void setDescuentoPension(float descuentoPension) {
        if (descuentoPension < 0 || descuentoPension > 100)
            throw new IllegalArgumentException("El porcentaje del descuento de pension debe estar entre 0 y 100.");
        this.descuentoPension = descuentoPension;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + nombre + " | Doc: " + documento
                + " | Categoría: " + categoria
                + " | Salario Neto: $" + String.format("%.2f", calcularSalarioNeto()) + "]";
    }

    public abstract float calcularSalarioBruto();

    public float calcularBonificacionCategoria() {
        return switch (categoria) {
            case JUNIOR -> salarioBase * 0.05f;
            case SEMI_SENIOR -> salarioBase * 0.10f;
            case SENIOR -> salarioBase * 0.15f;
        };
    }

    public float calcularDescuentos() {
        float bruto = calcularSalarioBruto();
        return bruto * (descuentoSalud / 100f) + bruto * (descuentoPension / 100f);
    }

    public float calcularSalarioNeto() {
        return calcularSalarioBruto() - calcularDescuentos();
    }

    public ResumenPago generarResumenPago() {
        return new ResumenPago(
                documento, nombre, this.getClass().getSimpleName(), calcularSalarioBruto(), calcularDescuentos(), calcularSalarioNeto());
    }


    public String mostrarInformacion() {
        String info = "------------------------------------------\n";
        info += "INFORMACIÓN DEL EMPLEADO (" + this.getClass().getSimpleName() + ")\n";
        info += "Nombre: " + nombre + "\n";
        info += "Documento: " + documento + "\n";
        info += "Edad: " + edad + "\n";
        info += "Categoría: " + categoria + "\n";
        info += "Salario Base: $" + salarioBase + "\n";
        info += "Salario Bruto: $" + calcularSalarioBruto() + "\n";
        info += "Descuentos Aplicados: $" + calcularDescuentos() + "\n";
        info += "Bonificación Categoría: $" + calcularBonificacionCategoria() + "\n";
        info += "Salario Neto a Recibir: $" + calcularSalarioNeto() + "\n";
        return info;
    }

}

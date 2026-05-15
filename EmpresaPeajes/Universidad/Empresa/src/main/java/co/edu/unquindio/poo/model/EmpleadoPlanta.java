package co.edu.unquindio.poo.model;

public class EmpleadoPlanta extends Empleado {
    private String cargo ;
    private int horasExtra;
    private float valorHoraExtra;
    private float auxilioTransporte ;

    public EmpleadoPlanta(String nombre, String documento,
                          int edad, CategoriaEmpleado categoria,
                          float descuentoPension, float descuentoSalud,
                          float salarioBase, String cargo,
                          int horasExtra, float valorHoraExtra,
                          float auxilioTransporte) {

        super(nombre, documento, edad, categoria, descuentoPension, descuentoSalud, salarioBase);
        if (horasExtra < 0) {
            throw new IllegalArgumentException("Las horas extra no pueden ser negativas.");
        }
        if (valorHoraExtra < 0) {
            throw new IllegalArgumentException(" el valor de la hora extra no puede ser negativo.");
        }
        this.cargo = cargo;
        this.horasExtra = horasExtra;
        this.valorHoraExtra = valorHoraExtra;
        this.auxilioTransporte = auxilioTransporte;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(int horasExtra) {
        if (horasExtra < 0) throw new IllegalArgumentException("Las horas extra no pueden ser negativas.");
        this.horasExtra = horasExtra;
    }

    public float getValorHoraExtra() {
        return valorHoraExtra;
    }

    public void setValorHoraExtra(float valorHoraExtra) {
        if (valorHoraExtra < 0)
            throw new IllegalArgumentException("El valor de la hora extra no puede ser negativo.");
        this.valorHoraExtra = valorHoraExtra;
    }

    public float getAuxilioTransporte() {
        return auxilioTransporte;
    }

    public void setAuxilioTransporte(float auxilioTransporte) {
        this.auxilioTransporte = auxilioTransporte;
    }

    @Override
    public String mostrarInformacion() {
        String info = super.mostrarInformacion();
        info += "Cargo: " + cargo + "\n";
        info += "Horas Extra: " + horasExtra + "\n";
        info += "Valor Hora Extra: $" + valorHoraExtra + "\n";
        info += "Auxilio Transporte: $" + auxilioTransporte + "\n";

        return info;
    }
    @Override
    public float calcularSalarioBruto() {
        return salarioBase
                + calcularBonificacionCategoria()
                + (horasExtra * valorHoraExtra)
                + auxilioTransporte;
    }

}



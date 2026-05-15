package co.edu.unquindio.poo.model;

public class Moto extends Vehiculo {
    private int cilindraje;

    public Moto(String placa, int numeroPeajes, Conductor propietario, int cilindraje){
        super(placa, numeroPeajes, propietario);
        this.cilindraje= cilindraje;
    }
    public int getCilindraje(){
        return cilindraje;
    }
    public void setCilindraje(int cilindraje){
        this.cilindraje= cilindraje;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "cilindraje=" + cilindraje +
                '}';
    }
    @Override
    public double calcularTarifa() {
        double tarifa = 5000;
        if (cilindraje > 200) tarifa += 2000;
        return tarifa;
    }
}

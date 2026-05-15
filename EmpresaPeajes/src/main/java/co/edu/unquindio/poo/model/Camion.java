package co.edu.unquindio.poo.model;

public class Camion extends Vehiculo {
    private double carga;
    private int ejes;

    public Camion(String placa, Conductor propietario,int numeroPeajes, double carga, int ejes) {
        super(placa, numeroPeajes,propietario);
        this.carga = carga;
        this.ejes = ejes;
    }
    public double getCarga() {
        return carga;
    }
    public void setCarga(double carga) {
        this.carga = carga;
    }

    public int getEjes() {
        return ejes;
    }
    public void setEjes(int ejes) {
        this.ejes = ejes;
    }

    @Override
    public String toString() {
        return "Camion{" + super.toString() +
                ", carga=" + carga +
                ", ejes=" + ejes + '}';
    }
    @Override
    public double calcularTarifa() {
        double tarifa = 7000 * ejes;
        if (carga > 10) tarifa *= 1.10;
        return tarifa;
    }
}


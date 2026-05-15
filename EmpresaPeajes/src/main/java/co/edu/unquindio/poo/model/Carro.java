package co.edu.unquindio.poo.model;

public class Carro extends Vehiculo {
    private boolean esElectrico;
    private boolean esPublico;

    public Carro(String placa, int numeroPeajes, Conductor propietario, boolean esElectrico, boolean esPublico) {
        super(placa, numeroPeajes, propietario);
        this.esElectrico = esElectrico;
        this.esPublico = esPublico;
    }
    public boolean getEsElectrico(){
        return esElectrico;
    }
    public void setEsElectrico(boolean esElectrico){
        this.esElectrico= esElectrico;
    }

    public boolean getsEsPublico() {
        return esPublico;
    }
    public void setEsPublico(boolean esPublico){
        this.esPublico= esPublico;
    }

    @Override
    public double calcularTarifa() {
        double tarifa = 10000;
        if (esElectrico) tarifa *= 0.80;
        if (esPublico) tarifa *= 1.15;
        return tarifa;
    }
}

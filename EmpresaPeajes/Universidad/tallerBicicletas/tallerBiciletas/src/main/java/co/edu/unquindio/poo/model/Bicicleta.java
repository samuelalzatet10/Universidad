package co.edu.unquindio.poo.model;

import java.util.Objects;

public class Bicicleta {
    private String id;
    private String marca;
    private String modelo;

    public Bicicleta(String id, String marca, String modelo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }

    public void setId(String id) { this.id = id; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bicicleta bicicleta = (Bicicleta) o;
        return Objects.equals(id, bicicleta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Bicicleta [ID=" + id+ ", Marca=" + marca + ", Modelo=" + modelo + "]";
    }
}

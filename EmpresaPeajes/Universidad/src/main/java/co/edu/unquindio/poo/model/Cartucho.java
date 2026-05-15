package co.edu.unquindio.poo.model;

public class Cartucho implements Impresora {
    private String nombre;
    private String codigo;
    private int nivelTinta;
    private String color;

    public Cartucho( String nombre,String codigo, int nivelTinta, String color) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.nivelTinta = nivelTinta;
        this.color = color;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getNivelTinta() {
        return nivelTinta;
    }

    public void setNivelTinta(int nivelTinta) {
        this.nivelTinta = nivelTinta;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cartucho{" +"nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", nivelTinta=" + nivelTinta +
                ", color='" + color + '\'' +
                '}';
    }


    @Override
    public void imprimir(Documento documento) {
    }
}

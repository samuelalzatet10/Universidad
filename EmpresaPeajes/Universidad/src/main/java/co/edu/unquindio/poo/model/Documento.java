package co.edu.unquindio.poo.model;

public class Documento {
    private String nombre;
    private String contenido;
    private AreaEmpresa areaEmpresa;

    public Documento(String nombre, String contenido) {
        this.nombre = nombre;
        this.contenido = contenido;

    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public AreaEmpresa getAreaEmpresa() {
        return areaEmpresa;
    }
    public void setAreaEmpresa(AreaEmpresa areaEmpresa) {
        this.areaEmpresa = areaEmpresa;
    }
    @Override
    public String toString() {
        return "Documento{" +
                "nombre='" + nombre + '\'' +
                ", contenido='" + contenido + '\'' +
                ", area='" + areaEmpresa.getNombre() + '\'' +
                '}';
    }
}


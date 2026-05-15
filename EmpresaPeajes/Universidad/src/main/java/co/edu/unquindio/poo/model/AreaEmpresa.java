package co.edu.unquindio.poo.model;
import java.util.List;
import java.util.ArrayList;

public class AreaEmpresa {
    private String nombre;
    private String codigo;
    private List<Documento>listaDocumentos;

    public AreaEmpresa(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.listaDocumentos = new ArrayList<>();
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
    public List<Documento> getListaDocumentos() {
        return listaDocumentos;
    }
    public void setListaDocumentos(List<Documento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }
    @Override
    public String toString() {
        return "AreaEmpresa{" +
                "nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", totalDocumentos=" + listaDocumentos.size() +
                '}';
    }
    public void enviarSolicitud(CentroImpresion centroImpresion, Documento documento) {
        centroImpresion.recibirSolicitud(documento, this);
    }
}


package co.edu.unquindio.poo.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CentroImpresion {
    private String codigo;
    private List<Impresora> listaImpresoras;
    private Queue<Documento> listaSolicitudes;
    private List<AreaEmpresa> listaAreasEmpresa;

    public CentroImpresion(String codigo) {
        this.codigo = codigo;
        this.listaImpresoras = new ArrayList<>();
        this.listaSolicitudes = new LinkedList<>();
        this.listaAreasEmpresa = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Impresora> getListaImpresoras() {
        return listaImpresoras;
    }

    public void setListaImpresoras(List<Impresora> listaImpresoras) {
        this.listaImpresoras = listaImpresoras;
    }

    public Queue<Documento> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(Queue<Documento> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public List<AreaEmpresa> getListaAreasEmpresa() {
        return listaAreasEmpresa;
    }

    public void setListaAreasEmpresa(List<AreaEmpresa> listaAreasEmpresa) {
        this.listaAreasEmpresa = listaAreasEmpresa;
    }

    @Override
    public String toString() {
        return "CentroImpresion{" +
                "codigo='" + codigo + '\'' +
                ", totalImpresoras=" + listaImpresoras.size() +
                ", solicitudesEnCola=" + listaSolicitudes.size() +
                '}';
    }
    public void recibirSolicitud(Documento documento, AreaEmpresa areaEmpresa) {
        listaSolicitudes.offer(documento);
    }

    public String procesarSolicitud() {
        if (listaSolicitudes.isEmpty()) {
            return "No hay solicitudes en cola.";
        }

        Impresora impresoraDisponible = null;
        for (Impresora impresora : listaImpresoras) {
            impresoraDisponible = impresora;
            break;
        }

        if (impresoraDisponible == null) {
            return "No hay impresoras registradas en el centro.";
        }

        Documento documento = listaSolicitudes.poll();
        return imprimir(impresoraDisponible, documento);
    }

    public String imprimir(Impresora impresora, Documento documento) {
        impresora.imprimir(documento);
        return "Documento '" + documento.getNombre() + "' impreso por '" + impresora.getNombre() + "'.";
    }

    public String conectarImpresora(String codigo) {
        for (Impresora impresora : listaImpresoras) {
            if (impresora.getCodigo().equals(codigo)) {
                return "Impresora '" + impresora.getNombre() + "' conectada.";
            }
        }
        return "No se encontró la impresora con código '" + codigo + "'.";
    }

    public String desconectarImpresora(String codigo) {
        for (Impresora impresora : listaImpresoras) {
            if (impresora.getCodigo().equals(codigo)) {
                return "Impresora '" + impresora.getNombre() + "' desconectada.";
            }
        }
        return "No se encontró la impresora con código '" + codigo + "'.";
    }

    public String verEstadoImpresion() {
        if (listaImpresoras.isEmpty()) {
            return "No hay impresoras registradas en el centro.";
        }
        String estado = "=== Estado del Centro de Impresión ===\n";
        for (Impresora impresora : listaImpresoras) {
            estado += impresora.toString() + "\n";
        }
        estado += "Solicitudes en cola: " + listaSolicitudes.size();
        return estado;
    }

    public String verSolicitudes() {
        if (listaSolicitudes.isEmpty()) {
            return "No hay solicitudes en cola.";
        }
        String resultado = "=== Solicitudes en Cola (orden de llegada) ===\n";
        int pos = 1;
        for (Documento doc : listaSolicitudes) {
            resultado += pos++ + ". " + doc.toString() + "\n";
        }
        return resultado;
    }
}

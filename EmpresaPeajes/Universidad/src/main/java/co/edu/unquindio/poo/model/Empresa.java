package co.edu.unquindio.poo.model;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombre;
    private CentroImpresion centroImpresion;
    private List<AreaEmpresa> listaAreasEmpresa;
    private List<Impresora> listaImpresoras;
    private List<Documento> listaDocumentos;

    public Empresa(String nombre, String codigoCentroImpresion) {
        this.nombre = nombre;
        this.centroImpresion = new CentroImpresion(codigoCentroImpresion);
        this.listaAreasEmpresa = new ArrayList<>();
        this.listaImpresoras = new ArrayList<>();
        this.listaDocumentos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CentroImpresion getCentroImpresion() {
        return centroImpresion;
    }

    public void setCentroImpresion(CentroImpresion centroImpresion) {
        this.centroImpresion = centroImpresion;
    }

    public List<AreaEmpresa> getListaAreasEmpresa() {
        return listaAreasEmpresa;
    }

    public void setListaAreasEmpresa(List<AreaEmpresa> listaAreasEmpresa) {
        this.listaAreasEmpresa = listaAreasEmpresa;
    }

    public List<Impresora> getListaImpresoras() {
        return listaImpresoras;
    }

    public void setListaImpresoras(List<Impresora> listaImpresoras) {
        this.listaImpresoras = listaImpresoras;
    }

    public List<Documento> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<Documento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nombre='" + nombre + '\'' +
                ", totalAreas=" + listaAreasEmpresa.size() +
                ", totalImpresoras=" + listaImpresoras.size() +
                ", totalDocumentos=" + listaDocumentos.size() +
                ", centroImpresion='" + centroImpresion.getCodigo() + '\'' +
                '}';
    }
    // CRUD AREAS EMPRESA
    public boolean crearArea(String nombre, String codigo) {
        for (AreaEmpresa area : listaAreasEmpresa) {
            if (area.getCodigo().equals(codigo)) {
                return false;
            }
        }
        AreaEmpresa nuevaArea = new AreaEmpresa(nombre, codigo);
        listaAreasEmpresa.add(nuevaArea);
        return true;
    }

    public AreaEmpresa consultarArea(String codigo) {
        for (AreaEmpresa area : listaAreasEmpresa) {
            if (area.getCodigo().equals(codigo)) {
                return area;
            }
        }
        return null;
    }

    public boolean actualizarArea(String codigo, String nuevoNombre) {
        AreaEmpresa areaEncontrada = consultarArea(codigo);
        if (areaEncontrada != null) {
            areaEncontrada.setNombre(nuevoNombre);
            return true;
        }
        return false;
    }

    // CRUD IMPRESORA
    public boolean agregarImpresora(Impresora impresora) {
        for (Impresora imp : listaImpresoras) {
            if (imp.getCodigo().equals(impresora.getCodigo())) {
                return false;
            }
        }
        listaImpresoras.add(impresora);
        centroImpresion.getListaImpresoras().add(impresora);
        return true;
    }

    public Impresora consultarImpresora(String codigo) {
        for (Impresora imp : listaImpresoras) {
            if (imp.getCodigo().equals(codigo)) {
                return imp;
            }
        }
        return null;
    }

    public boolean eliminarImpresora(String codigo) {
        Impresora impresoraEncontrada = consultarImpresora(codigo);
        if (impresoraEncontrada != null) {
            listaImpresoras.remove(impresoraEncontrada);
            centroImpresion.getListaImpresoras().remove(impresoraEncontrada);
            return true;
        }
        return false;
    }

    // CRUD DOCUMENTOS
    public boolean crearDocumento(String nombre, String contenido, AreaEmpresa areaEmpresa) {
        for (Documento doc : listaDocumentos) {
            if (doc.getNombre().equals(nombre)) {
                return false;
            }
        }
        Documento nuevoDocumento = new Documento(nombre, contenido);
        nuevoDocumento.setAreaEmpresa(areaEmpresa);
        listaDocumentos.add(nuevoDocumento);
        return true;
    }

    public Documento consultarDocumentoGlobal(String nombre) {
        for (Documento doc : listaDocumentos) {
            if (doc.getNombre().equals(nombre)) {
                return doc;
            }
        }
        return null;
    }

    public boolean actualizarDocumento(String nombre, String nuevoContenido) {
        Documento documentoEncontrado = consultarDocumentoGlobal(nombre);
        if (documentoEncontrado != null) {
            documentoEncontrado.setContenido(nuevoContenido);
            return true;
        }
        return false;
    }

    public boolean eliminarDocumento(String nombre) {
        Documento documentoEncontrado = consultarDocumentoGlobal(nombre);
        if (documentoEncontrado != null) {
            listaDocumentos.remove(documentoEncontrado);
            return true;
        }
        return false;
    }
}
package co.edu.unquindio.poo.model;

public class Push implements TipoNotificacion{
    private String id;

    public Push(String id){
        super();
        this.id= id;

    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Push{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public EstadoNoticia enviarNotificacion() {
        return EstadoNoticia.ENVIADA;
    }
}

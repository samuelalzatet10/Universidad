package co.edu.unquindio.poo.model;

public class Email implements TipoNotificacion {
    private String correo;

    public Email(String correo){
        this.correo= correo;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    @Override
    public String toString() {
        return "Email{" +
                "correo='" + correo + '\'' +
                '}';
    }
    @Override
    public EstadoNoticia enviarNotificacion() {
        return EstadoNoticia.ENVIADA;
    }
}

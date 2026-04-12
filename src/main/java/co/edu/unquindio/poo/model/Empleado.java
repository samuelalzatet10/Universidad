package co.edu.unquindio.poo.model;
    import java.time.LocalTime;

    public class Empleado {
        private String nombre;
        private String cedula;
        private LocalTime horaEntrada;
        private LocalTime horaSalida;

        private Empresa ownedByEmpresa;

        public Empleado(String nombre, String cedula, LocalTime horaEntrada, LocalTime horaSalida) {
            this.nombre = nombre;
            this.cedula = cedula;
            this.horaEntrada = horaEntrada;
            this.horaSalida = horaSalida;
        }
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public String getCedula() {
            return cedula;
        }
        public void setCedula(String cedula) {
            this.cedula = cedula;
        }
        public LocalTime getHoraEntrada() {
            return horaEntrada;
        }
        public void setHoraEntrada(LocalTime horaEntrada) {
            this.horaEntrada = horaEntrada;
        }
        public LocalTime getHoraSalida() {
            return horaSalida;
        }
        public void setHoraSalida(LocalTime horaSalida) {
            this.horaSalida = horaSalida;
        }
        public Empresa getOwnedByEmpresa() {
            return ownedByEmpresa;
        }
        public void setOwnedByEmpresa(Empresa ownedByEmpresa) {
            this.ownedByEmpresa = ownedByEmpresa;
        }
        public boolean llegoTarde(LocalTime horaEntradaEmpresa) {
            boolean resultado = false;
            if(horaEntrada.isAfter(horaEntradaEmpresa)) {
                resultado = true;
            }
            return resultado;
        }
        @Override
        public String toString() {
            return "Empleado [nombre=" + nombre + ", cedula=" + cedula +
                    ", entrada=" + horaEntrada + ", salida=" + horaSalida + "]";
        }
    }



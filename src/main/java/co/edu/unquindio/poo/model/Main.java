package co.edu.unquindio.poo.model;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        // Cliente y su bicicleta

        Cliente a1= new Cliente ("Samuel Alzate","7547786","315-3558714");
        Bicicleta b1= new Bicicleta("Trek","Snake");
        a1.addBicicleta(b1);

        // Registrar mecánico
        Mecanico a2= new Mecanico("Daniel Ceballos","A-31");

        //Crear Servicio (Fecha en String)

        Servicio s1= new Servicio ("Mantenimiento frenos","01/03/2026","A-201","Recibida", b1);

        // 4. Operaciones
               s1.asignarMecanico(a2);
            s1.cambiarEstado("En proceso");

             s1.agregarTarea(new Tarea("Ajuste de pastillas", 15000));
               s1.agregarRepuesto(new Repuesto("Líquido de frenos", "LF-01", 1, 10000));

            // 5. Finalizar
             s1.cambiarEstado("Finalizado");

               // MOSTRAR RESULTADOS EN VENTANAS
               JOptionPane.showMessageDialog(null, "--- REGISTRO EXITOSO ---\n" + a1.toString());

             String reporteFinal = "--- REPORTE DE SERVICIO ---\n" +
                     "Código Servicio: " + s1.getCodigoServicio() + "\n" +
                     "Bicicleta: " + s1.getBicicleta().getMarca() + " " + s1.getBicicleta().getModelo() + "\n" +
                     "Mecánico: " + s1.getMecanico().getNombre() + "\n" +
                     "Estado: " + s1.getEstado() + "\n" +
                     "---------------------------\n" +
                     "TOTAL A PAGAR: $" + String.format("%,.0f", s1.costoTotal());

             JOptionPane.showMessageDialog(null, reporteFinal);
           }
        }




import javax.swing.*;
public class Arreglos {
    public static void main(String[] args) {
        int cantidadNotas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de notas"));
        double[] notas = new double[cantidadNotas];
        for (int i = 0; i < cantidadNotas; i++) {
            notas[i] = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota " + (i + 1)));

        }
        double prom = promedio(notas);
        double max = numeroMayor(notas);
        double min = numeroMenor(notas);
        double perdidas = notasPerdidas(notas);
        numerosOrdenado(notas);
        String mensaje = " Analisis de las notas \n\n";
        mensaje += "Promedio: " + String.format("%.2f", prom) + "\n";
        mensaje += "Nota más alta: " + max + "\n";
        mensaje += "Nota más baja: " + min + "\n";
        mensaje += "Cantidad de notas perdidas: " + (int)perdidas + "\n";
        mensaje += "Notas:" ;

        for (int i = 0; i < notas.length; i++) {
            mensaje += notas[i];

            if (i < notas.length - 1) {
                mensaje += ", ";
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);


    }

    public static double promedio(double[] notas) {
        double suma = 0;
        for (int i = 0; i < notas.length; i++) {
            suma += notas[i];
        }
        double promedioTotal = suma / notas.length;
        return promedioTotal;
    }

    public static double numeroMayor(double[] notas) {
        double mayor = 0;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] > mayor) {
                mayor = notas[i];
            }
        }
        return mayor;
    }

    public static double numeroMenor(double[] notas) {
        double menor = 5;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] < menor) {
                menor = notas[i];
            }
        }
        return menor;
    }

    public static double [] numerosOrdenado(double[] notas) {
        for (int i = 0; i < (notas.length - 1); i++) {
            for (int j = 0; j < (notas.length - 1); j++) {
                if (notas [j] > notas [j + 1]) {
                    double aux = notas [j];
                    notas [j] = notas [j + 1];
                    notas [j + 1] = aux;
                }
            }
        }
        return notas;
    }

    public static double notasPerdidas(double[] notas) {
        double p = 0;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] < 3.0){
                p++;

            }
        }
        return p;
    }
}

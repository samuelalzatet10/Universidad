package co.edu.unquindio.poo.model;

import java.time.LocalDate;
import java.util.List;

public record Conductor(
    String nombre,
    String apellidos,
    String identificacion,
    LocalDate fechaNacimiento,
    List<Vehiculo>listaVehiculos,
    Empresa ownedByEmpresa
)
{}

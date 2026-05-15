package co.edu.unquindio.poo.model;

import java.time.LocalDate;
import java.time.LocalTime;


public record RegistroPeaje(
    double valor, LocalDate fecha, LocalTime hora,
    Vehiculo vehiculo, Cobrador entidadCobro
)
{}

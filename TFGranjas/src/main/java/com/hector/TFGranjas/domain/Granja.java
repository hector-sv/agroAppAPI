package com.hector.TFGranjas.domain;
//granjaId
//Nombre
//Direccion
//Telefono
//Correo
//Latitude
//Longitude
//UsuarioId

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "granjas")
public class Granja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank(message = "El campo no puede estar en blanco")
    @NotNull(message = "El campo no puede estar vacío")
    private String nombre;

    @Column
    @NotBlank(message = "El campo no puede estar en blanco")
    @NotNull(message = "El campo no puede estar vacío")
    private String direccion;

    @Column

    private String telefono;

    @Column
    @NotBlank(message = "El campo no puede estar en blanco")
    @NotNull(message = "El campo no puede estar vacío")
    private String correo;

    @Column
    private double latitude;

    @Column
    private double longitud;



}

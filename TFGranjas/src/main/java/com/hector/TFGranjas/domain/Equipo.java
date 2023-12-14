package com.hector.TFGranjas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

////EquipoId
//Nombre
//Tipo
//FechaCompra
//Estado
//GranjaId
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "equipos")
public class Equipo {
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
    private String tipo;

    @Column

    private String fechaCompra;

    @Column
    @NotBlank(message = "El campo no puede estar en blanco")
    @NotNull(message = "El campo no puede estar vacío")
    private String estado;


}
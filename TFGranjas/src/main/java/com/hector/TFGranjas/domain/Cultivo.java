package com.hector.TFGranjas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

//CultivoId
//Nombre
//Tipo
//FechaSiembra
//FechaCosecha
//GranjaId
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "cultivos")
public class Cultivo {
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
    private String fechaSiembra;

    @Column
    private String fechaCosecha;


}

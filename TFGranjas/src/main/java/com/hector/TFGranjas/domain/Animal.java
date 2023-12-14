package com.hector.TFGranjas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

//AnimalId
//Nombre
//Tipo
//FechaEntr
//Sexo
//GranjaId
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "animales")
public class Animal {
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
    private String  fechaEntrada;

    @Column
    @NotBlank(message = "El campo no puede estar en blanco")
    @NotNull(message = "El campo no puede estar vacío")
    private String sexo;


}

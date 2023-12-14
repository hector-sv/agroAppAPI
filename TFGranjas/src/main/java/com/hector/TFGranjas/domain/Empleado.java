package com.hector.TFGranjas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

//EmpleadoId
//Nombre
//Apellido
//Cargo
//FechaContrato
//Slario
//GranjaId
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank(message = "El campo no puede estar en blanco")
    @NotNull(message = "El campo no puede estar vacío")
    private String nombre;

    @Column
    private String apellido;

    @Column
    @NotBlank(message = "El campo no puede estar en blanco")
    @NotNull(message = "El campo no puede estar vacío")
    private String cargo;

    @Column
    @NotBlank(message = "El campo no puede estar en blanco")
    @NotNull(message = "El campo no puede estar vacío")
    private String correo;

    @Column
    private String fechaContrato;

    @Column
    private float salario;


}

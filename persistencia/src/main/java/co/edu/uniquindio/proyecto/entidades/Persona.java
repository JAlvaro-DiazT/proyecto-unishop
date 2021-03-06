package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/*
    Entidad Persona, es una clase que hereda atributos a la clase Administrador y Usuario,
    cuenta con los datos representativos y basicos que toda persona debe tener.

    La clase esta marcada con @Entity para que sea considerada entidad y a la hora de
    desplegar el proyecto poder mapear la clase a la base de datos en SQL

    Se cuenta con los metodos @Getter, @Setter @NoArgsConstructor, @AllArgsConstructor,
    @EqualsAndHashCode(onlyExplicitlyIncluded = true) y @ToString lo que nos facilita acceso
    a los atributos de la entidad de una manera mas organizada en el codigo.

    @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) nos permite indicar el tipo
    de estrategia que se esta utilizando para heredar los datos.

    Todos los atributos cuentan con su respectiva parametrizacion, con lo cual se agregan
    las restricciones pertinentes a los atributos.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@ToString
public class Persona implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Positive
    private Integer codigo;

    @Column(nullable = false, unique = true)
    @Email(message = "Escriba un email valido")
    @NotBlank
    private String email;

    @Column(nullable = false, length = 50)
    @Length(max = 50)
    @NotBlank
    private String nombre;

    @Column(nullable = false, length = 20)
    @Length(max = 20, message = "La contraseña debe tener maximo 20 caracteres")
    @NotBlank
    //@JsonIgnore
    private String password;
}

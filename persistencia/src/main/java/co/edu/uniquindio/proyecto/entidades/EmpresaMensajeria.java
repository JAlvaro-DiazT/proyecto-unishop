package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

/*
    Entidad EmpresaMensajeria, representa la empresa por medio de la cual el producto es enviado al cliente y
    este se encarga de seleccionarla.

    La clase esta marcada con @Entity para que sea considerada entidad y a la hora de
    desplegar el proyecto poder mapear la clase a la base de datos en SQL

    Se cuenta con los metodos @Getter, @Setter @NoArgsConstructor, @AllArgsConstructor,
    @EqualsAndHashCode(onlyExplicitlyIncluded = true) y @ToString lo que nos facilita acceso
    a los atributos de la entidad de una manera mas organizada en el codigo.

    Todos los atributos cuentan con su respectiva parametrizacion, con lo cual se agregan
    las restricciones pertinentes a los atributos.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class EmpresaMensajeria implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 30)
    private String nombre;

    @Column(length = 10)
    private String telefono;

    @OneToOne
    private Compra miCompra;

}

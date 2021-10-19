package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;

/*
    Entidad Comentario, como su nombre lo indica, representa el comentario que un usuario puede
    realizar a determinado producto que este publicado.

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
public class Comentario implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 500)
    private String mensaje;

    @Column(length = 500)
    private String respuesta;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha_comentario;

    @Column(nullable = false)
    @Positive
    @Max(5)
    @Min(1)
    private int calificacion;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto miProducto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario miUsuario;
}

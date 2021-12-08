package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer codigo;

    @Lob
    @NotBlank
    @Column(nullable = false)
    private String mensaje;

    @Lob
    @Column(length = 500)
    private String respuesta;

    @Column(nullable = false)
    private LocalDateTime fecha_comentario;

    @Column(nullable = false)
    @PositiveOrZero
    private int calificacion;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto miProducto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario miUsuario;

    public String getFechaEstilo(){
        return fecha_comentario.format( DateTimeFormatter.ISO_DATE );
    }
}

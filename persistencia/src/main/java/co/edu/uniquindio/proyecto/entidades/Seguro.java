package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/*
    Entidad Seguro, representa el seguro con el que el producto cuenta y el usuario define el periodo de tiempo
    que quiere que este vigente dicho seguro.

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
public class Seguro implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(length = 250)
    private String descripcion;

    @Column(nullable = false)
    private LocalDateTime fecha_inicio;

    @Column(nullable = false)
    private LocalDateTime fecha_vencimiento;

    @Positive
    private float valor;

    @OneToMany(mappedBy = "seguro")
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> miProducto;

    public Seguro(Integer codigo, String descripcion, LocalDateTime fecha_inicio, LocalDateTime fecha_vencimiento, float valor) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_vencimiento = fecha_vencimiento;
        this.valor = valor;
    }
}

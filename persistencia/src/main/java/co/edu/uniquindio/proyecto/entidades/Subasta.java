package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/*
    Entidad Subasta, representa el proceso de subasta de un producto dentro de unishop

    La clase esta marcada con @Entity para que sea considerada entidad y a la hora de
    desplegar el proyecto poder mapear la clase a la base de datos en SQL

    Se cuenta con los metodos @Getter, @Setter @NoArgsConstructor y @ToString(callSuper = true)
    de esta manera accedemos a los datos.

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
public class Subasta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha_limite;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto miProducto;

    @OneToMany(mappedBy = "miSubasta")
    @ToString.Exclude
    private List<DetalleSubasta> miDetalleSubasta;

    public Subasta(Integer codigo, LocalDateTime fecha_limite, Producto miProducto) {
        this.codigo = codigo;
        this.fecha_limite = fecha_limite;
        this.miProducto = miProducto;
    }
}

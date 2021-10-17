package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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

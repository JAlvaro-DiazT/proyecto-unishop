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
    private List<DetalleSubasta> miDetalleSubasta;
}

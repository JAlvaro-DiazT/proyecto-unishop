package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleSubasta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Positive
    private float valor;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha_subasta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Subasta miSubasta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario miUsuario;

}

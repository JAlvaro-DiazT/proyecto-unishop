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
public class Compra implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha_compra;

    @Column(length = 30)
    private String medio_pago;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario miUsuario;

    @OneToMany(mappedBy = "miCompra")
    @ToString.Exclude
    private List<DetalleCompra> miDetalleCompra;

    public Compra(Integer codigo, LocalDateTime fecha_compra, String medio_pago, Usuario miUsuario) {
        this.codigo = codigo;
        this.fecha_compra = fecha_compra;
        this.medio_pago = medio_pago;
        this.miUsuario = miUsuario;
    }
}

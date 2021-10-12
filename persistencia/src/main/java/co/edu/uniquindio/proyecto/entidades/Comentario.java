package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto miProducto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario miUsuario;
}

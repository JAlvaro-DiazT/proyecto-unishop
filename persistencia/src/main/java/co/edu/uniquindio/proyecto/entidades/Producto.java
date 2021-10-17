package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Producto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 20)
    private String nombre;

    @Positive
    private Integer unidades;

    @Column(length = 500)
    private String  descripcion;

    @Positive
    private float precio;

    @Positive
    private float descuento;

    @OneToMany(mappedBy = "miProducto")
    @ToString.Exclude
    private List<DetalleCompra> miDetalleCompra;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha_limite;

    @ElementCollection
    @Column(nullable = false)
    private Map<String,String> imagen;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad miCiudad;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario miUsuario;

    @OneToMany(mappedBy = "miProducto")
    @ToString.Exclude
    private List<Subasta> miSubasta;

    @OneToMany(mappedBy = "miProducto")
    @ToString.Exclude
    private List<Comentario> miComentario;

    @ManyToMany(mappedBy = "miProducto")
    private List<Categoria> miCategoria;

    @ManyToMany
    private List<Usuario> miFavorito;

}

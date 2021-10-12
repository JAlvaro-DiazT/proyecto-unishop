package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> telefonos;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad miCiudad;

    @OneToMany(mappedBy = "miUsuario")
    @ToString.Exclude
    private List<Chat> miChat;

    @OneToMany(mappedBy = "miUsuario")
    @ToString.Exclude
    private List<Compra> miCompra;

    @OneToMany(mappedBy = "miUsuario")
    @ToString.Exclude
    private List<Producto> miProducto;

    @OneToMany(mappedBy = "miUsuario")
    @ToString.Exclude
    private List<DetalleSubasta> miDetalleSubasta;

    @OneToMany(mappedBy = "miUsuario")
    @ToString.Exclude
    private List<Comentario> miComentario;

    @ManyToMany(mappedBy = "miFavorito")
    private List<Producto> favorito;

    public Usuario(Integer codigo, String nombre, String email, String password) {
        super(codigo, nombre, email, password);
    }
}

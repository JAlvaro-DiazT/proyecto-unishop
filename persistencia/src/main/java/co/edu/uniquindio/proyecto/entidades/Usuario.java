package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/*
    Entidad Usuario, hereda atributos de la entidad Persona, representa los usuarios dentro de
    unishop con todos los datos basicos para poder interactuar con la aplicacion.

    La clase esta marcada con @Entity para que sea considerada entidad y a la hora de
    desplegar el proyecto poder mapear la clase a la base de datos en SQL

    Se cuenta con los metodos @Getter, @Setter @NoArgsConstructor y @ToString(callSuper = true)
    de esta manera accedemos a los datos.

    Todos los atributos cuentan con su respectiva parametrizacion, con lo cual se agregan
    las restricciones pertinentes a los atributos.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Map<String, String> telefonos;

    @Column(nullable = false, unique = true)
    private String username;

    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "miUsuario")
    @ToString.Exclude
    private List<Chat> miChat;

    @OneToMany(mappedBy = "miUsuario")
    @ToString.Exclude
    private List<Compra> miCompra;

    @OneToMany(mappedBy = "vendedor")
    @ToString.Exclude
    private List<Producto> miProducto;

    @OneToMany(mappedBy = "miUsuario")
    @ToString.Exclude
    private List<DetalleSubasta> miDetalleSubasta;

    @OneToMany(mappedBy = "miUsuario")
    @ToString.Exclude
    private List<Comentario> miComentario;

    @ManyToMany(mappedBy = "miFavoritoUsuario")
    @ToString.Exclude
    private List<Producto> miProductofavorito;

    public Usuario(Integer codigo, String email, String nombre, String password, Map<String, String> telefonos, Ciudad ciudad) {
        super(codigo, email, nombre, password);
        this.telefonos = telefonos;
        this.ciudad = ciudad;
    }

}

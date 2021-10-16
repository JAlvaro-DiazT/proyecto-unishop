package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ciudad implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(length = 30)
    private String nombre;

    @OneToMany(mappedBy = "miCiudad")
    @ToString.Exclude
    private List<Usuario> miUsuario;

    @OneToMany(mappedBy = "miCiudad")
    @ToString.Exclude
    private List<Producto> miProducto;
}

package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 30)
    private String nombre;

    @ManyToMany
    private List<Producto> miProducto;

}

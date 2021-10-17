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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Chat implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    @OneToMany(mappedBy = "miChat")
    @ToString.Exclude
    private List<Mensaje> miMensaje;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario miUsuario;

    public Chat(Integer codigo, Usuario miUsuario) {
        this.codigo = codigo;
        this.miUsuario = miUsuario;
    }
}

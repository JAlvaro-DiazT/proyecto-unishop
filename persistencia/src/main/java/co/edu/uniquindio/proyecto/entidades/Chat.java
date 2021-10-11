package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Chat implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    @OneToMany(mappedBy = "miChat")
    private List<Mensaje> miMensaje;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario miUsuario;
}

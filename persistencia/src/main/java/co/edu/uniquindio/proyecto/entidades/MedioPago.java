package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MedioPago {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(length = 30)
    private String nombre;

    @OneToMany(mappedBy = "medio_pago")
    @ToString.Exclude
    @JsonIgnore
    private List<Compra> compra;

    public MedioPago(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}

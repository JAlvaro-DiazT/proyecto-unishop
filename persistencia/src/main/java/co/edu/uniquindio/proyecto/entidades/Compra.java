package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/*
    Entidad Compra, en esta entidad se encuentra el registro de la compra de algun producto
    de manera detallada.

    La clase esta marcada con @Entity para que sea considerada entidad y a la hora de
    desplegar el proyecto poder mapear la clase a la base de datos en SQL

    Se cuenta con los metodos @Getter, @Setter @NoArgsConstructor, @AllArgsConstructor,
    @EqualsAndHashCode(onlyExplicitlyIncluded = true) y @ToString lo que nos facilita acceso
    a los atributos de la entidad de una manera mas organizada en el codigo.

    Todos los atributos cuentan con su respectiva parametrizacion, con lo cual se agregan
    las restricciones pertinentes a los atributos.
 */
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false)
    private LocalDateTime fecha_compra;

    @ManyToOne
    private MedioPago medio_pago;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario miUsuario;

    @OneToMany(mappedBy = "miCompra")
    @ToString.Exclude
    @JsonIgnore
    private List<DetalleCompra> miDetalleCompra;

    @ManyToOne
    private EmpresaMensajeria miEmpresaMensajeria;

    public Compra(Integer codigo, LocalDateTime fecha_compra, MedioPago medio_pago, Usuario miUsuario, EmpresaMensajeria miEmpresaMensajeria) {
        this.codigo = codigo;
        this.fecha_compra = fecha_compra;
        this.medio_pago = medio_pago;
        this.miUsuario = miUsuario;
        this.miEmpresaMensajeria = miEmpresaMensajeria;
    }
}

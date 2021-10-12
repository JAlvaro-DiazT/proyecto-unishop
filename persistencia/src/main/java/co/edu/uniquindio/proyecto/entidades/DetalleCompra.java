package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleCompra implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Positive
    private Integer unidades;

    @Positive
    private float precio_producto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Compra miCompra;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto miProducto;
}
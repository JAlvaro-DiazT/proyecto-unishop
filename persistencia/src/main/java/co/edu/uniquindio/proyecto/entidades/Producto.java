package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
    Entidad Producto, la cual representa los productos que van a ser vendidos/comprados en
    unishop, cuenta con todos los atributos necesarios para describir a detalle un producto.

    La clase esta marcada con @Entity para que sea considerada entidad y a la hora de
    desplegar el proyecto poder mapear la clase a la base de datos en SQL

    Se cuenta con los metodos @Getter, @Setter @NoArgsConstructor y @ToString(callSuper = true)
    de esta manera accedemos a los datos.

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
public class Producto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(length = 20, nullable = false)
    private String nombre;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer unidades;

    @NotBlank
    @Column(length = 500, nullable = false)
    private String  descripcion;

    @Positive
    @Column(nullable = false)
    private float precio;

    @PositiveOrZero
    @Column(nullable = false)
    private float descuento;

    @OneToMany(mappedBy = "miProducto")
    @ToString.Exclude
    @JsonIgnore
    private List<DetalleCompra> miDetalleCompra;

    @Future
    @Column(nullable = false)
    private LocalDateTime fecha_limite;

    @ElementCollection
    private List<String> imagen;

    @ManyToOne
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario vendedor;

    @OneToMany(mappedBy = "miProducto")
    @ToString.Exclude
    @JsonIgnore
    private List<Subasta> miSubasta;

    @OneToMany(mappedBy = "miProducto")
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario> miComentario;

    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<Categoria> categoria;

    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<Usuario> miFavoritoUsuario;

    @ManyToOne
    private Seguro seguro;

    public Producto(String nombre, Integer unidades, String descripcion, float precio, float descuento, LocalDateTime fecha_limite, List<String> imagen, Ciudad ciudad, Usuario vendedor, Seguro seguro) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
        this.fecha_limite = fecha_limite;
        this.imagen = imagen;
        this.ciudad = ciudad;
        this.vendedor = vendedor;
        this.seguro = seguro;
    }

    public String getImagenPrincipal(){
        if(imagen!=null && !imagen.isEmpty()){
            return imagen.get(0);
        }
        return "default.png";
    }
}

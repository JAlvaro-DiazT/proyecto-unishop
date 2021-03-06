package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    Repositorio ProductoRepo, Interface la cual extiende de JpaRepository, cuenta con dos datos:
    la tabla que hace referencia (Producto) y el tipo de dato de la llave primaria de la entidad
    (Integer), de esta manera se podra realizar el respectivo CRUD en la tabla Producto.
 */
@Repository
public interface ProductoRepo extends JpaRepository<Producto,Integer> {

    // Una lista de todos los productos creados por un usuario específico
    @Query("select p from Producto p where p.vendedor.codigo = :codigo")
    List<Producto> buscarProductoPorUsuario(Integer codigo);

    // Una lista de todos los productos y la información del usuario que los creó. Incluya todos los
    //usuarios (así no tengan productos creados).
    @Query("select u.nombre, p from Producto p left join p.vendedor u")
    List<Object[]> listarProductosYUsuarios();

    //Una lista de productos que tienen un descuento que está dentro de un rango que se pase
    //por parámetro. Solo muestre los productos que tengan unidades disponibles.
    @Query("select p from Producto p where (p.descuento between :rangoInferior and :rangoSuperior) and p.unidades > 0")
    List<Producto>ListarPorDescuentoYdisponibilidad(float rangoInferior, float rangoSuperior);

    @Query("select p from Producto p where p.nombre like concat('%', :nombre, '%') ")
    List<Producto> buscarProductoNombre(String nombre);

    @Query("select p from Producto p where :categoria member of p.categoria")
    List<Producto> listarPorCategoria(Categoria categoria);

    @Query("select p.unidades from Producto p where p.codigo = :id")
    Integer obtenerTotalUnidadesDisponibles(Integer id);

    @Query("select p from Producto p where p.vendedor.codigo = :codigo ")
    List<Producto> listarProductosUsuario(Integer codigo);

    @Query("select c from Categoria c where c.nombre like concat('%', :nombreCategoria, '%')")
     Categoria obtenerCategoria(String nombreCategoria);


    @Query("select p from Producto  p where p.precio between :precio1 and :precio2")
    List<Producto>listarPorRangoDePrecios(float precio1,float precio2);

    @Query("select p from Producto p where  p.ciudad.nombre = :Ciudad")
    List<Producto>listarProductosCiudad(String Ciudad);

    @Query("select p from Producto p where  p.descuento >= :descuento")
    List<Producto>listarPorDescuento(float descuento);


    @Query("select p from Producto p where  p.seguro.codigo = :codigo")
    List<Producto>listarPorSeguro(int codigo);

    @Query("select p.unidades from Producto p where p.codigo = :codigo ")
    Number cantidadProducto(Integer codigo);
}

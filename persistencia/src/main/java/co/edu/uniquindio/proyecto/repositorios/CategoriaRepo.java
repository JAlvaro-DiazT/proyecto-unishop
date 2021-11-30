package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    Repositorio CategoriaRepo, Interface la cual extiende de JpaRepository, cuenta con dos datos:
    la tabla que hace referencia (Categoria) y el tipo de dato de la llave primaria de la entidad
    (Integer), de esta manera se podra realizar el respectivo CRUD en la tabla Categoria.
 */
@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {

    // Una lista con las categorías y su calificación promedio. Ordene la lista de mayor a menor
    //de acuerdo a la calificación promedio. (incluya todos las categorías así no tenga productos
    // con calificaciones)

    //@Query("select c.nombre, avg(co.calificacion) from Categoria c left join Comentario co group by c.nombre order by co.calificacion desc ")
    //List<Object[]> listarPorCategoriasYCalificacion();
    // @Query("select co.miProducto.miCategoria, avg(co.calificacion) from Comentario co group by co.miProducto.miCategoria")
    // List<Object[]> listarPorCategoriasYCalificacion();

    @Query("select c.nombre, avg(cp.calificacion) from Categoria c join c.producto p left join p.miComentario cp group by c order by cp.calificacion desc ")
    List<Object[]> listarPorCategoriasYCalificacion();
}

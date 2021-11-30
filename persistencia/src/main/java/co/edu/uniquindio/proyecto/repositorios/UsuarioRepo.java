package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
    Repositorio UsuarioRepo, Interface la cual extiende de JpaRepository, cuenta con dos datos:
    la tabla que hace referencia (Usuario) y el tipo de dato de la llave primaria de la entidad
    (Integer), de esta manera se podra realizar el respectivo CRUD en la tabla Usuario.
 */
@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    List <Usuario> findAllByNombreContains(String nombre);

    Optional <Usuario> findByEmail(String email);

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmailAndPassword(String email, String password);

    @Query("select p from Usuario u, IN (u.miProductofavorito) p where u.email = :email")
    List<Producto> obtenerProductosFavoritos(String email);

}

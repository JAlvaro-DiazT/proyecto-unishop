package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

/*
    Clase por medio de la cual se van a realizar las pruebas de las funciones de: Registrar, Eliminar,
    Actualizar y Listar de la entidad Comentario.

    La anotacion @DataJpaTest indica que es una clase para probar datos
    La anotacion @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) para que
    los datos de la prueba se guarde en la base de datos
    La anotacion @Autowired inicializa las variables que representan componentes de SpringBoot
    La anotacion @Test permite ejecutar las pruebas
    La anotacion @Sql("classpath:datosUnishop.sql") por medio del cual se realiza la conexion a los
    recursos del archivo Sql
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //para que se guarde en la base
public class ComentarioTest {
    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Test // programa de tipo test para ingresar un comentario
    @Sql("classpath:datosUnishop.sql")
    public void RegistrarTest() {

        //Obtener un producto ya registrado
        Producto producto =  productoRepo.findById(203).orElse(null);

        //Obtener un usuario ya registrado
        Usuario usuario =  usuarioRepo.findById(101).orElse(null);

        Comentario comentario=  new Comentario(410,"Me encanto el producto","Nos alegra que te haya gustado",LocalDateTime.now(),5,producto,usuario);

        Comentario comentarioGuardado= comentarioRepo.save(comentario);
        Assertions.assertNotNull(comentarioGuardado);
    }

    @Test // programa de tipo test para eliminar un comentario
    @Sql("classpath:datosUnishop.sql")
    public void eliminarTest() {

        //borramos el comentario buscando por codigo
        comentarioRepo.deleteById(400);

        Comentario comentarioBuscado = comentarioRepo.findById(400).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(comentarioBuscado);
    }

    @Test // programa de tipo test para actualizar un comentario
    @Sql("classpath:datosUnishop.sql")
    public void actualizarTest() {

        Comentario comentario = comentarioRepo.findById(403).orElse(null);
        comentario.setMensaje("Ya recibí los accesorios del producto, gracias");
        //Se guarda la modificación
        comentarioRepo.save(comentario);

        Comentario comentarioBuscado = comentarioRepo.findById(403).orElse(null);

        //Se busca que si haya quedado en el registro el cambio
        Assertions.assertEquals("Ya recibí los accesorios del producto, gracias",comentarioBuscado.getMensaje());
    }

    @Test // programa de tipo test para listar los comentarios creados
    @Sql("classpath:datosUnishop.sql")
    public void ListarTest() {

        List<Comentario> comentarios =comentarioRepo.findAll();
        comentarios.forEach(Comentario -> System.out.println(Comentario));
    }
}

package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import com.sun.activation.registries.MailcapParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Clase por medio de la cual se van a realizar las pruebas de las funciones de: Registrar, Eliminar,
    Actualizar y Listar de la entidad Usuario.

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
public class UsuarioTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test // programa de tipo test para ingresar un usuario con ciudad y telefonos
    @Sql("classpath:datosUnishop.sql")
    public void RegistrarTest() {

        //Obtener una ciudad ya registrada
        Ciudad miCiudad =  ciudadRepo.findById(3).orElse(null);

        Map<String,String> telefonos = new HashMap<>();
        telefonos.put("3192738324","Casa");
        telefonos.put("3121927675","Trabajo");

        Usuario usuario=  new Usuario(110,"alvaro@email.com","Alvaro Diaz","i4u1y4",telefonos, miCiudad);
        Usuario usuarioGuardado= usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);
    }

    @Test // programa de tipo test para eliminar un usuario
    @Sql("classpath:datosUnishop.sql")
    public void eliminarTest() {

        //borramos el usuario buscando por codigo
        usuarioRepo.deleteById(102);

        Usuario usuarioBuscado = usuarioRepo.findById(102).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(usuarioBuscado);
    }

    @Test // programa de tipo test para actualizar un usuario
    @Sql("classpath:datosUnishop.sql")
    public void actualizarTest() {

        Usuario usuario = usuarioRepo.findById(102).orElse(null);
        usuario.setPassword("cn8t2cem83");
        //Se guarda la modificación
        usuarioRepo.save(usuario);

        Usuario usuarioBuscado = usuarioRepo.findById(102).orElse(null);

        //Se busca que si haya quedado en el registro el cambio
        Assertions.assertEquals("cn8t2cem83",usuarioBuscado.getPassword());
    }

    @Test // programa de tipo test para listar los usuarios creados
    @Sql("classpath:datosUnishop.sql")
    public void ListarTest() {

        List<Usuario> usuarios =usuarioRepo.findAll();
        usuarios.forEach(Usuario -> System.out.println(Usuario));
    }
}

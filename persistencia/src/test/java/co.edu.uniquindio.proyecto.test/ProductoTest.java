package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Clase por medio de la cual se van a realizar las pruebas de las funciones de: Registrar, Eliminar,
    Actualizar y Listar de la entidad Producto.

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
public class ProductoTest {
    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;


    @Test // programa de tipo test para ingresar un producto con ciudad,usuario e imagen
    @Sql("classpath:datosUnishop.sql")
    public void RegistrarTest() {

        //Obtener una ciudad ya registrada
        Ciudad miCiudad =  ciudadRepo.findById(3).orElse(null);

        //Obtener un usuario ya registrado
        Usuario usuario =  usuarioRepo.findById(101).orElse(null);

        Map<String,String> imagen = new HashMap<>();
        imagen.put("ruta/img5",".jpg");

        Producto producto=  new Producto(210,"destornillador",30,
                "herramientas de mano diseñados para apretar o aflojar tornillos",
                8000,9, LocalDateTime.now(),imagen,miCiudad,usuario);

        Producto productoGuardado= productoRepo.save(producto);
        Assertions.assertNotNull(productoGuardado);
    }

    @Test // programa de tipo test para eliminar un producto
    @Sql("classpath:datosUnishop.sql")
    public void eliminarTest() {

        //borramos el producto buscando por codigo
        productoRepo.deleteById(200);

        Producto productoBuscado = productoRepo.findById(200).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(productoBuscado);
    }

    @Test // programa de tipo test para actualizar un producto
    @Sql("classpath:datosUnishop.sql")
    public void actualizarTest() {

        Producto producto = productoRepo.findById(203).orElse(null);
        producto.setPrecio(400000);
        //Se guarda la modificación
        productoRepo.save(producto);

        Producto productoBuscado = productoRepo.findById(203).orElse(null);

        //Se busca que si haya quedado en el registro el cambio
        Assertions.assertEquals(400000,productoBuscado.getPrecio());

    }

    @Test // programa de tipo test para listar los usuarios creados
    @Sql("classpath:datosUnishop.sql")
    public void ListarTest() {

        List<Producto> productos =productoRepo.findAll();
        productos.forEach(Producto -> System.out.println(Producto));
    }
}

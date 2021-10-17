package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
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
    Actualizar y Listar de la entidad Subasta.

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
public class SubastaTest {
    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private SubastaRepo subastaRepo;

    @Test // programa de tipo test para ingresar una subasta
    @Sql("classpath:datosUnishop.sql")
    public void RegistrarTest() {

        //Obtener un producto ya registrado
        Producto producto =  productoRepo.findById(202).orElse(null);

        Subasta subasta=  new Subasta(555, LocalDateTime.now(),producto);

        Subasta subastaGuardada= subastaRepo.save(subasta);
        Assertions.assertNotNull(subastaGuardada);
    }

    @Test // programa de tipo test para eliminar una subasta
    @Sql("classpath:datosUnishop.sql")
    public void eliminarTest() {

        //borramos la subasta buscando por codigo
        subastaRepo.deleteById(500);

        Subasta subastaBuscada = subastaRepo.findById(500).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(subastaBuscada);
    }

    @Test // programa de tipo test para actualizar una subasta
    @Sql("classpath:datosUnishop.sql")
    public void actualizarTest() {

        //Obtener un producto ya registrado
        Producto producto =  productoRepo.findById(203).orElse(null);

        Subasta subasta = subastaRepo.findById(501).orElse(null);
        subasta.setMiProducto(producto);
        //Se guarda la modificación
        subastaRepo.save(subasta);

        Subasta subastaBuscada = subastaRepo.findById(501).orElse(null);

        //Se busca que si haya quedado en el registro el cambio
        Assertions.assertEquals(producto,subastaBuscada.getMiProducto());

    }

    @Test // programa de tipo test para listar los subastas
    @Sql("classpath:datosUnishop.sql")
    public void ListarTest() {

        List<Subasta> subastas =subastaRepo.findAll();
        subastas.forEach(Subasta -> System.out.println(Subasta));
    }
}

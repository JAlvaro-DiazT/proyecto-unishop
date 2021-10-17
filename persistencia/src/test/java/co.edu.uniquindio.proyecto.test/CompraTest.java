package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
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
    Actualizar y Listar de la entidad Compra.

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
public class CompraTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Test // programa de tipo test para ingresar una compra con usuario
    @Sql("classpath:datosUnishop.sql")
    public void RegistrarTest() {

        //Obtener un usuario ya registrado
        Usuario miUsuario =  usuarioRepo.findById(103).orElse(null);

        Compra compra=  new Compra(0005, LocalDateTime.now(),"Credito",miUsuario);
        Compra compraGuardada= compraRepo.save(compra);
        Assertions.assertNotNull(compraGuardada);
    }

    @Test // programa de tipo test para eliminar una compra
    @Sql("classpath:datosUnishop.sql")
    public void eliminarTest() {

        //borramos la compra buscando por codigo
        compraRepo.deleteById(0002);

        Compra compraBuscada = compraRepo.findById(0002).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(compraBuscada);
    }

    @Test // programa de tipo test para actualizar una compra
    @Sql("classpath:datosUnishop.sql")
    public void actualizarTest() {

        Compra compra = compraRepo.findById(0002).orElse(null);
        compra.setMedio_pago("Debito");
        //Se guarda la modificación
        compraRepo.save(compra);

        Compra compraBuscada = compraRepo.findById(0002).orElse(null);

        //Se busca que si haya quedado en el registro el cambio
        Assertions.assertEquals("Debito",compraBuscada.getMedio_pago());

    }

    @Test // programa de tipo test para listar las compras realizadas
    @Sql("classpath:datosUnishop.sql")
    public void ListarTest() {

        List<Compra> compras =compraRepo.findAll();
        compras.forEach(Compra -> System.out.println(Compra));
    }
}

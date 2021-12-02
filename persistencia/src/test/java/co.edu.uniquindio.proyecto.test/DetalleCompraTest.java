package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/*
    Clase por medio de la cual se van a realizar las pruebas de las funciones de: Registrar, Eliminar,
    Actualizar y Listar de la entidad DetalleCompra.

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
public class DetalleCompraTest {
    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    @Test // programa de tipo test para ingresar el detalle de una compra
    @Sql("classpath:datosUnishop.sql")
    public void RegistrarTest() {

        //Obtener un producto ya registrado
        Producto producto =  productoRepo.findById(203).orElse(null);

        //Obtener una compra ya registrada
        Compra compra =  compraRepo.findById(0001).orElse(null);

        DetalleCompra detalleCompra=  new DetalleCompra(320,4,700000,compra, producto);

        DetalleCompra detalleCompra1= detalleCompraRepo.save(detalleCompra);
        Assertions.assertNotNull(detalleCompra1);
    }

    @Test // programa de tipo test para eliminar el detalle de una compra
    @Sql("classpath:datosUnishop.sql")
    public void eliminarTest() {

        //borramos el detalle de la compra buscando por codigo
        detalleCompraRepo.deleteById(301);

        DetalleCompra detalleCompra = detalleCompraRepo.findById(301).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(detalleCompra);
    }

    @Test // programa de tipo test para actualizar el detalle de una compra
    @Sql("classpath:datosUnishop.sql")
    public void actualizarTest() {

        DetalleCompra detalleCompra = detalleCompraRepo.findById(304).orElse(null);
        detalleCompra.setUnidades(6);
        //Se guarda la modificación
        detalleCompraRepo.save(detalleCompra);

        DetalleCompra detalleCompra1 = detalleCompraRepo.findById(304).orElse(null);

        //Se busca que si haya quedado en el registro el cambio
        Assertions.assertEquals(6,detalleCompra1.getUnidades());
    }

    @Test // programa de tipo test para listar los detalles de las compras
    @Sql("classpath:datosUnishop.sql")
    public void ListarTest() {

        List<DetalleCompra> detalleCompras =detalleCompraRepo.findAll();
        detalleCompras.forEach(DetalleCompra -> System.out.println(DetalleCompra));
    }
}

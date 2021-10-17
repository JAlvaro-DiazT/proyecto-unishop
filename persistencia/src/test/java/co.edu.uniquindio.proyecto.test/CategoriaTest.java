package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/*
    Clase por medio de la cual se van a realizar las pruebas de las funciones de: Registrar, Eliminar,
    Actualizar y Listar de la entidad Categoria.

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
public class CategoriaTest {

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Test // programa de tipo test para ingresar una categoria
    @Sql("classpath:datosUnishop.sql")
    public void RegistrarTest() {

        Categoria categoria=  new Categoria(04,"Artesanias");
        Categoria categoriaGuardada= categoriaRepo.save(categoria);
        Assertions.assertNotNull(categoriaGuardada);
    }

    @Test // programa de tipo test para eliminar una categoria
    @Sql("classpath:datosUnishop.sql")
    public void eliminarTest() {

        //borramos la categoria buscando por codigo
        categoriaRepo.deleteById(03);

        Categoria categoriaBuscada = categoriaRepo.findById(03).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(categoriaBuscada);
    }

    @Test // programa de tipo test para actualizar una categoria
    @Sql("classpath:datosUnishop.sql")
    public void actualizarTest() {

        Categoria categoria = categoriaRepo.findById(02).orElse(null);
        categoria.setNombre("Merceria");
        //Se guarda la modificación
        categoriaRepo.save(categoria);

        Categoria categoriaBuscada = categoriaRepo.findById(02).orElse(null);

        //Se busca que si haya quedado en el registro el cambio
        Assertions.assertEquals("Merceria",categoriaBuscada.getNombre());
    }

    @Test // programa de tipo test para listar las categorias creadas
    @Sql("classpath:datosUnishop.sql")
    public void ListarTest() {

        List<Categoria> categorias =categoriaRepo.findAll();
        categorias.forEach(Categoria -> System.out.println(Categoria));
    }

}

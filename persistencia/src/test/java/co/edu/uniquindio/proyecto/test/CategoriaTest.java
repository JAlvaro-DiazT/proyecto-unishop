package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //para que se guarde en la base
public class CategoriaTest

{
    @Autowired
    private CategoriaRepo categoriaRepo;


    @Test // programa de tipo test para ingresar un usuario
    @Sql("classpath:unishopBase.sql")
    public void RegistrarTest()

    {


        //Categoria categoria= new Categoria(320,"Electrodomesticos",);
    }
}

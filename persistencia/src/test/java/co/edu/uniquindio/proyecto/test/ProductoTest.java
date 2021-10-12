package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //para que se guarde en la base

public class ProductoTest
{
    private ProductoRepo productoRepo;

    @Test // programa de tipo test para ingresar un usuario
    @Sql("classpath:unishopBase.sql")
    public void registrarTest()
    {

       // Map<String,String> telefonoss = new HashMap<>();
       // telefonoss.put("casa", "437535945");
        //telefonoss.put("celular", "352525");





        Producto producto = new Producto(122,"Lavadora a chorros",50,"gama alta",);
    }

}

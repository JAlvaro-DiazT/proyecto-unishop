package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/*
    Repositorio CompraRepo, Interface la cual extiende de JpaRepository, cuenta con dos datos:
    la tabla que hace referencia (Compra) y el tipo de dato de la llave primaria de la entidad
    (Integer), de esta manera se podra realizar el respectivo CRUD en la tabla Compra.
 */
@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer> {

    //La cantidad de compras que se hace por cada medio de pago disponible.
    @Query("select c.medio_pago, count(c) from Compra c group by c.medio_pago")
    List<Object[]> listarPorMedioDepPago();

    //El valor total de cada una de las compras que ha hecho un usuario específico.
    @Query("select c.codigo, sum(d.precio_producto * d.unidades) from Compra c join DetalleCompra d join c.miUsuario u where u.codigo = :cod")
    List<Object[]> valorTotalCompras(Integer cod);

    @Query("select c from Compra c where c.miUsuario.codigo = :codigo ")
    List<Compra> listarProductosUsuario(Integer codigo);

    @Query("select d from DetalleCompra d where d.miCompra.codigo = :codigoCompra")
    List<DetalleCompra> listarCompras(Integer codigoCompra);
}

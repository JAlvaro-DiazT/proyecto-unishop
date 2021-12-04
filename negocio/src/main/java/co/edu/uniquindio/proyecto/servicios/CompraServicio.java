package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;

import java.util.List;

public interface CompraServicio {

    Compra comprarProducto(Compra compra)throws Exception;

    Compra obtenerCompra(Integer codigo) throws Exception;

    List<Compra> listarComprasUsuario(Integer codigo) throws Exception;
}

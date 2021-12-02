package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;

public interface CompraServicio {

    Compra comprarProducto(Compra compra)throws Exception;

    Compra obtenerCompra(Integer codigo) throws Exception;
}

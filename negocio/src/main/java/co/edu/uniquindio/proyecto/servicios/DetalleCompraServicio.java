package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;

import java.util.List;

public interface DetalleCompraServicio{
    List<DetalleCompra> listarDetallesCompra(Integer codigoCompra)throws Exception;
}

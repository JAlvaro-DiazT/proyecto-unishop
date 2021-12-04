package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface DetalleCompraServicio{
    ArrayList<DetalleCompra> listarDetallesCompra(Integer codigoCompra);
}

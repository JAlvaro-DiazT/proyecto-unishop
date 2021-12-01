package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.MedioPago;

import java.util.List;

public interface MedioPagoServicio {
    List<MedioPago> listarMediosPagos();

    MedioPago obtenerMedioPago(Integer id) throws Exception;
}

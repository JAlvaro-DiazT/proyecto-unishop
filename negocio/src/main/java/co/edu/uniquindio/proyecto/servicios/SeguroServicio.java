package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Seguro;

import java.util.List;

public interface SeguroServicio {

    List<Seguro> listarSeguros();

    Seguro obtenerSeguro(Integer id) throws Exception;
}

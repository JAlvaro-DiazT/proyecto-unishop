package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.MedioPago;
import co.edu.uniquindio.proyecto.repositorios.MedioPagoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedioPagoServicioImpl implements MedioPagoServicio {

    private final MedioPagoRepo medioPagoRepo;

    public MedioPagoServicioImpl(MedioPagoRepo medioPagoRepo) {
        this.medioPagoRepo = medioPagoRepo;
    }

    @Override
    public List<MedioPago> listarMediosPagos() {
        return medioPagoRepo.findAll();
    }

    @Override
    public MedioPago obtenerMedioPago(Integer id) throws Exception {
        return medioPagoRepo.findById(id).orElseThrow( () -> new Exception("La opción no corresponde a ningun medio de pago"));
    }
}

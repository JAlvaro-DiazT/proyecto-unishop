package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.EmpresaMensajeria;
import co.edu.uniquindio.proyecto.repositorios.EmpresaMensajeriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaMensajeriaImpl implements EmpresaMensajeriaServicio {

    @Autowired
    private EmpresaMensajeriaRepo empresaMensajeriaRepo;

    @Override
    public EmpresaMensajeria obtenerEmpresa(Integer id) throws Exception {
        return empresaMensajeriaRepo.findById(id).orElseThrow( () -> new Exception("El id no corresponde a ninguna Empresa de mensajeria"));
    }
}

package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Seguro;
import co.edu.uniquindio.proyecto.repositorios.SeguroRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeguroServicioImpl implements SeguroServicio{

    private final SeguroRepo seguroRepo;

    public SeguroServicioImpl(SeguroRepo seguroRepo) {
        this.seguroRepo = seguroRepo;
    }

    @Override
    public List<Seguro> listarSeguros() {
        return seguroRepo.findAll();
    }

    @Override
    public Seguro obtenerSeguro(Integer id) throws Exception {
        return seguroRepo.findById(id).orElseThrow( () -> new Exception("No has seleccionado ningun seguro"));
    }
}

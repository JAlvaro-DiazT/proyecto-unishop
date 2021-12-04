package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;

    public CompraServicioImpl(CompraRepo compraRepo) {
        this.compraRepo = compraRepo;
    }

    @Override
    public Compra comprarProducto(Compra compra) throws Exception {
        try {
            return compraRepo.save(compra);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Compra obtenerCompra(Integer codigo) throws Exception {
        return compraRepo.findById(codigo).orElseThrow( () -> new Exception("El codigo de la compra no es valido"));

    }

    @Override
    public List<Compra> listarComprasUsuario(Integer codigo) throws Exception {
        return compraRepo.listarProductosUsuario(codigo);
    }
}

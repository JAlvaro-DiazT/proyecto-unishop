package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleCompraImpl implements DetalleCompraServicio{

    private final CompraRepo compraRepo;

    public DetalleCompraImpl(CompraRepo compraRepo){
        this.compraRepo = compraRepo;
    }

    @Override
    public List<DetalleCompra> listarDetallesCompra(Integer codigoCompra) throws Exception{
        return compraRepo.listarCompras(codigoCompra);
    }
}

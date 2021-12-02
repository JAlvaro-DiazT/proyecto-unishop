package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.EmpresaMensajeria;

import java.util.List;

public interface EmpresaMensajeriaServicio {

    List<EmpresaMensajeria> listarEmpresasMensajerias();

    EmpresaMensajeria obtenerEmpresa(Integer id) throws Exception;
}

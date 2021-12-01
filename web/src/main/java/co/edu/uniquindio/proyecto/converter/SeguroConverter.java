package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Seguro;
import co.edu.uniquindio.proyecto.servicios.SeguroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class SeguroConverter implements Converter<Seguro>, Serializable {

    @Autowired
    private SeguroServicio seguroServicio;

    @Override
    public Seguro getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Seguro seguro = null;
        try {
            seguro = seguroServicio.obtenerSeguro(Integer.parseInt(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seguro;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Seguro seguro) {
        if(seguro!=null){
            return seguro.getCodigo().toString();
        }
        return "";
    }
}

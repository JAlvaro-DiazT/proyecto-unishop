package co.edu.uniquindio.proyecto.excepciones;

import java.util.NoSuchElementException;

public class ProductoNoEncontradoException extends NoSuchElementException {

    public ProductoNoEncontradoException(String error) {
        super(error);
    }
}

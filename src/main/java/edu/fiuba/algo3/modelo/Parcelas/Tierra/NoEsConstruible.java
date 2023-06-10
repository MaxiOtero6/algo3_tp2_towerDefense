package edu.fiuba.algo3.modelo.Parcelas.Tierra;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Torres.*;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;

public class NoEsConstruible implements Construible {

    @Override //Crear Exception
    public Torre construir(Torre torre, Posicion posicion) throws TerrenoDeConstruccionInvalidoError
    {
        throw new TerrenoDeConstruccionInvalidoError();
    }
    
}

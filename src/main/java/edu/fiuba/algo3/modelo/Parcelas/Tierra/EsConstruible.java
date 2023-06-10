package edu.fiuba.algo3.modelo.Parcelas.Tierra;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Torres.*;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;

public class EsConstruible implements Construible{
    
    @Override
    public Torre construir(Torre torre, Posicion posicion) throws TerrenoDeConstruccionInvalidoError
    {
        torre.setearPosicion(posicion);
        return torre;
    }
}

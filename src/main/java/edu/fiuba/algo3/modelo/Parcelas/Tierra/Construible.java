package edu.fiuba.algo3.modelo.Parcelas.Tierra;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.Torres.*;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;

public interface Construible {
    
    public Torre construir(Torre torre, Posicion posicion) throws TerrenoDeConstruccionInvalidoError;

}

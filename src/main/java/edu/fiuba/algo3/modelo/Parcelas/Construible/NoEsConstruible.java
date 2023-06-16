package edu.fiuba.algo3.modelo.Parcelas.Construible;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;

public class NoEsConstruible implements Construible {

    @Override
    public Defensa construir(Defensa defensa, Posicion posicion)
    {
        throw new TerrenoDeConstruccionInvalidoError();
    }
    
}

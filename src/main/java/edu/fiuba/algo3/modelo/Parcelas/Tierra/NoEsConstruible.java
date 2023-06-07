package edu.fiuba.algo3.modelo.Parcelas.Tierra;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;

public class NoEsConstruible implements Construible {

    @Override //Crear Exception
    public Defensa construir(Defensa defensa, Posicion posicion) throws TerrenoDeConstruccionInvalidoError
    {
        throw new TerrenoDeConstruccionInvalidoError();
    }
    
}

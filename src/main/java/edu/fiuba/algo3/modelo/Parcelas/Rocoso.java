package edu.fiuba.algo3.modelo.Parcelas;

import edu.fiuba.algo3.modelo.Errores.AgregarEnemigoError;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class Rocoso extends Parcela {
    public Rocoso(int coordenadaX, int coordenadaY)
    {
        super(coordenadaX, coordenadaY);
    }

    @Override
    public void construir(Defensa defensa)
    {
        throw new TerrenoDeConstruccionInvalidoError();
    }
}
